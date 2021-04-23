package com.example.backenderp;

import com.example.backenderp.model.Disponibilidad;
import com.example.backenderp.model.Estado;
import com.example.backenderp.model.Experto;
import com.example.backenderp.repository.DisponibilidadRepository;
import com.example.backenderp.repository.EstadoRepository;
import com.example.backenderp.repository.ExpertoRepository;
import com.example.backenderp.service.DisponibilidadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class BackendErpApplication implements CommandLineRunner {

    final ExpertoRepository expertoRepository;
    final EstadoRepository estadoRepository;
    final DisponibilidadRepository disponibilidadRepository;

    public BackendErpApplication(ExpertoRepository expertoRepository, EstadoRepository estadoRepository, DisponibilidadRepository disponibilidadRepository) {
        this.expertoRepository = expertoRepository;
        this.estadoRepository = estadoRepository;
        this.disponibilidadRepository = disponibilidadRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(BackendErpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Estado estado1 = new Estado("Verificado");
        Estado estado2 = new Estado("Pendiente");
        Estado estado3 = new Estado("Descartado");
        estadoRepository.save(estado1);
        estadoRepository.save(estado2);
        estadoRepository.save(estado3);

        Disponibilidad disponibilidad1 = new Disponibilidad("Mañanas");
        Disponibilidad disponibilidad2= new Disponibilidad("Tardes");
        Disponibilidad disponibilidad3 = new Disponibilidad("Completo");
        Disponibilidad disponibilidad4 = new Disponibilidad("Findes");
        disponibilidadRepository.save(disponibilidad1);
        disponibilidadRepository.save(disponibilidad2);
        disponibilidadRepository.save(disponibilidad3);
        disponibilidadRepository.save(disponibilidad4);

        Experto experto1 = new Experto("Juan Manuel", Instant.now(),Instant.now(),estado1.getId(),disponibilidad3.getId(),"desconocido","SI","952254785", "juanma@gmail.com","Malaga","Juan Manuel Ruiz Gil");
        expertoRepository.save(experto1);

    }


}
