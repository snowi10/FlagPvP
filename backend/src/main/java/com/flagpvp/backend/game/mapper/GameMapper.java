package com.flagpvp.backend.game.mapper;

import com.flagpvp.backend.game.controller.dto.GameDto;
import com.flagpvp.backend.game.controller.dto.NewGameRequestDto;
import com.flagpvp.backend.game.domain.entity.Game;
import com.flagpvp.backend.game.service.NewGameRequest;

/**
 * TODO: Fill in this class.
 */
public interface GameMapper {

    GameDto toDto(Game game);

    NewGameRequest fromDto(NewGameRequestDto dto);

    // UpdateGameRequest fromDto(UpdateGameRequestDto Dto);

}
