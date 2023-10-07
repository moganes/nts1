package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.CPE;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface CPERepository extends CrudRepository<CPE, UUID> {

}