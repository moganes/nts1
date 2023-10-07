package com.spectrum.notes.repository;

import com.spectrum.notes.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alam
 */
public interface HubRepository extends JpaRepository<Hub, Integer> {
    Hub findTopByHubIdOrderByInsertDateTimeDesc(String hubId);
}