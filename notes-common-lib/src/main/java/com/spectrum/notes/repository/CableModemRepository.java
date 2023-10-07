package com.spectrum.notes.repository;

import com.spectrum.notes.model.CableModem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author alam
 */
public interface CableModemRepository extends JpaRepository<CableModem, Integer> {

    @Query(value = "WITH each_cm as(SELECT cm.*, ROW_NUMBER() OVER(PARTITION BY id ORDER BY insert_date_time DESC) as n FROM cable_modem as cm WHERE cm.cmts_name like ?1) SELECT * from each_cm WHERE n = 1",
            nativeQuery = true)
    List<CableModem> findTopNByCmtsId(String clli);
}