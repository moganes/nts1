package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.edge.EquipmentEntityEdge;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface EquipmentEntityEdgeRepository extends CrudRepository<EquipmentEntityEdge, UUID> {

}