package com.example.backend.application.service;

import com.example.backend.application.port.in.TagUseCase;
import com.example.backend.application.port.out.TagRepositoryPort;
import com.example.backend.domain.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class TagService implements TagUseCase {

    private final TagRepositoryPort tagRepo;


    @Override
    public Tag create(String name) {
        tagRepo.findByName(name).ifPresent(t -> {
            throw new IllegalArgumentException("Tag exist: " + name);
        });
        Tag tag = new Tag(name);
        return tagRepo.save(tag);
    }

    @Override
    public List<Tag> listAll() {
        return tagRepo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        // Optionally remove relationship with notes if DB constraints require it.
        tagRepo.deleteById(id);
    }
}
