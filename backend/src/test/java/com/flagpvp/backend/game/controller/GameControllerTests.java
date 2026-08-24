package com.flagpvp.backend.game.controller;

import com.flagpvp.backend.game.domain.entity.Game;
import com.flagpvp.backend.game.domain.entity.GameMode;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;


import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class GameControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    // START OF TESTING GET METHOD.
    @Test
    void shouldGetAnExistingGameSettings() {

        // Gets an existing game.
        ResponseEntity<String> response = restTemplate.getForEntity("/gamesettings/12345", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Checks that the game has the correct settings.
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Integer id = documentContext.read("$.id");
        String mode = documentContext.read("$.mode");
        Boolean timed = documentContext.read("$.timed");
        Integer playerCount = documentContext.read("$.playerCount");

        assertThat(id).isEqualTo(12345);
        assertThat(mode).isEqualTo("SINGLEPLAYER");
        assertThat(timed).isEqualTo(false);
        assertThat(playerCount).isEqualTo(1);
    }

    @Test
    void shouldNotGetAGameSettingsThatDoesNotExist() {

        // Gets a non-existent game
         ResponseEntity<String> unknownGame = restTemplate.getForEntity("/gamesettings/6789", String.class);
         assertThat(unknownGame.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
         assertThat(unknownGame.getBody()).isBlank();
    }

    // START OF TESTING POST METHOD.
    @Test
    @DirtiesContext
    void shouldCreateANewGameWithSuppliedSettings() {

        // Creates new game with supplied settings.
        Game newGameSettings = new Game();
        newGameSettings.setMode(GameMode.MULTIPLAYER);
        newGameSettings.setTimed(true);
        newGameSettings.setPlayerCount(2);

        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/gamesettings", newGameSettings,
                Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Checks that the game settings were created and saved.
        URI locationOfNewGameSettings = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewGameSettings, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Integer id = documentContext.read("$.id");
        String mode = documentContext.read("$.mode");
        Boolean timed = documentContext.read("$.timed");
        Integer playerCount = documentContext.read("$.playerCount");

        assertThat(id).isNotNull();
        assertThat(mode).isEqualTo("MULTIPLAYER");
        assertThat(timed).isEqualTo(true);
        assertThat(playerCount).isEqualTo(2);
    }

    @Test
    @DirtiesContext
    void shouldCreateANewGameWithDefaultSettings() {

        // Creates a new game with no values set (should
        // supply default settings).
        Game newGameSettings = new Game();

        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/gamesettings", newGameSettings,
                Void.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        // Checks that the game settings are the default values.
        URI locationOfNewGameSettings = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewGameSettings, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Integer id = documentContext.read("$.id");
        String mode = documentContext.read("$.mode");
        Boolean timed = documentContext.read("$.timed");
        Integer playerCount = documentContext.read("$.playerCount");

        assertThat(id).isNotNull();
        assertThat(mode).isEqualTo("SINGLEPLAYER");
        assertThat(timed).isEqualTo(false);
        assertThat(playerCount).isEqualTo(1);

    }

    // START OF TESTING PUT METHOD.
    @Test
    @DirtiesContext
    void shouldUpdateTheSettingsOfAnExistingGame() {

        // Specifies new settings for the game.
        Game newGameSettings = new Game();
        newGameSettings.setMode(GameMode.MULTIPLAYER);
        newGameSettings.setTimed(true);
        newGameSettings.setPlayerCount(2);

        // Updates the game with the new settings.
        HttpEntity<Game> request = new HttpEntity<>(newGameSettings);
        ResponseEntity<Void> response = restTemplate.exchange("/gamesettings/12345", HttpMethod.PUT,
                request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        // Checks that the game has the updated settings.
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/gamesettings/12345", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Integer id = documentContext.read("$.id");
        String mode = documentContext.read("$.mode");
        Boolean timed = documentContext.read("$.timed");
        Integer playerCount = documentContext.read("$.playerCount");

        assertThat(id).isEqualTo(12345);
        assertThat(mode).isEqualTo("MULTIPLAYER");
        assertThat(timed).isEqualTo(true);
        assertThat(playerCount).isEqualTo(2);
    }

    @Test
    void shouldNotUpdateAGameThatDoesNotExist() {

        // Creates new game settings.
        Game newGameSettings = new Game();
        newGameSettings.setMode(GameMode.MULTIPLAYER);
        newGameSettings.setTimed(true);
        newGameSettings.setPlayerCount(100);

        // Attempts to update a non-existent game.
        HttpEntity<Game> request = new HttpEntity<>(newGameSettings);
        ResponseEntity<Void> response = restTemplate.exchange("/gamesettings/666", HttpMethod.PUT,
                request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_CONTENT);
    }

    // START OF TESTING DELETE METHOD.
    @Test
    @DirtiesContext
    void shouldDeleteAnExistingGameSettings() {

        // Deletes the specified game settings.
        ResponseEntity<Void> response = restTemplate.exchange("/gamesettings/12345", HttpMethod.DELETE,
                null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);

        // Checks that the game was deleted.
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/gamesettings/12345", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldNotDeleteAGameSettingsThatDoesNotExist() {

        // Attempts to delete a non-existent game.
        ResponseEntity<Void> response = restTemplate.exchange("/gamesettings/666", HttpMethod.DELETE,
                null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}