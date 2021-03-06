package com.example.backenderp.service;

import com.example.backenderp.model.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

public interface TagService {
    Optional<Tag> findTagById(Long id);
    Tag createTag(Tag tag);
    Tag updateTag(Tag tag);
    ResponseEntity<Void> deleteTagById(Long id);
    Page<Tag> findAllWithFilterNombre(TagPage tagPage, TagSearchCriteria tagSearchCriteria);

}
