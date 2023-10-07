package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.edge.InterfaceEdge;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface InterfaceEdgeRepository extends CrudRepository<InterfaceEdge, UUID> {

}