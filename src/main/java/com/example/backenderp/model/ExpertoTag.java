package com.example.backenderp.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="EXPERTO_TAG")
public class ExpertoTag {

    @EmbeddedId
    private ExpertoTagPK id;

    @Column(name = "created_at")
    private Instant createdAt;

    //RELATIONS
    @ManyToOne
    @JoinColumn(name = "id_experto",insertable = false,updatable = false)
    private Experto experto;

    @ManyToOne
    @JoinColumn(name="id_tag",insertable = false, updatable = false)
    private Tag etiqueta;

    public ExpertoTagPK getId() {
        return id;
    }

    public ExpertoTag setId(ExpertoTagPK id) {
        this.id = id;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public ExpertoTag setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }


}
