package com.example.backenderp.controller;


import com.example.backenderp.model.Tag;
import com.example.backenderp.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.readers.operation.ResponseMessagesReader;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TagController {

    private final Logger log = LoggerFactory.getLogger(Tag.class);
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * ENDPOINT encuentra un Tag usando el ID
     * @param id Id del tag a buscar
     * @return Tag
     */
    @GetMapping("/etiquetas/{id}")
    public ResponseEntity<Tag> findTagById(@PathVariable Long id){
        log.debug("ENDPOINT FindOneTagById Task");

        if(id != null){
            Optional<Tag> tagReceived= this.tagService.findTagById(id);
           if(tagReceived.isPresent()){
               return ResponseEntity.ok().body(tagReceived.get());
           }
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.badRequest().build();

    }

    /**
     * ENDPOINT crea un tag
     * @param tag
     * @return Tag creado
     */
    @PostMapping("/etiquetas")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag){
        log.debug("ENDPOINT createTag");

        if(tag.getId() == null){
            Tag tagCreated = this.tagService.createTag(tag);
            return ResponseEntity.ok().body(tagCreated);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * ENDPOINT borra la etiqueta con ID
     */
    @DeleteMapping("/etiquetas/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Long id){
        log.debug("ENDPOINT deleteTagById");
        if(id != null){
            return this.tagService.deleteTagById(id);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/etiquetas")
    public ResponseEntity<Tag> updateTagById(@RequestBody Tag tag){
        log.debug("ENDPOINT updateTagByID");


        if (tag.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Tag tagReceived = this.tagService.updateTag(tag);

        if (tagReceived == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(tagReceived );
    }
}
