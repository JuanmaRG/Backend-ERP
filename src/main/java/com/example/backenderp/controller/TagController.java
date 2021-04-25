package com.example.backenderp.controller;


import com.example.backenderp.model.Tag;
import com.example.backenderp.model.TagPage;
import com.example.backenderp.model.TagSearchCriteria;
import com.example.backenderp.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
        log.debug("ENDPOINT Crea una etiqueta\n");

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
        log.debug("ENDPOINT Borra la Etiqueta con ID = id");
        if(id != null){
            return this.tagService.deleteTagById(id);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/etiquetas")
    public ResponseEntity<Tag> updateTagById(@RequestBody Tag tag){
        log.debug("ENDPOINT DActualiza la Etiqueta con ID = id");


        if (tag.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Tag tagReceived = this.tagService.updateTag(tag);

        if (tagReceived == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(tagReceived );
    }

    @GetMapping("/etiquetas")
    public ResponseEntity<Page<Tag>> findAllWithFilterNombre(TagPage tagPage, TagSearchCriteria tagSearchCriteria){
        log.debug("ENDPOINT Devuelve todas las etiquetas");


        tagSearchCriteria.setName("Oracle");
        return new ResponseEntity<>(tagService.findAllWithFilterNombre(tagPage,tagSearchCriteria), HttpStatus.OK);
    }
}
