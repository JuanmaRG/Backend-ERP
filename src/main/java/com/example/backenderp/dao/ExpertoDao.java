package com.example.backenderp.dao;

import com.example.backenderp.model.Experto;
import com.example.backenderp.model.ExpertoPage;
import com.example.backenderp.model.ExpertoSearchCriteria;
import com.example.backenderp.model.Tag;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExpertoDao {
    Page<Experto> findAllWithFilter(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria);
    Page<Experto> findAllWithFilterTag(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Tag tag);

}
