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

}
