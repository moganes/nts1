package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.EquipmentChassis;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface EquipmentChassisRepository extends CrudRepository<EquipmentChassis, UUID> {

}