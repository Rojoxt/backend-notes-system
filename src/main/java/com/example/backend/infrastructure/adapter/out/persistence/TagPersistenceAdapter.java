package com.example.backend.infrastructure.adapter.out.persistence;

import com.example.backend.application.port.out.TagRepositoryPort;
import com.example.backend.domain.model.Tag;
import com.example.backend.infrastructure.adapter.out.persistence.entities.TagEntity;
import com.example.backend.infrastructure.adapter.out.persistence.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TagPersistenceAdapter implements TagRepositoryPort {

    private final TagRepository repository;


    private Tag toDomain(TagEntity e) {
        return new Tag(e.getId(), e.getName());
    }

    private TagEntity toEntity(Tag d) {
        TagEntity e = new TagEntity();
        e.setId(d.getId());
        e.setName(d.getName());
        return e;
    }

    @Override
    public Tag save(Tag tag) {
        return toDomain(repository.save(toEntity(tag)));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return repository.findByName(name).map(this::toDomain);
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
