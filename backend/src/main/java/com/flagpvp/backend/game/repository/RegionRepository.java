package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.Region;
import com.flagpvp.backend.game.domain.entity.Regions;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * Repository for the region.
 */
public interface RegionRepository extends CrudRepository<Region, String> {

    // Finds a region by its name.
    Optional<Region> findByName(Regions name);
}
