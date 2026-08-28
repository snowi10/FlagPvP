package com.flagpvp.backend.game.controller;

import com.flagpvp.backend.game.domain.entity.Game;
import com.flagpvp.backend.game.repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

/**
 * Handles HTTP requests for configuring
 * the game settings.
 *
 * TODO: Update this class in conjunction with the GameSerivce interface.
 */
@RestController
@RequestMapping("/gamesettings")
public class GameController {

    // The repository that will be used to save the game settings.
    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Gets the specified game.
     *
     * @param id the ID of the specified game.
     * @return a response entity with the specified game or a response entity
     * with a game not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameSettings(@PathVariable Long id) {

        // Finds the game by the ID.
        Optional<Game> gameSettings = gameRepository.findById(id);

        // Checks if the game exists.
        if (gameSettings.isPresent()) {
            return ResponseEntity.ok(gameSettings.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Creates the game settings based on the
     * parameters or supplies the default settings.
     *
     * @param newGame the requested settings for the game.
     * @param ucb builds the URI for the saved game settings.
     * @return a response entity with the location of the newly created game.
     */
    @PostMapping
    public ResponseEntity<Void> createNewGame(@RequestBody Game newGame,
                                                      UriComponentsBuilder ucb) {

        // Saves the new game settings in the database.
        Game savedGameSettings = gameRepository.save(newGame);

        // Builds the URI location of the game.
        URI locationOfNewGameSettings = ucb
                .path("gamesettings/{id}")
                .buildAndExpand(savedGameSettings.getId())
                .toUri();

        return ResponseEntity.created(locationOfNewGameSettings).build();
    }

    /**
     * Updates the game settings based on user configurations.
     *
     * @param id the ID of the game server that will be updated.
     * @param newGameSettings supplies the new settings for the game.
     * @return a response entity that accepts the new game settings or
     * states an unprocessable content if the settings could not be updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGameSettings(@PathVariable Long id,
                                                   @RequestBody Game newGameSettings) {

        // Finds the game by the ID.
        Optional<Game> retrievedGameSettings = gameRepository.findById(id);

        // Checks if the game exists.
        if (retrievedGameSettings.isPresent()) {

            // Updates the game with the new settings and
            // saves it to the repository.
            Game updatedGameSettings = new Game(retrievedGameSettings.get().getId(),
                    newGameSettings.getMode(), newGameSettings.getTimed(), newGameSettings.getPlayerCount(),
                    newGameSettings.getInProgress());
            gameRepository.save(updatedGameSettings);

            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.unprocessableContent().build();
    }

    /**
     * Deletes a game.
     *
     * @param id the ID of the game to be deleted.
     * @return a response entity that accepts the deletion of the game or
     * is not found if the game could not be retrieved from the database.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameSettings(@PathVariable Long id) {

        // Finds the game by the ID.
        Optional<Game> retrievedGameSettings = gameRepository.findById(id);

        // Checks if the game exists.
        if (retrievedGameSettings.isPresent()) {

            // Deletes the game.
            gameRepository.deleteById(id);
            return ResponseEntity.accepted().build();
        }

        return ResponseEntity.notFound().build();
    }
}