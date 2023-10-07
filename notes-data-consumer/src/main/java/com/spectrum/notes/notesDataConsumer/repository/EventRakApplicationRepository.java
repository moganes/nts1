package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.EventRakApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface EventRakApplicationRepository extends CrudRepository<EventRakApplication, UUID> {
}