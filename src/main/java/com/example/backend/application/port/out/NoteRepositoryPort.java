package com.example.backend.application.port.out;


import com.example.backend.domain.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepositoryPort {
    Note save(Note note);
    Optional<Note> findById(Long id);
    void deleteById(Long id);
    List<Note> findActive();
    List<Note> findArchived();
    List<Note> findByTagName(String tagName);
}
