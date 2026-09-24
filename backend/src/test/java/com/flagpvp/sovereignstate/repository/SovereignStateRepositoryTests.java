package com.flagpvp.sovereignstate.repository;

import com.flagpvp.game.domain.entity.Regions;
import com.flagpvp.sovereignstate.domain.entity.SovereignState;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SovereignStateRepositoryTests {

    @Autowired
    SovereignStateRepository sovereignStateRepository;

    @Test
    public void shouldGetExistingStates() {

        // Checks that a country from the Americas is in the database.
        Optional<SovereignState> chile = sovereignStateRepository.findByName("Chile");
        assertTrue(chile.isPresent());
        assertEquals("Chile", chile.get().getName());
        assertEquals(Regions.AMERICAS, chile.get().getRegion());
        assertNotNull(chile.get().getFlagImage());

        // Checks that a country from Europe is in the database.
        Optional<SovereignState> russia = sovereignStateRepository.findByName("Russia");
        assertTrue(russia.isPresent());
        assertEquals("Russia", russia.get().getName());
        assertEquals(Regions.EUROPE, russia.get().getRegion());
        assertNotNull(russia.get().getFlagImage());

        // Checks that a country from Africa is in the database.
        Optional<SovereignState> kenya = sovereignStateRepository.findByName("Kenya");
        assertTrue(kenya.isPresent());
        assertEquals("Kenya", kenya.get().getName());
        assertEquals(Regions.AFRICA, kenya.get().getRegion());
        assertNotNull(kenya.get().getFlagImage());

        // Checks that a country from Asia is in the database
        Optional<SovereignState> georgia = sovereignStateRepository.findByName("Georgia");
        assertTrue(georgia.isPresent());
        assertEquals("Georgia", georgia.get().getName());
        assertEquals(Regions.ASIA, georgia.get().getRegion());
        assertNotNull(georgia.get().getFlagImage());

        // Checks that a country from Oceania is in the database.
        Optional<SovereignState> tonga = sovereignStateRepository.findByName("Tonga");
        assertTrue(tonga.isPresent());
        assertEquals("Tonga", tonga.get().getName());
        assertEquals(Regions.OCEANIA, tonga.get().getRegion());
        assertNotNull(tonga.get().getFlagImage());
    }

    @Test
    public void shouldNotGetAStateThatDoesNotExist() {

        // Gets a state that does not exist.
        Optional<SovereignState> doesNotExist = sovereignStateRepository.findByName("does not exist");
        assertFalse(doesNotExist.isPresent());
    }
}