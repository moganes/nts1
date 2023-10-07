package com.spectrum.notes.repository;

import com.spectrum.notes.model.CMTS;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alam
 */
public interface CMTSRepository extends JpaRepository<CMTS, Integer> {
    CMTS findTopByDocsisNaturalOrderByInsertDateTimeDesc(String clli);
}