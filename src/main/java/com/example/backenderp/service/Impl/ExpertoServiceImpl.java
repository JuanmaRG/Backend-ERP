package com.example.backenderp.service.Impl;

import com.example.backenderp.model.Experto;
import com.example.backenderp.repository.ExpertoRepository;
import com.example.backenderp.service.ExpertoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpertoServiceImpl implements ExpertoService {

    private final Logger log = LoggerFactory.getLogger(Experto.class);
    private final ExpertoRepository repository;

    public ExpertoServiceImpl(ExpertoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Experto> findOneById(Long id) {
        log.info("findOneExpertById");
        if (repository.existsById(id)) {
            Optional<Experto> expertoRetrieved = repository.findById(id);
            if (!expertoRetrieved.isPresent())
                return Optional.empty();
            return expertoRetrieved;
        }
        return Optional.empty();

    }
}