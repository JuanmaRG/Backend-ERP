package com.example.backenderp.service;

import com.example.backenderp.model.Estado;

import java.util.Optional;

public interface EstadoService {
    public Optional<Estado> findOneById(Long id);

    public Estado createEstado(Estado estado);
}
