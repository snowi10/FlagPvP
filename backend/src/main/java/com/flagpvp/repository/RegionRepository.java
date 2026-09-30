package com.flagpvp.repository;

import org.springframework.data.repository.CrudRepository;

import com.flagpvp.domain.Region;
import com.flagpvp.domain.Regions;

import java.util.Optional;

/**
 * Repository for the region.
 */
public interface RegionRepository extends CrudRepository<Region, String> {

    // Finds a region by its name.
    Optional<Region> findByName(Regions name);
}
