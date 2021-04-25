package com.example.backenderp.dao.Impl;

import com.example.backenderp.dao.TagDao;
import com.example.backenderp.model.Tag;
import com.example.backenderp.model.TagPage;
import com.example.backenderp.model.TagSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class TagDaoImpl implements TagDao{
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public TagDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Tag> findAllWithFilterNombre(TagPage tagPage,
                                             TagSearchCriteria tagSearchCriteria){
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(Tag.class);
        Root<Tag> tagRoot = criteriaQuery.from(Tag.class);
        Predicate predicate = getPredicate(tagSearchCriteria, tagRoot);
        criteriaQuery.where(predicate);
        setOrder(tagPage, criteriaQuery, tagRoot);

        TypedQuery<Tag> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(tagPage.getPageNumber() * tagPage.getPageSize());
        typedQuery.setMaxResults(tagPage.getPageSize());

        Pageable pageable = getPageable(tagPage);

        long tagsCount = getTagsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, tagsCount);
    }




    private Predicate getPredicate(TagSearchCriteria tagSearchCriteria, Root<Tag> tagRoot) {
        List<Predicate> predicates= new ArrayList<>();
        if(Objects.nonNull(tagSearchCriteria.getName())){
            predicates.add(
                    criteriaBuilder.like(tagRoot.get("nombre"),"%" + tagSearchCriteria.getName() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(TagPage tagPage,
                          CriteriaQuery<Tag> criteriaQuery,
                          Root<Tag> tagRoot) {
        if(tagPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(tagRoot.get(tagPage.getSortBy())));
        }else{
            criteriaQuery.orderBy(criteriaBuilder.desc(tagRoot.get(tagPage.getSortBy())));
        }
    }

    private Pageable getPageable(TagPage tagPage) {
        Sort sort = Sort.by(tagPage.getSortDirection(), tagPage.getSortBy());
        return PageRequest.of(tagPage.getPageNumber(),tagPage.getPageSize(),sort);
    }

    private long getTagsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Tag> countRoot = countQuery.from(Tag.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
