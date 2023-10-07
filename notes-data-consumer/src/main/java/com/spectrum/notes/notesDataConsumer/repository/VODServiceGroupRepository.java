package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.VODServiceGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface VODServiceGroupRepository extends CrudRepository<VODServiceGroup, UUID> {

}