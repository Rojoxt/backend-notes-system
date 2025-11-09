package com.example.backend.infrastructure.adapter.in.web.controller;

import com.example.backend.application.port.in.NoteUseCase;
import com.example.backend.domain.model.Note;
import com.example.backend.domain.model.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
public class NoteController {

    private final NoteUseCase service;

    public NoteController(NoteUseCase service) {
        this.service = service;
    }

    @PostMapping
    public Note create(@RequestBody Note note) {
        return service.create(note);
    }

    @GetMapping("/active")
    public List<Note> getActive() {
        return service.listActive();
    }

    @GetMapping("/archived")
    public List<Note> getArchived() {
        return service.listArchived();
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable Long id, @RequestBody Note note) {
        return service.update(id, note);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/toggle")
    public Note toggle(@PathVariable Long id) {
        return service.toggleArchive(id);
    }

    @PostMapping("/{id}/tags")
    public Note assignTag(@PathVariable Long id, @RequestBody Tag tag) {
        return service.assignTagToNote(id, tag.getName());
    }

    @DeleteMapping("/{id}/tags/{name}")
    public Note removeTag(@PathVariable Long id, @PathVariable String name) {
        return service.removeTagFromNote(id, name);
    }

    @GetMapping("/tag/{name}")
    public List<Note> findByTag(@PathVariable String name) {
        return service.findByTag(name);
    }
}
