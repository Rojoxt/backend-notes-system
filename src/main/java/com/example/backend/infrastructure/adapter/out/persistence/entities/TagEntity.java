package com.example.backend.infrastructure.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Getter
@Setter
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<NoteEntity> notes = new HashSet<>();

    public TagEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<NoteEntity> getNotes() { return notes; }
    public void setNotes(Set<NoteEntity> notes) { this.notes = notes; }
}
