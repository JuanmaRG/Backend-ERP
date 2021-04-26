package com.example.backenderp.dao;

import com.example.backenderp.model.Tag;
import com.example.backenderp.model.TagPage;
import com.example.backenderp.model.TagSearchCriteria;
import org.springframework.data.domain.Page;

public interface TagDao {
    public Page<Tag> findAllWithFilterNombre(TagPage tagPage, TagSearchCriteria tagSearchCriteria);

}
