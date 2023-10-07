package com.spectrum.notes.repository;

import com.spectrum.notes.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alam
 */
public interface HouseRepository extends JpaRepository<House, Integer> {

}