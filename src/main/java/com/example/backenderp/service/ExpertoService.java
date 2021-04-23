package com.example.backenderp.service;

import com.example.backenderp.model.Experto;

import java.util.Optional;

public interface ExpertoService {
    Optional<Experto> findOneById(Long id);
}
