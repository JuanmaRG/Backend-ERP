package com.example.backenderp.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

// Clase que contiene la clave compuesta de nuestra tabla Experto_Tag
@Embeddable
public class ExpertoTagPK implements Serializable {

    @Column(name="id_experto")
    private Long idExperto;

    @Column(name="id_tag")
    private Long idTag;

    public Long getIdExperto() {
        return idExperto;
    }

    public ExpertoTagPK setIdExperto(Long idExperto) {
        this.idExperto = idExperto;
        return this;
    }

    public Long getIdTag() {
        return idTag;
    }

    public ExpertoTagPK setIdTag(Long idTag) {
        this.idTag = idTag;
        return this;
    }
}
