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

        // Gets an existing player. 
        Optional<Player> snowi10 = findById("snowi10");
        assertTrue(snowi10.isPresent());

        // Chekcs that all values are correct.
        Player snowi10Player = snowi10.get();
        assertEquals("snowi10", snowi10Player.getId());
        assertEquals(12345, snowi10Player.getGameId());
        assertEquals(false, snowi10Player.getReady());
        assertEquals(0, snowi10Player.getPoints());


        // Gets another existing player.  
        Optional<Player> eden9 = findById("eden9");
        assertTrue(eden9.isPresent());

        // Checks that all values are correct.
        Player eden9Player = eden9.get();
        assertEquals("eden9", eden9Player.getId());
        assertEquals(12345, eden9Player.getGameId());
        assertEquals(false, eden9Player.getReady());
        assertEquals(0, eden9Player.getPoints());
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

        // Creates a new player with default values.
        Player newPlayer = new Player();
        playerRepository.save(newPlayer); 

        // Checks that the player exists in the repository
        Optional<Player> getPlayer = findById(newPlayer.getId());
        assertTrue(getPlayer.isPresent());

        // Checks that all values are correct.
        Player getPlayerObj = getPlayer.get();
        assertEquals(newPlayer.getId(), getPlayerObj.getId());
        assertNull(getPlayerObj.getGameId());
        assertFalse(getPlayerObj.getReady());
        assertEquals(0, getPlayerObj.getPoints());

        // Creates a new player with supplied settings.
        Player newPlayer2 = new Player();
        newPlayer2.setGameId(12345L);
        newPlayer2.setReady(true);
        newPlayer2.setPoints(10);
        playerRepository.save(newPlayer2);

        // Checks that they exist in the repository
        Optional<Player> getPlayer2 = findById(newPlayer2.getId());
        assertTrue(getPlayer2.isPresent());

        // Checks that all values are correct.
        Player getPlayer2Obj = getPlayer2.get();
        assertEquals(newPlayer2.getId(), getPlayer2Obj.getId());
        assertEquals(true, getPlayer2Obj.getReady());
        assertEquals(10, getPlayer2Obj.getPoints());
    }

    @Test
    public void shouldNotCreateANewPlayerWithAGameThatDoesNotExist() {

        // Creates a new player with an invalid game ID
        // and checks that this causes an error.
        Player invalidPlayer = new Player();
        invalidPlayer.setGameId(1789L);
        assertThrows(Exception.class, () -> playerRepository.save(invalidPlayer));
    }

    @Test
    @DirtiesContext
    public void shouldUpdateAPlayer() {

        // Gets an existing player.
        Optional<Player> eden = findById("eden9");
        assertTrue(eden.isPresent());

        // Checks that all values are correct for the player.
        Player edenPlayer = eden.get();
        assertEquals("eden9", edenPlayer.getId());
        assertEquals(12345L, edenPlayer.getGameId());
        assertEquals(false, edenPlayer.getReady());
        assertEquals(0, edenPlayer.getPoints());

        // Updates the player with new values
        // and saves them to the repository.
        Player updateEden = new Player(eden.get().getId(), 67890L, true, eden.get().getPoints());
        playerRepository.save(updateEden);

        // Gets the updated player. 
        Optional<Player> getEden = findById("eden9");
        assertTrue(getEden.isPresent());

        // Checks that the updated player has the correct values.
        Player getEdenPlayer = getEden.get();
        assertEquals("eden9", getEdenPlayer.getId());
        assertEquals(67890L, getEdenPlayer.getGameId());
        assertEquals(true, getEdenPlayer.getReady());
        assertEquals(0, getEdenPlayer.getPoints());
    }

    @Test
    public void playerShouldNotHaveAnyPointsAndCannotBeReadyWhenNotInAGame() {

        // Gets an existing player.
        Optional<Player> player = findById("snowi10");
        assertTrue(player.isPresent());

        // Checks that they have the correct values.
        Player playerObj = player.get();
        assertEquals(12345L, playerObj.getGameId());
        assertEquals(false, playerObj.getReady());
        assertEquals(0, playerObj.getPoints());

        // Updates the player with points when not in a game
        // and checks that an error occurs.
        Player invalidPlayer = new Player(playerObj.getId(), null, playerObj.getReady(), 1);
        assertThrows(Exception.class, () -> savePlayer(invalidPlayer)); 

        // Updates the player to being ready when not in a game
        // and checks that an error occurs.
        invalidPlayer.setReady(true);
        invalidPlayer.setPoints(0);
        assertThrows(Exception.class, () -> savePlayer(invalidPlayer));

    }

    // Method to refactor PlayerRepository save() method.
    private Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    // Method to refactor PlayerRepository findById() method. 
    private Optional<Player> findById(String id) {
        return playerRepository.findById(id);
    }
}