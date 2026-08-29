package com.flagpvp.backend.player.domain.entity;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

/**
 * The player entity.
 */
@Table
public class Player {
    @Id @Column private String id; // The player ID.
    @Column private Long  gameId; // The game ID that the player is currently in.
    @Column private Boolean ready; // Specifies whether the player is ready to play or not.
    @Column private Integer points; // Current points that the player has in a game.

    // Constructor with default values.
    public Player() {
        ready = false;
        points = 0;
    }

    // Constructor with supplied values.
    public Player(String id, Long gameId, Boolean ready, Integer points) {
        this.id = id;
        this.gameId = gameId;
        this.ready = ready;
        this.points = points;
    }

    // Setters for Player attributes.
    public void setId(String id) { this.id = id; }
    public void setGameId(Long gameId) { this.gameId = gameId; }
    public void setReady(Boolean ready) { this.ready = ready; }
    public void setPoints(Integer points) { this.points = points; }

    // Getters for Player attributes.
    public String getId() { return id; }
    public Long getGameId() { return gameId; }
    public Boolean getReady() { return ready; }
    public Integer getPoints() { return points; }

}