package com.flagpvp.service;

import org.springframework.stereotype.Service;

import com.flagpvp.domain.Game;

/**
 * TODO: Fill in this class.
 */
@Service
public interface GameService {
    Game createNewGame(NewGameRequest request);

    void updateGameSettings();

    void deleteGame();
}
