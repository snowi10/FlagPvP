package com.flagpvp.backend.game.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The sovereign state entity.
 * Each state is part of a region.
 *
 * @param name the name of the sovereign state.
 * @param region the region that the sovereign state is in.
 */
@Table
public record SovereignState(
        @Id @Column String name,
        @Column Regions region)
{

    // Getters for SovereignState attributes.
    public String getName() {
        return name;
    }
    public Regions getRegion() {
        return region;
    }
}
