package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.Regions;
import com.flagpvp.backend.game.domain.entity.SovereignState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
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

        // Checks that a country from Europe is in the database.
        Optional<SovereignState> russia = sovereignStateRepository.findByName("Russia");
        assertTrue(russia.isPresent());
        assertEquals("Russia", russia.get().getName());
        assertEquals(Regions.EUROPE, russia.get().getRegion());

        // Checks that a country from Africa is in the database.\
        Optional<SovereignState> kenya = sovereignStateRepository.findByName("Kenya");
        assertTrue(kenya.isPresent());
        assertEquals("Kenya", kenya.get().getName());
        assertEquals(Regions.AFRICA, kenya.get().getRegion());

        // Checks that a country from Asian is in the database
        Optional<SovereignState> georgia = sovereignStateRepository.findByName("Georgia");
        assertTrue(georgia.isPresent());
        assertEquals("Georgia", georgia.get().getName());
        assertEquals(Regions.ASIA, georgia.get().getRegion());

        // Checks that a country from Oceania is in the database.
        Optional<SovereignState> tonga = sovereignStateRepository.findByName("Tonga");
        assertTrue(tonga.isPresent());
        assertEquals("Tonga", tonga.get().getName());
        assertEquals(Regions.OCEANIA, tonga.get().getRegion());

    }

    @Test
    public void shouldNotGetAStateThatDoesNotExist() {
        Optional<SovereignState> doesNotExist = sovereignStateRepository.findByName("does not exist");
        assertFalse(doesNotExist.isPresent());
    }
}
