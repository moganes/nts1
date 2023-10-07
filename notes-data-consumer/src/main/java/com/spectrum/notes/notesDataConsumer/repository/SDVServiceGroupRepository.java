package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.SDVServiceGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface SDVServiceGroupRepository extends CrudRepository<SDVServiceGroup, UUID> {

}