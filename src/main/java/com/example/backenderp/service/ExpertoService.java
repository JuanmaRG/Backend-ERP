package com.example.backenderp.service;

import com.example.backenderp.model.Experto;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ExpertoService {
    Optional<Experto> findOneById(Long id);

    Experto createExperto(Experto experto);

    Experto updateExperto(Experto experto);

    ResponseEntity<Void> deleteExpertoById(Long id);

}
