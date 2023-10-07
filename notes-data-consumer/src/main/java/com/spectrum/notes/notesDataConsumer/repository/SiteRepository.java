package com.spectrum.notes.notesDataConsumer.repository;

import com.spectrum.notes.notesDataConsumer.model.Site;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author alam
 */
public interface SiteRepository extends CrudRepository<Site, UUID> {

}