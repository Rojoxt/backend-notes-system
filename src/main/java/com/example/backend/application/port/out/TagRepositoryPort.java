package com.example.backend.application.port.out;


import com.example.backend.domain.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepositoryPort {
    Tag save(Tag tag);
    Optional<Tag> findByName(String name);
    List<Tag> findAll();
    void deleteById(Long id);
}
