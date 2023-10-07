package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.House;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface HouseRepository extends CrudRepository<House, UUID> {

}