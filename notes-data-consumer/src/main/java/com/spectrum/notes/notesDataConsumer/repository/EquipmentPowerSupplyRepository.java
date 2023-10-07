package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.EquipmentPowerSupply;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface EquipmentPowerSupplyRepository extends CrudRepository<EquipmentPowerSupply, UUID> {

}