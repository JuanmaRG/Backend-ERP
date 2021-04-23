package com.example.backenderp.service.Impl;

import com.example.backenderp.model.Disponibilidad;
import com.example.backenderp.repository.DisponibilidadRepository;
import com.example.backenderp.service.DisponibilidadService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {

    final DisponibilidadRepository disponibilidadRepository;

    public DisponibilidadServiceImpl(DisponibilidadRepository disponibilidadRepository) {
        this.disponibilidadRepository = disponibilidadRepository;
    }

    @Override
    public Optional<Disponibilidad> findOneById(Long id) {
        return disponibilidadRepository.findById(id);
    }

    @Override
    public Disponibilidad createDisponibilidad(Disponibilidad disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }
}
