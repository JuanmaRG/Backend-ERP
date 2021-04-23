package com.example.backenderp.repository;

import com.example.backenderp.model.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long> {
}
