package com.example.backenderp.model;

import org.springframework.data.domain.Sort;

public class ExpertoPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "nombre";

    public ExpertoPage() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public ExpertoPage setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public ExpertoPage setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public ExpertoPage setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }

    public String getSortBy() {
        return sortBy;
    }

    public ExpertoPage setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }
}
