package com.example.backenderp.controller;
import com.example.backenderp.model.Tag;
import com.example.backenderp.model.TagPage;
import com.example.backenderp.model.TagSearchCriteria;
import com.example.backenderp.service.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:55135", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
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
    @ApiOperation("Devuelve la etiqueta con ID = id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tag no encontrado"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Tag> findTagById(@ApiParam(value = "El ID del tag a buscar", required = true, example = "1") @PathVariable Long id){
        log.debug("ENDPOINT: Devuelve la etiqueta con ID = id");

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
     * ENDPOINT para crear un tag
     * @param tag
     * @return Tag creado
     */
    @PostMapping("/etiquetas")
    @ApiOperation("Crea una etiqueta")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Tag> createTag(@ApiParam(value = "El Tag a crear") @RequestBody Tag tag){
        log.debug("ENDPOINT: Crea una etiqueta");

        if(tag.getId() == null){
            Tag tagCreated = this.tagService.createTag(tag);
            return ResponseEntity.ok().body(tagCreated);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * ENDPOINT para borrar la etiqueta usando el ID
     * @param id ID de la etiqueta a borrar
     * @return
     */
    @DeleteMapping("/etiquetas/{id}")
    @ApiOperation("Borra la Etiqueta con ID = id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tag no encontrado"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Void> deleteTagById(@ApiParam(value = "ID del Tag a buscar",required = true,example = "1") @PathVariable Long id){
        log.debug("ENDPOINT: Borra la Etiqueta con ID = id");
        if(id != null){
            return this.tagService.deleteTagById(id);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * ENDPOINT para modificar un Tag existente
     * @param tag se le pasa el tag a modificar
     * @return
     */
    @PutMapping("/etiquetas")
    @ApiOperation("Actualiza la Etiqueta con ID = id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tag no encontrado")
    })
    public ResponseEntity<Tag> updateTagById(@RequestBody Tag tag){
        log.debug("ENDPOINT: Actualiza la Etiqueta con ID = id");


        if (tag.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Tag tagReceived = this.tagService.updateTag(tag);

        if (tagReceived == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(tagReceived );
    }

    /**
     * ENDPOINt para Devuelve todas las etiquetas haciendo uso de filtros
     * @param nombre filtro para buscar por nombre
     * @param limite filtro para Limite
     * @param pagina filtro de paginacion
     * @return
     */
    @GetMapping("/etiquetas")
    @ApiOperation("Devuelve todas las etiquetas segun los filtros usados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tag no encontrado")
    })
    public ResponseEntity<Page<Tag>> findAllWithFilterNombre( @RequestParam(name="nombre", required = false) String nombre,
                                                              @RequestParam(name="limite",required = false) Integer limite,
                                                              @RequestParam(name="pagina",required = false) Integer pagina){
        log.debug("ENDPOINT Devuelve todas las etiquetas");

        TagPage tagPage = new TagPage();
        TagSearchCriteria tagSearchCriteria = new TagSearchCriteria();


        if(Objects.nonNull(nombre))
            tagSearchCriteria.setName(nombre);


        if(Objects.nonNull(limite))
            tagPage.setPageSize(limite);

        if(Objects.nonNull(pagina))
            tagPage.setPageNumber(pagina);


        return new ResponseEntity<>(tagService.findAllWithFilterNombre(tagPage,tagSearchCriteria), HttpStatus.OK);
    }

}
