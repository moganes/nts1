package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.QPSKDemod;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface QPSKDemodRepository extends CrudRepository<QPSKDemod, UUID> {

}