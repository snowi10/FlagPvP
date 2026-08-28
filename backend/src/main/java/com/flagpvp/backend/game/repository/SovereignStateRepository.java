package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.SovereignState;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository for sovereign state.
 */
public interface SovereignStateRepository extends CrudRepository<SovereignState, String> {

    // Finds a sovereign state by name.
    Optional<SovereignState> findByName(String name);
}
