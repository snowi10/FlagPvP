package com.flagpvp.backend.game.repository;

import com.flagpvp.backend.game.domain.entity.Region;
import com.flagpvp.backend.game.domain.entity.Regions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
public class RegionRepositoryTests {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void shouldFindAllRegions() {

        // Checks that AMERICAS is in the repository.
        Optional<Region> americas = regionRepository.findByName(Regions.AMERICAS);
        assertTrue(americas.isPresent());
        assertEquals(Regions.AMERICAS, americas.get().getName());
        assertEquals(35, americas.get().getStatesCount());

        // Checks that EUROPE is in the repository.
        Optional<Region> europe = regionRepository.findByName(Regions.EUROPE);
        assertTrue(europe.isPresent());
        assertEquals(Regions.EUROPE, europe.get().getName());
        assertEquals(44, europe.get().getStatesCount());

        // Checks that AFRICA is in the repository.
        Optional<Region> africa = regionRepository.findByName(Regions.AFRICA);
        assertTrue(africa.isPresent());
        assertEquals(Regions.AFRICA, africa.get().getName());
        assertEquals(54, africa.get().getStatesCount());

        // Checks that ASIA is in the repository.
        Optional<Region> asia = regionRepository.findByName(Regions.ASIA);
        assertTrue(asia.isPresent());
        assertEquals(Regions.ASIA, asia.get().getName());
        assertEquals(48, asia.get().getStatesCount());

        // Checks that OCEANIA is in the repository.
        Optional<Region> oceania = regionRepository.findByName(Regions.OCEANIA);
        assertTrue(oceania.isPresent());
        assertEquals(Regions.OCEANIA, oceania.get().getName());
        assertEquals(14, oceania.get().getStatesCount());

    }
}
