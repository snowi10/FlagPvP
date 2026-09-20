package com.flagpvp.game.mapper;

import com.flagpvp.game.controller.dto.GameDto;
import com.flagpvp.game.controller.dto.NewGameRequestDto;
import com.flagpvp.game.domain.entity.Game;
import com.flagpvp.game.service.NewGameRequest;

/**
 * TODO: Fill in this class.
 */
public interface GameMapper {

    GameDto toDto(Game game);

    NewGameRequest fromDto(NewGameRequestDto dto);

    // UpdateGameRequest fromDto(UpdateGameRequestDto Dto);

}
