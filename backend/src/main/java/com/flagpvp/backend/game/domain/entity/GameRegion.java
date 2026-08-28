package com.flagpvp.backend.game.domain.entity;

import com.flagpvp.backend.game.repository.GameRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Intersection table for specifying the games and
 * the regions that will be included in the games.
 *
 * TODO: Implement 'Version' to create entities in the database
 *  instead of updating them.
 */
@Table
public class GameRegion {

    @Id
    @Embedded.Nullable
    private GameRegionId gameRegionId; // The ID of the game.
    @Version
    Integer version; // Version for inserting entities with a new ID.


    // Constructor requires the game ID and the region.
    public GameRegion(GameRegionId gameRegionId) {
        this.gameRegionId = gameRegionId;
    }

    // Getters for GameRegion attributes.
    public GameRegionId getGameRegionId() {
        return gameRegionId;
    }

    public void setGameRegionId(GameRegionId gameRegionId) {
        this.gameRegionId = gameRegionId;
    }

    public String toString() {
        return "Game ID is " + gameRegionId.getGameId() + " and Region is " + gameRegionId.getRegion();
    }
}