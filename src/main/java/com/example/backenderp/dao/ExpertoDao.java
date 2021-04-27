package com.example.backenderp.dao;

import com.example.backenderp.model.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExpertoDao {
    Page<Experto> findAllWithFilter(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria);
    Page<Experto> findAllWithFilterTag(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Tag tag);
    Page<Experto> findAllWithFilterEstado(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Estado estado);
}
