package com.flagpvp.dao;

import com.flagpvp.domain.entity.Region;
import com.flagpvp.domain.RegionName;

/**
 * DAO interface for the Region entity.
 */
public interface RegionDao {

    // Get a Region entity by its name.
    Region findByName(RegionName name);
}