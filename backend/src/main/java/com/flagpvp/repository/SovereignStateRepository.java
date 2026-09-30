package com.flagpvp.repository;

import org.springframework.data.repository.CrudRepository;

import com.flagpvp.domain.SovereignState;

import java.util.Optional;

/**
 * Repository for sovereign state.
 */
public interface SovereignStateRepository extends CrudRepository<SovereignState, String> {

    // Finds a sovereign state by name.
    Optional<SovereignState> findByName(String name);
}
