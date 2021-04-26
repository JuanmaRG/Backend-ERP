package com.example.backenderp;

import com.example.backenderp.model.*;
import com.example.backenderp.repository.DisponibilidadRepository;
import com.example.backenderp.repository.EstadoRepository;
import com.example.backenderp.repository.ExpertoRepository;
import com.example.backenderp.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BackendErpApplication implements CommandLineRunner {

    final ExpertoRepository expertoRepository;
    final EstadoRepository estadoRepository;
    final DisponibilidadRepository disponibilidadRepository;
    final TagRepository tagRepository;

    public BackendErpApplication(ExpertoRepository expertoRepository, EstadoRepository estadoRepository, DisponibilidadRepository disponibilidadRepository, TagRepository tagRepository) {
        this.expertoRepository = expertoRepository;
        this.estadoRepository = estadoRepository;
        this.disponibilidadRepository = disponibilidadRepository;
        this.tagRepository = tagRepository;
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

        Tag tag1 = new Tag("Docker");
        Tag tag2 = new Tag("IA");
        Tag tag3 = new Tag("Oracle Cloud Infraestructure");
        Tag tag4 = new Tag("Oracle PLSQL");
        Tag tag5 = new Tag("Oracle SQL Database");
        Tag tag6 = new Tag("Oracle Fusion Midleware");
        tagRepository.save(tag1);
        tagRepository.save(tag2);
        tagRepository.save(tag4);
        tagRepository.save(tag5);
        tagRepository.save(tag6);


        Experto experto1 = new Experto("Juan Manuel", Instant.now(),Instant.now(),estado1.getId(),disponibilidad3.getId(),"Remoto","SI","952254785", "juanma@gmail.com","Malaga","Juan Manuel Ruiz Gil");
        List<Tag> tagExpert = new ArrayList<>();
        expertoRepository.save(experto1);
        experto1.setIdDisponibilidad(2L);
        expertoRepository.save(experto1);

        tagExpert.add(tag1);
        tagExpert.add(tag2);
        experto1.setTagList(tagExpert);
        tag1.getExpertoList().add(experto1);
        tagRepository.save(tag1);
        expertoRepository.save(experto1);

    }


}
