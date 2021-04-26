package com.example.backenderp.service.Impl;

import com.example.backenderp.dao.ExpertoDao;
import com.example.backenderp.model.*;
import com.example.backenderp.repository.ExpertoRepository;
import com.example.backenderp.service.ExpertoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertoServiceImpl implements ExpertoService {

    private final Logger log = LoggerFactory.getLogger(Experto.class);
    private final ExpertoRepository repository;
    private final ExpertoDao expertoDao;

    public ExpertoServiceImpl(ExpertoRepository repository, ExpertoDao expertoDao) {
        this.repository = repository;
        this.expertoDao = expertoDao;
    }

    @Override
    public Optional<Experto> findOneById(Long id) {
        log.info("Service findOneExpertById");
        if (repository.existsById(id)) {
            Optional<Experto> expertoRetrieved = repository.findById(id);
            if (!expertoRetrieved.isPresent())
                return Optional.empty();
            return expertoRetrieved;
        }
        return Optional.empty();

    }

    @Override
    public Experto createExperto(Experto experto) {
        log.info("SERVICE createExperto");
        return repository.save(experto);
    }

    @Override
    public Experto updateExperto(Experto experto) {
        log.info("SERVICE updateExpert");
        if(repository.existsById(experto.getId())){
            experto.setUpdated_at(Instant.now());
            return repository.save(experto);
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteExpertoById(Long id) {
        log.info("SERVICE deleteExpertoById");
        if (repository.existsById(id)) {
            this.repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Page<Experto> findAllWithFilter(ExpertoPage expertoPage,
                                           ExpertoSearchCriteria expertoSearchCriteria){
        log.info("SERVICE findAllWithFilter");
        return this.expertoDao.findAllWithFilter(expertoPage,expertoSearchCriteria);
    }

    @Override
    public Page<Experto> findAllWithFilterTag(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Tag tag) {
        log.info("SERVICE findAllWithFilterTag");
        return this.expertoDao.findAllWithFilterTag(expertoPage,expertoSearchCriteria,tag);
    }

    @Override
    public Page<Experto> findAllWithFilterEstado(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Estado estado) {
        log.info("SERVICE findAllWithFilterEstado");
        return this.expertoDao.findAllWithFilterEstado(expertoPage,expertoSearchCriteria,estado);
    }


}
