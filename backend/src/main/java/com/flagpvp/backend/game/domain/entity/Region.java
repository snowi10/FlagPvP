package com.flagpvp.backend.game.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The region entity.
 * Players choose which regions they want to be quizzed on.
 *
 * @param name the name of the region.
 * @param statesCount the number of sovereign states in the region.
 */
@Table
public record Region(
        @Id @Column Regions name,
        @Column Integer statesCount)
{
    // Getters for Region attributes.
    public Regions getName() {
        return name;
    }
    public Integer getStatesCount() {
        return statesCount;
    }
}