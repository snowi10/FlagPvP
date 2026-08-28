package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.Game;
import com.flagpvp.backend.game.domain.entity.GameRegion;
import com.flagpvp.backend.game.domain.entity.GameRegionId;
import com.flagpvp.backend.game.domain.entity.Regions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
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

        // Gets an existing game with the AMERICAS region
        // and checks that all the settings are correct.
        Optional<GameRegion> gameRegion = gameRegionRepository.findByGameRegionId(id);
        assertTrue(gameRegion.isPresent());
        assertEquals(12345, gameRegion.get().getGameRegionId().getGameId());
        assertEquals(Regions.AMERICAS, gameRegion.get().getGameRegionId().getRegion());

        // Gets an existing game with the ASIA region
        // and checks that all the settings are correct.
        id = new GameRegionId(12345L, Regions.ASIA);
        gameRegion = gameRegionRepository.findByGameRegionId(id);
        assertTrue(gameRegion.isPresent());
        assertEquals(Regions.ASIA, gameRegion.get().getGameRegionId().getRegion());
    }

    @Test
    public void shouldNotGetAGameRegionThatDoesNotExist() {

        // An ID that does not exist.
        GameRegionId id = new GameRegionId(0L, Regions.EUROPE);

        // Checks that the GameRegion does not exist.
        Optional<GameRegion> gameRegion = gameRegionRepository.findByGameRegionId(id);
        assertFalse(gameRegion.isPresent());

        // An ID that exists but not with the specified region.
        id = new GameRegionId(12345L, Regions.OCEANIA);

        // Checks that the GameRegion does not exist.
        gameRegion = gameRegionRepository.findByGameRegionId(id);
        assertFalse(gameRegion.isPresent());

    }

    @Test
    @DirtiesContext
    public void shouldCreateAGameRegionWithAGameThatExists() {

        // Creates a new game with the EUROPE region and saves it to the database.
        GameRegionId newId = new GameRegionId(12345L, Regions.EUROPE);
        gameRegionRepository.save(new GameRegion(newId));

        // Checks that the new GameRegion exists in the database.
        Optional<GameRegion> newGameRegion = gameRegionRepository.findByGameRegionId(newId);
        assertTrue(newGameRegion.isPresent());
    }

    @Test
    public void shouldNotCreateAGameRegionWithAGameThatDoesNotExist() {

        // Creates a GameRegion with a game ID that does not exist
        // and checks that an error is thrown.
        GameRegion newGameRegion = new GameRegion(new GameRegionId(0L, Regions.EUROPE));
        assertThrows(DataIntegrityViolationException.class, () ->  gameRegionRepository.save(newGameRegion) );
    }
}