package com.flagpvp.backend.game.domain.entity;

import org.springframework.data.relational.core.mapping.Column;

/**
 * Composite primary key class for GameRegion.
 */
public record GameRegionId(
        @Column Long gameId,
        @Column Regions region)
{
    public Long getGameId() {
        return gameId;
    }
    public Regions getRegion() {
        return region;
    }
}
