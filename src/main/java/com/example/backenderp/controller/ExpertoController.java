package com.example.backenderp.controller;

import com.example.backenderp.model.*;
import com.example.backenderp.service.ExpertoService;
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


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ExpertoController {

    private final Logger log = LoggerFactory.getLogger(Experto.class);

    private final ExpertoService expertoService;

    public ExpertoController(ExpertoService service) {
        this.expertoService = service;
    }

    /**
     * ENDPOINT que busca el experto usando su ID
     * @param id
     * @return  Experto
     */
    @GetMapping("/expertos/{id}")
    @ApiOperation("Devuelve el Experto con ID = id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Experto no encontrado")
    })
    public ResponseEntity<Experto> findOneById(@ApiParam(value = "ID del expero a buscar",required = true,example = "1") @PathVariable Long id){

        log.debug("ENDPOINT: Devuelve el Experto con ID = id");

        Optional<Experto> expertoRetrieved = expertoService.findOneById(id);

        if(expertoRetrieved.isPresent())
            return ResponseEntity.ok().body(expertoRetrieved.get());
        return ResponseEntity.notFound().build();

    }

    /**
     * ENDPOINT para crear un experto
     * @param experto
     * @return el experto si lo ha creado
     */
    @PostMapping("/expertos")
    @ApiOperation("Crea un experto.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")
    })
        public ResponseEntity<Experto> createExperto(@RequestBody Experto experto) throws URISyntaxException {

        log.debug("ENDPOINT: Crea un experto" );

        if(experto.getId() == null){
            Experto expertoCreated = expertoService.createExperto(experto);
            return ResponseEntity.created(new URI("/api/experto/"+ expertoCreated.getId())).body(expertoCreated);
        }
            return ResponseEntity.badRequest().build();
        }

    /**
     * ENDPOINT para hacer un update a un experto
     * @param experto
     * @return
     */
    @PutMapping("/expertos")
    @ApiOperation("Actualiza el Experto con ID = id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Experto no encontrado"),
            @ApiResponse(code = 400, message = "Bad request")
    })
        public ResponseEntity<Experto> updateExperto(@RequestBody Experto experto) {

        log.debug("ENDPOINT: Actualiza el Experto con ID = id");

        if (experto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Experto expertoReceived = expertoService.updateExperto(experto);

        if (expertoReceived == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(expertoReceived);
    }

    @DeleteMapping("/expertos/{id}")
    @ApiOperation("Borra el Experto con ID = id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Experto no encontrado")
    })
    public ResponseEntity<Void> deleteExpertoById(@ApiParam(value = "ID del experto a borrar", required = true, example = "1") @PathVariable Long id){
        log.debug("ENDPOINT: Borra el Experto con ID = id");

        if(id != null){

            return this.expertoService.deleteExpertoById(id);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/expertos")
    @ApiOperation("Devuelve todos los expertos filtrandolos segun Nombre, Estado, Etiqueta, Limite y Pagina.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Experto no encontrado"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public ResponseEntity<Page<Experto>> findAllWithFilter(@RequestParam(name="nombre", required = false) String nombre,
                                                           @RequestParam(name="limite",required = false) Integer limite,
                                                           @RequestParam(name="pagina",required = false) Integer pagina,
                                                           @RequestParam(name="idestado" ,required = false) Long estado,
                                                           @RequestParam(name="etiqueta", required=false) Long etiqueta){

        log.debug("ENDPOINT: Devuelve todos los expertos filtrandolos");

        ExpertoPage expertoPage = new ExpertoPage();
        ExpertoSearchCriteria expertoSearchCriteria = new ExpertoSearchCriteria();


        if(Objects.nonNull(nombre))
            expertoSearchCriteria.setName(nombre);


        if(Objects.nonNull(limite))
            expertoPage.setPageSize(limite);

        if(Objects.nonNull(pagina))
            expertoPage.setPageNumber(pagina);

        if(Objects.nonNull(etiqueta)){
            expertoSearchCriteria.setIdEtiqueta(etiqueta);
            return new ResponseEntity<>(expertoService.findAllWithFilterTag(expertoPage,expertoSearchCriteria,new Tag()),HttpStatus.OK);
            /*
            switch (estado){
                case "Verificado": expertoSearchCriteria.setIdEstado(1);
                                break;
                case "Pendiente": expertoSearchCriteria.setIdEstado(2);
                                break;
                default: expertoSearchCriteria.setIdEstado(3);
                                break;
            }*/
        }

        return new ResponseEntity<>(expertoService.findAllWithFilter(expertoPage,expertoSearchCriteria),HttpStatus.OK);


    }
}
