package com.example.backend.infrastructure.adapter.out.persistence.repository;

import com.example.backend.infrastructure.adapter.out.persistence.entities.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringNoteRepository extends JpaRepository<NoteEntity, Long> {
    List<NoteEntity> findByArchivedFalseOrderByUpdatedAtDesc();
    List<NoteEntity> findByArchivedTrueOrderByUpdatedAtDesc();
    List<NoteEntity> findByTags_Name(String tagName);
}
