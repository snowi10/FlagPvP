package com.flagpvp.backend.game.controller.dto;

import com.flagpvp.backend.game.domain.entity.GameMode;
import com.flagpvp.backend.game.domain.entity.Regions;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * DTO for a new game request.
 *
 * @param mode specifies a singleplayer or a multiplayer game.
 * @param timed specifies whether the game is timed or not.
 * @param regionsList specifies the regions that will be in the game.
 */
public record NewGameRequestDto(

        @NotNull
        GameMode mode,

        @AssertFalse
        Boolean timed,

        @NotEmpty
        List<Regions> regionsList,

        @NotEmpty
        List<String> playersList
)
{
        // Default settings for creating a new game.
        public NewGameRequestDto(String[] players) {
                this(GameMode.SINGLEPLAYER, false, allRegionsToList(), Arrays.asList(players));
        }

        // Defaults to all regions for the game.
        private static List<Regions> allRegionsToList() {
                Regions[] allRegions = new Regions[]{Regions.AMERICAS, Regions.EUROPE,
                        Regions.AFRICA, Regions.ASIA, Regions.OCEANIA};
                return Arrays.asList(allRegions);
        }
}
