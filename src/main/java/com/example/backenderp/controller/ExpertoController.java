package com.example.backenderp.controller;

import com.example.backenderp.model.Experto;
import com.example.backenderp.service.ExpertoService;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class ExpertoController {

    private final Logger log = LoggerFactory.getLogger(Experto.class);

    private final ExpertoService service;

    public ExpertoController(ExpertoService service) {
        this.service = service;
    }

    /**
     * ENDPOINT que busca el experto usando su ID
     * @param id
     * @return  Experto
     */
    @GetMapping("/expertos/{id}")
    public ResponseEntity<Experto> findOneById(@PathVariable Long id){

        log.debug("ENDPOINT findOneByID");

        Optional<Experto> expertoRetrieved = service.findOneById(id);

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
        public ResponseEntity<Experto> createExperto(@RequestBody Experto experto) throws URISyntaxException {

        log.debug("ENDPOINT createExperto" );

        if(experto.getId() == null){
            Experto expertoCreated = service.createExperto(experto);
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
        public ResponseEntity<Experto> updateExperto(@RequestBody Experto experto) {

        log.debug("ENDPOINT updateExperto");

        if (experto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Experto expertoReceived = service.updateExperto(experto);

        if (expertoReceived == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(expertoReceived);
    }

    @DeleteMapping("/expertos/{id}")
    public ResponseEntity<Void> deleteExpertoById(@PathVariable Long id){
        if(id != null){

            return this.service.deleteExpertoById(id);
        }
        return ResponseEntity.badRequest().build();
    }

}
