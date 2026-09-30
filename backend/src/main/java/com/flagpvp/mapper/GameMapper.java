package com.flagpvp.mapper;

import com.flagpvp.dto.GameDto;
import com.flagpvp.dto.NewGameRequestDto;
import com.flagpvp.domain.Game;
import com.flagpvp.service.NewGameRequest;

/**
 * TODO: Fill in this class.
 */
public interface GameMapper {

    GameDto toDto(Game game);

    NewGameRequest fromDto(NewGameRequestDto dto);

    // UpdateGameRequest fromDto(UpdateGameRequestDto Dto);

}
