package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.BPSApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface BPSApplicationRepository extends CrudRepository<BPSApplication, UUID> {

}