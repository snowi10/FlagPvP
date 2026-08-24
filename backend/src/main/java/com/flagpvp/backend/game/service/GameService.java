package com.flagpvp.backend.game.service;

import com.flagpvp.backend.game.domain.entity.Game;
import org.springframework.stereotype.Service;

/**
 * TODO: Fill in this class.
 */
@Service
public interface GameService {
    Game createNewGame(NewGameRequest request);

    void updateGameSettings();

    void deleteGame();
}
