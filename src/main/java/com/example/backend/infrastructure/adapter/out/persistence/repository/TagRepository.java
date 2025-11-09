package com.example.backend.infrastructure.adapter.out.persistence.repository;

import com.example.backend.infrastructure.adapter.out.persistence.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByName(String name);
}
