package com.example.backenderp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="DISPONIBILIDAD")
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_disponibilidad")
    private Long id;

    private String turno;

    //RELATIONS
    @OneToMany(mappedBy = "disponibilidad")
    private List<Experto> listaExpertos = new ArrayList<>();

    // CONSTRUCTOR

    public Disponibilidad() {
    }

    public Disponibilidad(String turno) {
        this.turno = turno;
    }
    //GETTER Y SETTER


    public Long getId() {
        return id;
    }

    public Disponibilidad setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTurno() {
        return turno;
    }

    public Disponibilidad setTurno(String turno) {
        this.turno = turno;
        return this;
    }
    /*
    @Override
    public String toString() {
        return "Disponibilidad{" +
                "id=" + id +
                ", turno='" + turno + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "Disponibilidad{" +
                "id=" + id +
                ", turno='" + turno + '\'' +
                '}';
    }
}
