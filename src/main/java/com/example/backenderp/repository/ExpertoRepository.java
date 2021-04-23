package com.example.backenderp.repository;

import com.example.backenderp.model.Experto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertoRepository extends JpaRepository<Experto, Long> {
}
