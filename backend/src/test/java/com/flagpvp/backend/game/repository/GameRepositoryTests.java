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

        // Finds a game in the repository.
        Optional<Game> game = findById(12345L);
        assertTrue(game.isPresent());

        // Checks for the correct settings.
        Game gameObj = game.get();
        assertEquals(GameMode.SINGLEPLAYER, gameObj.getMode());
        assertEquals(false, gameObj.getTimed());
        assertEquals(1, gameObj.getPlayerCount());
        assertEquals(false, gameObj.getInProgress());
    }

    @Test
    public void shouldNotGetAGameThatDoesNotExist() {

        // Gets a game that does not exist.
        Optional<Game> game = findById(0L);
        assertFalse(game.isPresent());
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewGameWithDefaultSettings() {

        // Creates a new game with default settings.
        Game newGame = new Game();
        saveGame(newGame);

        // Checks that the game exists in the repository.
        Optional<Game> createdGame = findById(newGame.getId());
        assertTrue(createdGame.isPresent());

        // Checks that the created game has the default settings.
        Game createdGameObj = createdGame.get();
        assertEquals(GameMode.SINGLEPLAYER, createdGameObj.getMode());
        assertEquals(false, createdGameObj.getTimed());
        assertEquals(1, createdGameObj.getPlayerCount());
        assertEquals(false, createdGameObj.getInProgress());
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewGameWithSuppliedSettings() {

        // Creates a new game with supplied settings.
        Game newGame = new Game(null, GameMode.MULTIPLAYER, true, 2, true);
        saveGame(newGame);

        // Checks that the created game exists in the repository.
        Optional<Game> createdGame = findById(newGame.getId());
        assertTrue(createdGame.isPresent());

        // Checks that the created game has the supplied settings.
        Game createdGameObj = createdGame.get();
        assertEquals(GameMode.MULTIPLAYER, createdGameObj.getMode());
        assertEquals(true, createdGameObj.getTimed());
        assertEquals(2, createdGameObj.getPlayerCount());
        assertEquals(true, createdGameObj.getInProgress());
    }

    @Test
    @DirtiesContext
    public void shouldUpdateAGame()  {

        // Finds an existing game in the repository.
        Optional<Game> game = findById(12345L);
        assertTrue(game.isPresent());

        // Checks that the game has the correct settings. 
        Game gameObj = game.get();
        assertEquals(GameMode.SINGLEPLAYER, gameObj.getMode());
        assertEquals(false, gameObj.getTimed());
        assertEquals(1, gameObj.getPlayerCount());
        assertEquals(false, gameObj.getInProgress());

        // Updates the game with new settings.
        Game updatedGame = new Game(game.get().getId(), GameMode.MULTIPLAYER, true, 2, true);
        saveGame(updatedGame);

        // Checks that the updated game exists. 
        game = findById(12345L);
        assertTrue(game.isPresent());

        // Checks that the updated game has the new settings.
        gameObj = game.get();
        assertEquals(GameMode.MULTIPLAYER, gameObj.getMode());
        assertEquals(true, gameObj.getTimed());
        assertEquals(2, gameObj.getPlayerCount());
        assertEquals(true, gameObj.getInProgress());
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
        Optional<Game> game = findById(12345L);
        assertTrue(game.isPresent());
        gameRepository.deleteById(game.get().getId());

        // Checks that the game is deleted from the repository.
        game = findById(12345L);
        assertFalse(game.isPresent());
    }

    // Method to refactor PlayerRepository save() method.
    private Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    // Method to refactor PlayerRepository findById() method. 
    private Optional<Game> findById(Long id) {
        return  gameRepository.findById(id);
    }
}