package com.example.backenderp.dao.Impl;

import com.example.backenderp.dao.ExpertoDao;

import com.example.backenderp.model.Experto;
import com.example.backenderp.model.ExpertoPage;
import com.example.backenderp.model.ExpertoSearchCriteria;
import com.example.backenderp.model.Tag;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExpertoDaoImpl implements ExpertoDao {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ExpertoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Experto> findAllWithFilterTag(ExpertoPage expertoPage, ExpertoSearchCriteria expertoSearchCriteria, Tag tag) {


        List<Experto> expertList= entityManager.createQuery("select e from Experto e inner join e.tagList et  where et.id = "+expertoSearchCriteria.getIdEtiqueta()).getResultList();
        Pageable paging = PageRequest.of(expertoPage.getPageNumber(), expertoPage.getPageSize());
        int start = Math.min((int)paging.getOffset(), expertList.size());
        int end = Math.min((start + paging.getPageSize()), expertList.size());
        Page<Experto> page = new PageImpl<>(expertList.subList(start, end), paging, expertList.size());
        return page;

    }

    public Page<Experto> findAllWithFilter(ExpertoPage expertoPage,
                                           ExpertoSearchCriteria expertoSearchCriteria) {

        CriteriaQuery<Experto> criteriaQuery = criteriaBuilder.createQuery(Experto.class);
        Root<Experto> expertoRoot = criteriaQuery.from(Experto.class);
        Predicate predicate = getPredicate(expertoSearchCriteria, expertoRoot);
        criteriaQuery.where(predicate);
        setOrder(expertoPage, criteriaQuery, expertoRoot);
        TypedQuery<Experto> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(expertoPage.getPageNumber() * expertoPage.getPageSize());
        typedQuery.setMaxResults(expertoPage.getPageSize());

        Pageable pageable = getPageable(expertoPage);
        long expertosCount = getExpertosCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, expertosCount);
    }


    private Predicate getPredicate(ExpertoSearchCriteria expertoSearchCriteria,
                                   Root<Experto> expertoRoot) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(expertoSearchCriteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(expertoRoot.get("nombre"), "%" + expertoSearchCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(expertoSearchCriteria.getModalidad())) {
            predicates.add(
                    criteriaBuilder.like(expertoRoot.get("modalidad"), "%" + expertoSearchCriteria.getModalidad() + "%")
            );
        }
        if (Objects.nonNull(expertoSearchCriteria.getIdEtiqueta())) {
            predicates.add(
                    criteriaBuilder.like(expertoRoot.get("tag_id"), expertoSearchCriteria.getIdEtiqueta().toString())
            );
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ExpertoPage expertoPage, CriteriaQuery<Experto> criteriaQuery, Root<Experto> expertoRoot) {
        if (expertoPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(expertoRoot.get(expertoPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(expertoRoot.get(expertoPage.getSortBy())));
        }
    }

    private Pageable getPageable(ExpertoPage expertoPage) {
        Sort sort = Sort.by(expertoPage.getSortDirection(), expertoPage.getSortBy());
        return PageRequest.of(expertoPage.getPageNumber(), expertoPage.getPageSize(), sort);
    }

    private long getExpertosCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Experto> countRoot = countQuery.from(Experto.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }



}