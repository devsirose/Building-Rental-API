package com.example.dvdrental;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest
class DvdrentalApplicationTests {
    @Autowired
    ActorRepository actorRepository;
    @Autowired
    IFilmRepository filmRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void getListActorByFirstNameStartingWith() {
        List<Actor> actors = actorRepository.findActorByFirstNameStartingWith("L");
        Function<Actor, String> function = (Actor actor) -> {
            return actor.getFirstName();
        };
        System.out.println(actors.stream().map(function).collect(Collectors.toList()));
    }

    @Test
    void updateListActorByIdBetween() {
        Long idFrom = Long.valueOf("1");
        Long idEnd = Long.valueOf("10");
        System.out.println(actorRepository.updateActorsByIdBetweenSet(idFrom, idEnd, "Sirose"));
    }

    @Test
    @Transactional
    void saveActorAndFilm() {
        Film film = Film.builder().title("Star War").description("Phim chien tranh").language(Language.builder().languageName("Vietnamese").lastUpdate(Timestamp.valueOf(LocalDateTime.now())).build()).build();
        filmRepository.save(film);
    }
}
