package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.edge.CableModemEdge;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface CableModemEdgeRepository extends CrudRepository<CableModemEdge, UUID> {

}