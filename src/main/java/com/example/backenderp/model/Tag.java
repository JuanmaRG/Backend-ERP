package com.example.backenderp.model;

import javassist.expr.NewArray;

import javax.persistence.*;
import java.beans.Expression;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tag")
    private Long id;

    private String nombre;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updateAt;


    //RELATIONS
    @OneToMany(mappedBy = "experto")
    private List<ExpertoTag> expertosList = new ArrayList<>();
    //CONSTRUCTOR

    public Tag() {
    }

    public Tag(String nombre, Instant updateAt) {
        this.nombre = nombre;
        this.updateAt = updateAt;
        this.createdAt = Instant.now();
    }

    // GETTER AND SETTER

    public Long getId() {
        return id;
    }

    public Tag setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Tag setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Tag setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public Tag setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
        return this;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
