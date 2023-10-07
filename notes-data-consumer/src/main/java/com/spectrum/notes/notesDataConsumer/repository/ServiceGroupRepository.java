package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.ServiceGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface ServiceGroupRepository extends CrudRepository<ServiceGroup, UUID> {

}