package com.flagpvp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The sovereign state entity.
 * Each state is part of a region.
 *
 * @param name the name of the sovereign state.
 * @param region the region that the sovereign state is in.
 * @param flagUrl the URL of the image
 */
@Table
public record SovereignState(
        @Id @Column String name,
        @Column Regions region,
        @Column String flagUrl)
{

    // Getters for SovereignState attributes.
    public String getName() {
        return name;
    }
    public Regions getRegion() {
        return region;
    }
    public String getFlagUrl() {
        return flagUrl;
    }
}
