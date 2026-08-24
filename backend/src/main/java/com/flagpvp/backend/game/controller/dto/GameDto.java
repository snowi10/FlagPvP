package com.flagpvp.backend.game.controller.dto;

import com.flagpvp.backend.game.domain.entity.GameMode;
import com.flagpvp.backend.game.domain.entity.Regions;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * DTO for a game.
 *
 * @param id the ID of the game.
 * @param mode specifies a singleplayer or a multiplayer game.
 * @param timed specifies whether the game is timed or not.
 * @param regionsList specifies the regions that will be in the game.
 */
public record GameDto(
    @Nullable
    Long id,

    @NotNull
    GameMode mode,

    @NotNull
    Boolean timed,

    @NotNull
    List<Regions> regionsList,

    @NotNull
    List<String> playersList
)
{}
