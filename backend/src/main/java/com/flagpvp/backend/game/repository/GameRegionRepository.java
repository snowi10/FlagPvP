package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.GameRegion;
import com.flagpvp.backend.game.domain.entity.GameRegionId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository for game region.
 */
public interface GameRegionRepository extends CrudRepository<GameRegion, GameRegionId> {

    // Finds a GameRegion by the game ID and the region.
    Optional<GameRegion> findByGameRegionId(GameRegionId id);

    // Deletes a GameRegion by the game ID and the region.
    void deleteByGameRegionId(GameRegionId id);
}