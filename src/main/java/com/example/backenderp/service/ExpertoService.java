package com.example.backenderp.service;

import com.example.backenderp.model.Experto;
import com.example.backenderp.model.ExpertoPage;
import com.example.backenderp.model.ExpertoSearchCriteria;
import com.example.backenderp.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ExpertoService {
    Optional<Experto> findOneById(Long id);

    Experto createExperto(Experto experto);

    Experto updateExperto(Experto experto);

    ResponseEntity<Void> deleteExpertoById(Long id);

    Page<Experto> findAllWithFilter(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria);
    Page<Experto> findAllWithFilterTag(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Tag tag);

}
