package com.example.backenderp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="EXPERTO")
public class Experto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experto")
    private Long id;

    private String nombre;

    private Instant created_at;

    private Instant updated_at;

    // Foreign Key
    @Column(name="id_estado")
    private Long idEstado;

    // Foreign Key
    @Column(name="id_disponibilidad")
    private Long idDisponibilidad;

    private String modalidad;

    private String autonomo;

    @Column(name="telefono")
    private String contacto_telefono;

    @Column(name="email")
    private String contacto_email;

    @Column(name="ciudad")
    private String contacto_ciudad;

    @Column(name="linkedin")
    private String contacto_linkedin;

    // RELATIONS
    @ManyToOne
    @JoinColumn(name ="id_estado", insertable = false, updatable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name ="id_disponibilidad", insertable = false, updatable = false)
    private Disponibilidad disponibilidad;

    /*
    @OneToMany(mappedBy = "etiqueta")
    private List<ExpertoTag> etiquetas = new ArrayList<>();
    */

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "expert_tag",
            joinColumns = {@JoinColumn(name = "expert_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    @JsonIgnoreProperties("expertoList")
    private List<Tag> tagList= new ArrayList<>();

    @OneToOne(mappedBy = "experto")
    private Credenciales credenciales;

    @OneToOne(mappedBy = "experto")
    private Fichero fichero;

    // CONSTRUCTOR
    public Experto() {
    }

    public Experto(String nombre, Instant created_at, Instant updated_at, Long idEstado, Long idDisponibilidad, String modalidad, String autonomo, String contacto_telefono, String contacto_email, String contacto_ciudad, String contacto_linkedin) {
        this.nombre = nombre;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.idEstado = idEstado;
        this.idDisponibilidad = idDisponibilidad;
        this.modalidad = modalidad;
        this.autonomo = autonomo;
        this.contacto_telefono = contacto_telefono;
        this.contacto_email = contacto_email;
        this.contacto_ciudad = contacto_ciudad;
        this.contacto_linkedin = contacto_linkedin;
    }

    //GETTER AND SETTER


    public Long getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public Experto setIdDisponibilidad(Long idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
        return this;
    }

    public Estado getEstado() {
        return estado;
    }

    public Experto setEstado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public Experto setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
        return this;
    }

    public Long getId_Estado() {
        return idEstado;
    }

    public Experto setId_Estado(Long id_Estado) {
        this.idEstado = id_Estado;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Experto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Experto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Instant getCreated_at() {
        return created_at;
    }

    public Experto setCreated_at(Instant created_at) {
        this.created_at = created_at;
        return this;
    }

    public Instant getUpdated_at() {
        return updated_at;
    }

    public Experto setUpdated_at(Instant updated_at) {
        this.updated_at = updated_at;
        return this;
    }

    public String getModalidad() {
        return modalidad;
    }

    public Experto setModalidad(String modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public String getAutonomo() {
        return autonomo;
    }

    public Experto setAutonomo(String autonomo) {
        this.autonomo = autonomo;
        return this;
    }

    public String getContacto_telefono() {
        return contacto_telefono;
    }

    public Experto setContacto_telefono(String contacto_telefono) {
        this.contacto_telefono = contacto_telefono;
        return this;
    }

    public String getContacto_email() {
        return contacto_email;
    }

    public Experto setContacto_email(String contacto_email) {
        this.contacto_email = contacto_email;
        return this;
    }

    public String getContacto_ciudad() {
        return contacto_ciudad;
    }

    public Experto setContacto_ciudad(String contacto_ciudad) {
        this.contacto_ciudad = contacto_ciudad;
        return this;
    }

    public String getContacto_linkedin() {
        return contacto_linkedin;
    }

    public Experto setContacto_linkedin(String contacto_linkedin) {
        this.contacto_linkedin = contacto_linkedin;
        return this;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public Experto setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
        return this;
    }


    public Credenciales getCredenciales() {
        return credenciales;
    }

    public Experto setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
        return this;
    }

    public Fichero getFichero() {
        return fichero;
    }

    public Experto setFichero(Fichero fichero) {
        this.fichero = fichero;
        return this;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public Experto setTagList(List<Tag> tagList) {
        this.tagList = tagList;
        return this;
    }

    @Override
    public String toString() {
        return "Experto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", idEstado=" + idEstado +
                ", idDisponibilidad=" + idDisponibilidad +
                ", modalidad='" + modalidad + '\'' +
                ", autonomo='" + autonomo + '\'' +
                ", contacto_telefono='" + contacto_telefono + '\'' +
                ", contacto_email='" + contacto_email + '\'' +
                ", contacto_ciudad='" + contacto_ciudad + '\'' +
                ", contacto_linkedin='" + contacto_linkedin + '\'' +
                ", estado=" + estado +
                ", disponibilidad=" + disponibilidad +
                '}';
    }
}
