package com.flagpvp.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.flagpvp.domain.RegionName;

/**
 * The Region entity/object.
 * Players choose which regions they want to be quizzed on.
 */
@Table
public record Region(
        @Id @Column RegionName name,    // The name of the region.
        @Column Integer statesCount)    // The number of sovereign states in the region.
{
    // Getters for Region attributes.
    public RegionName getName() {
        return name;
    }
    public Integer getStatesCount() {
        return statesCount;
    }
}