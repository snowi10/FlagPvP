package com.flagpvp.backend.player.domain.entity;

import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

/**
 * The player entity.
 */
@Table
public class Player {
    @Id @Column String id;
    @Column Long gameId;
    @Column Boolean ready;
    @Column Integer points;

    // Constructor with default values.
    public Player() {
        gameId = null;
        ready = false;
        points = 0;
    }

    // Constructor with supplied values.
    public Player(Long gameId, Boolean ready, Integer points) {
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
