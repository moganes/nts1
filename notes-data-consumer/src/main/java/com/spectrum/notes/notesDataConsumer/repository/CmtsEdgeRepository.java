package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.edge.CMTSEdge;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface CmtsEdgeRepository extends CrudRepository<CMTSEdge, UUID> {

}