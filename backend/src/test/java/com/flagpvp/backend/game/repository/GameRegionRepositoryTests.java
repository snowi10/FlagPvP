package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.GameRegion;
import com.flagpvp.backend.game.domain.entity.GameRegionId;
import com.flagpvp.backend.game.domain.entity.Regions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
public class GameRegionRepositoryTests {

    @Autowired
    GameRegionRepository gameRegionRepository;

    @Test
    public void shouldGetAGameRegionThatExists() {

        // The ID that will be used to find the GameRegion.
        GameRegionId id = new GameRegionId(12345L, Regions.AMERICAS);

        // Gets an existing game with the AMERICAS region.
        Optional<GameRegion> gameRegion = findById(id);
        assertTrue(gameRegion.isPresent());
        
        // Checks that all values are correct for the AMERICAS region.
        GameRegion gameRegionObj = gameRegion.get();
        assertEquals(12345, gameRegionObj.getGameRegionId().getGameId());
        assertEquals(Regions.AMERICAS, gameRegionObj.getGameRegionId().getRegion());

        // Gets an existing game with the ASIA region.
        id = new GameRegionId(12345L, Regions.ASIA);
        gameRegion = findById(id);

        // Checks that all the settings are correct for the ASIA region.
        gameRegionObj = gameRegion.get();
        assertTrue(gameRegion.isPresent());
        assertEquals(Regions.ASIA, gameRegionObj.getGameRegionId().getRegion());
    }

    @Test
    public void shouldNotGetAGameRegionThatDoesNotExist() {

        // An ID that does not exist.
        GameRegionId id = new GameRegionId(0L, Regions.EUROPE);

        // Checks that the GameRegion does not exist.
        Optional<GameRegion> gameRegion = findById(id);
        assertFalse(gameRegion.isPresent());

        // An ID that exists but not with the specified region.
        id = new GameRegionId(12345L, Regions.OCEANIA);

        // Checks that the GameRegion does not exist.
        gameRegion = findById(id);
        assertFalse(gameRegion.isPresent());

    }

    @Test
    @DirtiesContext
    public void shouldCreateAGameRegionWithAGameThatExists() {

        // Creates a new game with the EUROPE region and saves it to the repository.
        GameRegionId newId = new GameRegionId(12345L, Regions.EUROPE);
        saveGameRegion(new GameRegion(newId));

        // Checks that the new GameRegion exists in the repository.
        Optional<GameRegion> newGameRegion = findById(newId);
        assertTrue(newGameRegion.isPresent());
    }

    @Test
    public void shouldNotCreateAGameRegionWithAGameThatDoesNotExist() {

        // Creates a GameRegion with a game ID that does not exist
        // and checks that an error is thrown.
        GameRegion newGameRegion = new GameRegion(new GameRegionId(0L, Regions.EUROPE));
        assertThrows(Exception.class, () ->  saveGameRegion(newGameRegion) );
    }

    // Method to refactor GameRegion save() method.
    private GameRegion saveGameRegion(GameRegion gameRegion) {
        return gameRegionRepository.save(gameRegion);
    }

    // Method to refactor GameRegion findById() method.
    private Optional<GameRegion> findById(GameRegionId id) {
        return gameRegionRepository.findByGameRegionId(id);
    }
}