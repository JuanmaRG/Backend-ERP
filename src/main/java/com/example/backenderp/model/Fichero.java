package com.example.backenderp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="FICHERO")
public class Fichero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_fichero")
    private Long id;

    //Foreign Key
    @Column(name="id_experto")
    private Long idExperto;

    private String foto;

    private String cv;

    //RELATION
    @OneToOne
    @JoinColumn(name="id_experto",  insertable = false, updatable = false)
    private Experto experto;



    //CONSTRUCTOR
    public Fichero() {
    }

    public Fichero(Long idExperto, String foto, String cv) {
        this.idExperto = idExperto;
        this.foto = foto;
        this.cv = cv;
    }
    //GETTER AND SETTER


    public Long getId() {
        return id;
    }

    public Fichero setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getIdExperto() {
        return idExperto;
    }

    public Fichero setIdExperto(Long idExperto) {
        this.idExperto = idExperto;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public Fichero setFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public String getCv() {
        return cv;
    }

    public Fichero setCv(String cv) {
        this.cv = cv;
        return this;
    }
}
