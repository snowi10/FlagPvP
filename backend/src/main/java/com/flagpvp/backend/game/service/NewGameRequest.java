package com.flagpvp.backend.game.service;

import com.flagpvp.backend.game.domain.entity.GameMode;
import com.flagpvp.backend.game.domain.entity.Regions;

import java.util.List;

/**
 * Creates a new game request.
 *
 * @param mode specifies a singleplayer game or a multiplayer game.
 * @param timed specifies whether the game is timed or not.
 * @param regionsList the list of regions that the game will consist of.
 */
public record NewGameRequest(
    GameMode mode,
    Boolean timed,
    List<Regions> regionsList,
    List<String> playersList
)
{}