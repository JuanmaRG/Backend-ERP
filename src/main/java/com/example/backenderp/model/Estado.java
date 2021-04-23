package com.example.backenderp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="ESTADO")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estado")
    private Long id;

    private String valor;

    // RELATIONS
    @OneToMany(mappedBy = "estado")
    private List<Experto> listaExpertos = new ArrayList<>();

    public Estado() {
    }

    public Estado(String valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public Estado setId(Long id) {
        this.id = id;
        return this;
    }

    public String getValor() {
        return valor;
    }

    public Estado setValor(String valor) {
        this.valor = valor;
        return this;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", valor='" + valor + '\'' +
                '}';
    }
}
