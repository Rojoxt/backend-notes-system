package com.example.backend.application.service;


import com.example.backend.application.port.in.NoteUseCase;
import com.example.backend.application.port.out.NoteRepositoryPort;
import com.example.backend.application.port.out.TagRepositoryPort;
import com.example.backend.domain.model.Note;
import com.example.backend.domain.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteService implements NoteUseCase {

    private final NoteRepositoryPort repository;
    private final TagRepositoryPort tagRepositoryPort;


    @Override
    public Note create(Note note) {
        return repository.save(note);
    }

    @Override
    public Note update(Long id, Note note) {
        Note existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        existing.setTitle(note.getTitle());
        existing.setContent(note.getContent());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Note toggleArchive(Long id) {
        Note existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        existing.toggleArchive();
        return repository.save(existing);
    }

    @Override
    public List<Note> listActive() {
        return repository.findActive();
    }

    @Override
    public List<Note> listArchived() {
        return repository.findArchived();
    }

    @Override
    public Note assignTagToNote(Long noteId, String tagName) {
        Note note = repository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        Tag tag = tagRepositoryPort.findByName(tagName)
                .orElseGet(() -> tagRepositoryPort.save(new Tag(tagName)));
        // Añadir la etiqueta a la nota
        note.getTags().add(tag);
        return repository.save(note);
    }

    @Override
    public Note removeTagFromNote(Long noteId, String tagName) {
        Note note = repository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        // eliminar por nombre
        note.getTags().removeIf(t -> tagName.equals(t.getName()));
        return repository.save(note);
    }

    @Override
    public List<Note> findByTag(String tagName) {
        return repository.findByTagName(tagName);
    }
}
