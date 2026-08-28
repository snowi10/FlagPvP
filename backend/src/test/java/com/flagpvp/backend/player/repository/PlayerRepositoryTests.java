package com.flagpvp.backend.player.repository;

import com.flagpvp.backend.player.domain.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJdbcTest
public class PlayerRepositoryTests {

    @Autowired
    PlayerRepository playerRepository;

    public void shouldGetAPlayerThatExists() {

        // Gets an existing player and checks that
        // all values are correct.
        Optional<Player> snowi10 = playerRepository.findById("snowi10");
        assertTrue(snowi10.isPresent());
        assertEquals("snowi10", snowi10.get().getId());
        assertEquals(12345, snowi10.get().getGameId());
        assertEquals(false, snowi10.get().getReady());
        assertEquals(0, snowi10.get().getPoints());

        // Gets another existing player and checks that
        // all values are correct.
        Optional<Player> eden9 = playerRepository.findById("eden9");
        assertTrue(eden9.isPresent());
        assertEquals("eden9", eden9.get().getId());
        assertEquals(12345, eden9.get().getGameId());
        assertEquals(false, eden9.get().getReady());
        assertEquals(0, eden9.get().getPoints());
    }
}
