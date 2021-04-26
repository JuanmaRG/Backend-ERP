package com.example.backenderp.model;

public class ExpertoSearchCriteria {

    private String name;

    private String modalidad;

    private Long idEstado;

    private Long idEtiqueta;

    public String getName() {
        return name;
    }

    public ExpertoSearchCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public String getModalidad() {
        return modalidad;
    }

    public ExpertoSearchCriteria setModalidad(String modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public ExpertoSearchCriteria setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public Long getIdEtiqueta() {
        return idEtiqueta;
    }

    public ExpertoSearchCriteria setIdEtiqueta(Long idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
        return this;
    }
}
