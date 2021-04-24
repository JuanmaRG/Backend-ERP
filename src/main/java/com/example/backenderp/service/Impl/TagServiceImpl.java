package com.example.backenderp.service.Impl;

import com.example.backenderp.model.Tag;
import com.example.backenderp.repository.TagRepository;
import com.example.backenderp.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Tag> findTagById(Long id) {
        log.info("SERVICE findTabById ", id);

        if(repository.existsById(id)){
            Optional<Tag> tagReceived = repository.findById(id);
            if(tagReceived.isPresent())
                return tagReceived;
        }
        return Optional.empty();
    }

    @Override
    public Tag createTag(Tag tag) {
        log.info("TagService crea un tag atraves del repositorio.");

        if(ObjectUtils.isEmpty(tag))
            return null;
        tag.setUpdateAt(Instant.now());
        tag.setCreatedAt(Instant.now());
        return repository.save(tag);
    }

    @Override
    public Tag updateTag(Tag tag) {
        log.info("SERVICE updateTag");

        if(repository.existsById(tag.getId())){
            tag.setUpdateAt(Instant.now());
            return repository.save(tag);
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteTagById(Long id) {
        log.info("SERVICE deleteTaskById ", id);

        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
