package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.Game;
import com.flagpvp.backend.game.domain.entity.GameMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
public class GameRepositoryTests {

    @Autowired
    GameRepository gameRepository;

    @Test
    public void shouldGetAGameThatExists() {

        // Finds a game in the database and checks for the correct settings.
        Optional<Game> game = gameRepository.findById(12345L);
        assertTrue(game.isPresent());
        assertEquals(GameMode.SINGLEPLAYER, game.get().getMode());
        assertEquals(false, game.get().getTimed());
        assertEquals(1, game.get().getPlayerCount());
        assertEquals(false, game.get().getInProgress());
    }

    @Test
    public void shouldNotGetAGameThatDoesNotExist() {

        // Gets a game that does not exist.
        Optional<Game> game = gameRepository.findById(0L);
        assertFalse(game.isPresent());
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewGameWithDefaultSettings() {

        // Creates a new game with default settings.
        Game newGame = new Game();
        gameRepository.save(newGame);

        // Checks that the created game has the default settings.
        Optional<Game> createdGame = gameRepository.findById(newGame.getId());
        assertTrue(createdGame.isPresent());
        assertEquals(GameMode.SINGLEPLAYER, createdGame.get().getMode());
        assertEquals(false, createdGame.get().getTimed());
        assertEquals(1, createdGame.get().getPlayerCount());
        assertEquals(false, createdGame.get().getInProgress());
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewGameWithSuppliedSettings() {

        // Creates a new game with supplied settings.
        Game newGame = new Game(null, GameMode.MULTIPLAYER, true, 2, true);
        gameRepository.save(newGame);

        // Checks that the created game has the supplied settings.
        Optional<Game> createdGame = gameRepository.findById(newGame.getId());
        assertTrue(createdGame.isPresent());
        assertEquals(GameMode.MULTIPLAYER, createdGame.get().getMode());
        assertEquals(true, createdGame.get().getTimed());
        assertEquals(2, createdGame.get().getPlayerCount());
        assertEquals(true, createdGame.get().getInProgress());
    }

    @Test
    @DirtiesContext
    public void shouldUpdateAGame()  {

        // Finds an existing game in the database.
        Optional<Game> game = gameRepository.findById(12345L);
        assertTrue(game.isPresent());
        assertEquals(GameMode.SINGLEPLAYER, game.get().getMode());
        assertEquals(false, game.get().getTimed());
        assertEquals(1, game.get().getPlayerCount());
        assertEquals(false, game.get().getInProgress());

        // Updates the game with new settings.
        Game updatedGame = new Game(game.get().getId(), GameMode.MULTIPLAYER, true, 2, true);
        gameRepository.save(updatedGame);

        // Checks that the game is updated with the new settings.
        game = gameRepository.findById(12345L);
        assertTrue(game.isPresent());
        assertEquals(GameMode.MULTIPLAYER, game.get().getMode());
        assertEquals(true, game.get().getTimed());
        assertEquals(2, game.get().getPlayerCount());
        assertEquals(true, game.get().getInProgress());
    }

    // TODO: Fill in this method.
    @Test
    @DirtiesContext
    public void playerCountShouldNotBeGreaterThanOneInSinglePlayerMode() {
         
    }

    @Test
    @DirtiesContext
    public void shouldDeleteAGameThatExists() {

        // Gets a game and deletes it.
        Optional<Game> game = gameRepository.findById(12345L);
        assertTrue(game.isPresent());
        gameRepository.deleteById(game.get().getId());

        // Checks that the game is deleted from the database.
        game = gameRepository.findById(12345L);
        assertFalse(game.isPresent());
    }
}