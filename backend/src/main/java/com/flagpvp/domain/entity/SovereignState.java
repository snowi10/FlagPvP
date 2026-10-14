package com.flagpvp.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.flagpvp.domain.RegionName;

/**
 * The SovereignState entity/object.
 * Each state is part of a region.
 */
@Table
public record SovereignState(
        @Id @Column String name,    // The name of the sovereign state.
        @Column RegionName region,  // The region that the sovereign state is in.
        @Column String flagUrl)     // The image URL of the flag. 
{

    // Getters for SovereignState attributes.
    public String getName() {
        return name;
    }
    public RegionName getRegion() {
        return region;
    }
    public String getFlagUrl() {
        return flagUrl;
    }
}
