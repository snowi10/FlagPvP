package com.flagpvp.dao;

import com.flagpvp.domain.entity.SovereignState;

/**
 * DAO interface for the SovereignState entity. 
 */
public interface SovereignStateDao {

    // Get a SovereignState entity by its name.
    SovereignState findByName(String name);
}
