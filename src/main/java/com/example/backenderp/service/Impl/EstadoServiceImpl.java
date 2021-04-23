package com.example.backenderp.service.Impl;

import com.example.backenderp.model.Estado;
import com.example.backenderp.repository.EstadoRepository;
import com.example.backenderp.service.EstadoService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository repository;

    public EstadoServiceImpl(EstadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Estado> findOneById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Estado createEstado(Estado estado) {
        return repository.save(estado);
    }

}
