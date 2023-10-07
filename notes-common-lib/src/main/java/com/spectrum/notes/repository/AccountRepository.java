package com.spectrum.notes.repository;

import com.spectrum.notes.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author alam
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {

}