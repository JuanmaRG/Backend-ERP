package com.example.backenderp.controller;

import com.example.backenderp.model.Experto;
import com.example.backenderp.service.ExpertoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
     * Metodo que busca el experto usando su ID
     * @param id
     * @return  Experto
     */
    @GetMapping("/expertos/{id}")
    public ResponseEntity<Experto> findOneById(@PathVariable Long id){
        log.debug("RestController request to retrieve a expert with id ",id);
        Optional<Experto> expertoRetrieved = service.findOneById(id);

        if(expertoRetrieved.isPresent())
            return ResponseEntity.ok().body(expertoRetrieved.get());
        return ResponseEntity.notFound().build();

    }
}
