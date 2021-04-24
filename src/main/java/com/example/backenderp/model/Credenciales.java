package com.example.backenderp.model;

import javax.persistence.*;

@Entity
@Table(name="CREDENCIAL")
public class Credenciales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_credencial")
    private Long id;

    // Foreign Key
    @Column(name="id_experto")
    private Long idExperto;

    @Column(name="correo")
    private String credencial_correo;

    @Column(name="correo_password")
    private String credencial_correo_password;

    @Column(name="zoom")
    private String credencial_zoom;

    @Column(name="zoom_password")
    private String credencial_zoom_password;

    //RELATIONS
    @OneToOne
    @JoinColumn(name = "id_experto",  insertable = false, updatable = false)
    private Experto experto;

    //CONSTRUCTOR
    public Credenciales() {
    }

    public Credenciales(String credencial_correo, String credencial_correo_password, String credencial_zoom, String credencial_zoom_password) {
        this.credencial_correo = credencial_correo;
        this.credencial_correo_password = credencial_correo_password;
        this.credencial_zoom = credencial_zoom;
        this.credencial_zoom_password = credencial_zoom_password;
    }

    // GETTER AND SETTER
    public Long getId() {
        return id;
    }

    public Credenciales setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCredencial_correo() {
        return credencial_correo;
    }

    public Credenciales setCredencial_correo(String credencial_correo) {
        this.credencial_correo = credencial_correo;
        return this;
    }

    public String getCredencial_correo_password() {
        return credencial_correo_password;
    }

    public Credenciales setCredencial_correo_password(String credencial_correo_password) {
        this.credencial_correo_password = credencial_correo_password;
        return this;
    }

    public String getCredencial_zoom() {
        return credencial_zoom;
    }

    public Credenciales setCredencial_zoom(String credencial_zoom) {
        this.credencial_zoom = credencial_zoom;
        return this;
    }

    public String getCredencial_zoom_password() {
        return credencial_zoom_password;
    }

    public Credenciales setCredencial_zoom_password(String credencial_zoom_password) {
        this.credencial_zoom_password = credencial_zoom_password;
        return this;
    }

    @Override
    public String toString() {
        return "Credenciales{" +
                "id=" + id +
                ", credencial_correo='" + credencial_correo + '\'' +
                ", credencial_correo_password='" + credencial_correo_password + '\'' +
                ", credencial_zoom='" + credencial_zoom + '\'' +
                ", credencial_zoom_password='" + credencial_zoom_password + '\'' +
                '}';
    }
}
