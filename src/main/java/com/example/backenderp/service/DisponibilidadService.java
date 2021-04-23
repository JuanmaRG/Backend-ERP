package com.example.backenderp.service;

import com.example.backenderp.model.Disponibilidad;
import com.example.backenderp.model.Estado;

import java.util.Optional;

public interface DisponibilidadService {
    public Optional<Disponibilidad> findOneById(Long id);

    public Disponibilidad createDisponibilidad (Disponibilidad disponibilidad);
}
