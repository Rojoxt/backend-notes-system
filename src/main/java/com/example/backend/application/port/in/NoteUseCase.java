package com.example.backend.application.port.in;

import com.example.backend.domain.model.Note;

import java.util.List;

public interface NoteUseCase {
    Note create(Note note);
    Note update(Long id, Note note);
    void delete(Long id);
    Note toggleArchive(Long id);
    List<Note> listActive();
    List<Note> listArchived();
    Note assignTagToNote(Long noteId, String tagName);
    Note removeTagFromNote(Long noteId, String tagName);
    List<Note> findByTag(String tagName);
}
