package com.flagpvp.dao;

import java.util.List;

import com.flagpvp.domain.RegionName;
import com.flagpvp.domain.entity.SovereignState;

/**
 * DAO interface for the SovereignState entity. 
 */
public interface SovereignStateDao {

    // Get a SovereignState entity by its name.
    SovereignState findByName(String name);

    // Get a list of SovereignState entities by regions.
    List<SovereignState> findByRegions(List<RegionName> regionList);

}
