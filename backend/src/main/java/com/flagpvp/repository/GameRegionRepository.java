package com.flagpvp.repository;

import org.springframework.data.repository.CrudRepository;

import com.flagpvp.domain.GameRegion;
import com.flagpvp.domain.GameRegionId;

import java.util.Optional;

/**
 * Repository for game-region.
 */
public interface GameRegionRepository extends CrudRepository<GameRegion, GameRegionId> {

    // Finds a GameRegion by the game ID and the region.
    Optional<GameRegion> findByGameRegionId(GameRegionId id);

    // Deletes a GameRegion by the game ID and the region.
    void deleteByGameRegionId(GameRegionId id);
}