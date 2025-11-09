package com.example.backend.application.port.in;

import com.example.backend.domain.model.Tag;

import java.util.List;

public interface TagUseCase {
    Tag create(String name);
    List<Tag> listAll();
    void deleteById(Long id);
}
