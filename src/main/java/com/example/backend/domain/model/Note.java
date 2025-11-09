package com.example.backend.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Note {
    private Long id;
    private String title;
    private String content;
    private boolean archived;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<Tag> tags = new HashSet<>();

    public Note(Long id, String title, String content, boolean archived, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.archived = archived;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Note() {
    }

    public Note(String title, String content) {
        this(null, title, content, false, LocalDateTime.now(), LocalDateTime.now());
    }

    // Tags getter/setter
    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }

    // Getters y setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public boolean isArchived() { return archived; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void toggleArchive() { this.archived = !this.archived; }
}