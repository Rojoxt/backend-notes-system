package com.example.backend.infrastructure.adapter.in.web.controller;

import com.example.backend.application.port.in.TagUseCase;
import com.example.backend.domain.model.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
public class TagController {

    private final TagUseCase tagService;

    public TagController(TagUseCase tagService) {
        this.tagService = tagService;
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return tagService.create(tag.getName());
    }

    @GetMapping
    public List<Tag> listAll() {
        return tagService.listAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagService.deleteById(id);
    }
}
