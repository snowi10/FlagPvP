package com.flagpvp.backend.game.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * The game entity.
 * Game settings can be configured by players.
 */
@Table
public class Game {

    @Id
    @Column
    private Long id; // The game ID.

    @Column
    private GameMode mode; // Specifies a singleplayer game or a multiplayer game.

    @Column
    private Boolean timed; // Specifies whether the game is timed or not.

    @Column
    private Integer playerCount; // The number of players in the game.

    @Column
    private Boolean inProgress; // Specifies whether the game is in progress or not.

    // Default settings for the game.
    public Game() {
        mode = GameMode.SINGLEPLAYER;
        timed = false;
        playerCount = 1;
        inProgress = false;
    }

    // Game constructor.
    public Game(Long id, GameMode mode, Boolean timed, Integer playerCount, Boolean inProgress) {
        this.id = id;
        this.mode = mode;
        this.timed = timed;
        this.playerCount = playerCount;
        this.inProgress = inProgress;
    }

    // Setters for Game attributes.
    public void setMode(GameMode mode) {
        this.mode = mode;
    }
    public void setTimed(Boolean timed) {
        this.timed = timed;
    }
    public void setPlayerCount(Integer playerCount) {
        this.playerCount = playerCount;
    }
    public void setInProgress(Boolean inProgress) { this.inProgress = inProgress; }

    // Getters for Game attributes.
    public Long getId() { return id; }
    public GameMode getMode() {
        return mode;
    }
    public Boolean getTimed() {
        return timed;
    }
    public Integer getPlayerCount() {
        return playerCount;
    }
    public Boolean getInProgress() { return inProgress; }
}
