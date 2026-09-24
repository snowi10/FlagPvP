package com.flagpvp.sovereignstate.repository;

import org.springframework.data.repository.CrudRepository;

import com.flagpvp.sovereignstate.domain.entity.SovereignState;

import java.util.Optional;

/**
 * Repository for sovereign state.
 */
public interface SovereignStateRepository extends CrudRepository<SovereignState, String> {

    // Finds a sovereign state by name.
    Optional<SovereignState> findByName(String name);
}
