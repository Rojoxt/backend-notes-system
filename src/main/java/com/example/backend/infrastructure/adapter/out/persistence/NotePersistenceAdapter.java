package com.example.backend.infrastructure.adapter.out.persistence;

import com.example.backend.application.port.out.NoteRepositoryPort;
import com.example.backend.domain.model.Note;
import com.example.backend.domain.model.Tag;
import com.example.backend.infrastructure.adapter.out.persistence.entities.NoteEntity;
import com.example.backend.infrastructure.adapter.out.persistence.entities.TagEntity;
import com.example.backend.infrastructure.adapter.out.persistence.repository.SpringNoteRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NotePersistenceAdapter implements NoteRepositoryPort {

    private final SpringNoteRepository repository;

    public NotePersistenceAdapter(SpringNoteRepository repository) {
        this.repository = repository;
    }

    private Note toDomain(NoteEntity e) {
        Note note = new Note(e.getId(), e.getTitle(), e.getContent(),
                e.isArchived(), e.getCreatedAt(), e.getUpdatedAt());
        // mapear tags
        note.setTags(e.getTags().stream()
                .map(te -> new Tag(te.getId(), te.getName()))
                .collect(Collectors.toSet()));
        return note;
    }

    private NoteEntity toEntity(Note d) {
        NoteEntity e = new NoteEntity();
        e.setId(d.getId());
        e.setTitle(d.getTitle());
        e.setContent(d.getContent());
        e.setArchived(d.isArchived());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        // mapear tags de dominio a entidad
        if (d.getTags() != null) {
            e.setTags(d.getTags().stream().map(t -> {
                TagEntity te = new TagEntity();
                te.setId(t.getId());
                te.setName(t.getName());
                return te;
            }).collect(Collectors.toSet()));
        }
        return e;
    }

    @Override
    public Note save(Note note) {
        return toDomain(repository.save(toEntity(note)));
    }

    @Override
    public Optional<Note> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Note> findActive() {
        return repository.findByArchivedFalseOrderByUpdatedAtDesc()
                .stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Note> findArchived() {
        return repository.findByArchivedTrueOrderByUpdatedAtDesc()
                .stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Note> findByTagName(String tagName) {
        return repository.findByTags_Name(tagName)
                .stream().map(this::toDomain).collect(Collectors.toList());
    }
}
