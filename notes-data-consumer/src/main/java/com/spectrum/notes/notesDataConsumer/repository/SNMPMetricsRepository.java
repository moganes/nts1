package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.SNMPMonMetricsFlat;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface SNMPMetricsRepository extends CrudRepository<SNMPMonMetricsFlat, UUID> {

}