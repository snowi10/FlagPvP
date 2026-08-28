package com.flagpvp.backend.game.service.impl;

import com.flagpvp.backend.game.domain.entity.Game;
import com.flagpvp.backend.game.repository.GameRepository;
import com.flagpvp.backend.game.service.GameService;
import com.flagpvp.backend.game.service.NewGameRequest;

/**
 * TODO: Fill in this class.
 */
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Creates a new game from the request.
    public Game createNewGame(NewGameRequest request) {
        Game newGame = new Game(null, request.mode(), request.timed(), request.playersList().size(), null);
        return gameRepository.save(newGame);
    }

    public Game getGame() {
        return null;
    }

    public void updateGameSettings() {

    }

    public void deleteGame() {

    }
}
