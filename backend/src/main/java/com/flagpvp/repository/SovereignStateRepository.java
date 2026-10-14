package com.flagpvp.repository;

import org.springframework.data.repository.CrudRepository;

import com.flagpvp.domain.entity.SovereignState;

import java.util.Optional;

/**
 * Repository for the SovereignState entity.
 */
public interface SovereignStateRepository extends CrudRepository<SovereignState, String> {

    // Get a SovereignState entity by its name.
    Optional<SovereignState> findByName(String name);
}
