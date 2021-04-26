package com.example.backenderp.model;

import org.springframework.data.domain.Sort;

public class TagPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "nombre";

    public TagPage() {
    }

    //GETTER AND SETTER

    public int getPageNumber() {
        return pageNumber;
    }

    public TagPage setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public TagPage setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public TagPage setSortDirection(Sort.Direction sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }

    public String getSortBy() {
        return sortBy;
    }

    public TagPage setSortBy(String sortBy) {
        this.sortBy = sortBy;
        return this;
    }
}
