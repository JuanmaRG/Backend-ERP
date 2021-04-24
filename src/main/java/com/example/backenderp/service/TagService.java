package com.example.backenderp.service;

import com.example.backenderp.model.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<Tag> findTagById(Long id);
    Tag createTag(Tag tag);
    Tag updateTag(Tag tag);
    ResponseEntity<Void> deleteTagById(Long id);
}
