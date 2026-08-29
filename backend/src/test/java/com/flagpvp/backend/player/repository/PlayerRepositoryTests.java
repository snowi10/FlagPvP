package com.flagpvp.backend.player.repository;

import com.flagpvp.backend.player.domain.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJdbcTest
public class PlayerRepositoryTests {

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void shouldGetAPlayerThatExists() {

        // Gets an existing player and checks that
        // all values are correct.
        Optional<Player> snowi10 = findById("snowi10");
        assertTrue(snowi10.isPresent());
        assertEquals("snowi10", snowi10.get().getId());
        assertEquals(12345, snowi10.get().getGameId());
        assertEquals(false, snowi10.get().getReady());
        assertEquals(0, snowi10.get().getPoints());

        // Gets another existing player and checks that
        // all values are correct.
        Optional<Player> eden9 = findById("eden9");
        assertTrue(eden9.isPresent());
        assertEquals("eden9", eden9.get().getId());
        assertEquals(12345, eden9.get().getGameId());
        assertEquals(false, eden9.get().getReady());
        assertEquals(0, eden9.get().getPoints());
    }

    @Test
    public void shouldNotGetAPlayerThatDoesNotExist() {

        // Gets a player that does not exist.
        Optional<Player> doesNotExist = findById("doesNotExist");
        assertFalse(doesNotExist.isPresent());
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewPlayer() {

        // Creates a new player with default values,
        // checks that they exist in the repository,
        // and checks that all values are correct.
        Player newPlayer = new Player();
        playerRepository.save(newPlayer); 
        Optional<Player> getPlayer = findById(newPlayer.getId());
        assertTrue(getPlayer.isPresent());
        assertEquals(newPlayer.getId(), getPlayer.get().getId());
        assertNull(getPlayer.get().getGameId());
        assertFalse(getPlayer.get().getReady());
        assertEquals(0, getPlayer.get().getPoints());

        // Creates a new player with supplied settings,
        // checks that they exist in the repository,
        // and checks that all values are correct.
        Player newPlayer2 = new Player();
        newPlayer2.setGameId(12345L);
        newPlayer2.setReady(true);
        newPlayer2.setPoints(10);
        playerRepository.save(newPlayer2);
        Optional<Player> getPlayer2 = findById(newPlayer2.getId());
        assertTrue(getPlayer2.isPresent());
        assertEquals(newPlayer2.getId(), getPlayer2.get().getId());
        assertEquals(true, getPlayer2.get().getReady());
        assertEquals(10, getPlayer2.get().getPoints());
    }

    @Test
    public void shouldNotCreateANewPlayerWithAGameThatDoesNotExist() {

        // Creates a new player with an invalid game ID
        // and checks that this causes an error.
        Player invalidPlayer = new Player();
        invalidPlayer.setGameId(1789L);
        assertThrows(Exception.class, () -> playerRepository.save(invalidPlayer));
    }

    // Method to refactor PlayerRepository findById() method. 
    private Optional<Player> findById(String id) {
        return playerRepository.findById(id);
    }
}