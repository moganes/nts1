package com.spectrum.notes.notesDataConsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author alam
 */
@SpringBootApplication
//@ComponentScan(basePackages = "com.spectrum.notes.notesDataConsumer.*")
@EnableJpaRepositories(basePackages = {"com.spectrum.notes.notesDataConsumer.repository"})
@EntityScan("com.spectrum.notes.notesDataConsumer.model")
@Slf4j
public class NotesDataConsumer {

    public static void main(String[] args) {
        SpringApplication.run(NotesDataConsumer.class, args);
    }
}