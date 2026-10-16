package com.flagpvp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.flagpvp.domain.RegionName;
import com.flagpvp.domain.entity.SovereignState;
import com.flagpvp.dao.SovereignStateDao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

@SpringBootTest 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SovereignStateDaoImplTests {

    @Autowired
    SovereignStateDao sovereignStateDao;

    @Test 
    public void shouldGetASovereignStatesThatExists() {

        // Get a SovereignState in the 'AMERICAS' region.
        SovereignState haiti = sovereignStateDao.findByName("Haiti");
        assertThat(haiti.getName()).isEqualTo("Haiti");
        assertThat(haiti.getRegion()).isEqualTo(RegionName.AMERICAS);

        // Get a SovereignState in the 'EUROPE' region.
        SovereignState lithuania = sovereignStateDao.findByName("Lithuania");
        assertThat(lithuania.getName()).isEqualTo("Lithuania");
        assertThat(lithuania.getRegion()).isEqualTo(RegionName.EUROPE);

        // Get a SovereignState in the 'AFRICA' region.
        SovereignState ethiopia = sovereignStateDao.findByName("Ethiopia");
        assertThat(ethiopia.getName()).isEqualTo("Ethiopia");
        assertThat(ethiopia.getRegion()).isEqualTo(RegionName.AFRICA);

        // Get a SovereignState in the 'ASIA' region.
        SovereignState laos = sovereignStateDao.findByName("Laos");
        assertThat(laos.getName()).isEqualTo("Laos");
        assertThat(laos.getRegion()).isEqualTo(RegionName.ASIA);

        // Get a SovereignState in the 'OCEANIA' region.
        SovereignState tuvalu = sovereignStateDao.findByName("Tuvalu");
        assertThat(tuvalu.getName()).isEqualTo("Tuvalu");
        assertThat(tuvalu.getRegion()).isEqualTo(RegionName.OCEANIA);
    }

    @Test
    public void shouldNotGetASovereignStateThatDoesNotExist() {

        // Get a SovereignState entity that does not exist.
       assertThrows(EmptyResultDataAccessException.class, () -> sovereignStateDao.findByName("doesNotExist"));
    }

    @Test
    public void shouldGetAListOfSovereignStatesByRegions() {

        ArrayList<RegionName> regionList = new ArrayList<RegionName>();
        List<SovereignState> sovereignStateList;

        // Add 'AMERICAS' to the region list and check that the number of sovereign
        // states that were retrieved is correct.
        regionList.add(RegionName.AMERICAS);
        sovereignStateList = sovereignStateDao.findByRegions(regionList);
        assertThat(sovereignStateList.size()).isEqualTo(35);

        // Add 'EUROPE' to the region list and check that the number of sovereign
        // states that were retrieved is correct.
        regionList.add(RegionName.EUROPE);
        sovereignStateList = sovereignStateDao.findByRegions(regionList);
        assertThat(sovereignStateList.size()).isEqualTo(79);

        // Add 'AFRICA' to the region list and check that the number of sovereign
        // states that were retrieved is correct.
        regionList.add(RegionName.AFRICA);
        sovereignStateList = sovereignStateDao.findByRegions(regionList);
        assertThat(sovereignStateList.size()).isEqualTo(133);

        // Add 'ASIA' to the region list and check that the number of sovereign
        // states that were retrieved is correct.
        regionList.add(RegionName.ASIA);
        sovereignStateList = sovereignStateDao.findByRegions(regionList);
        assertThat(sovereignStateList.size()).isEqualTo(181);

        // Add 'OCEANIA' to the region list and check that the number of sovereign
        // states that were retrieved is correct.
        regionList.add(RegionName.OCEANIA);
        sovereignStateList = sovereignStateDao.findByRegions(regionList);
        assertThat(sovereignStateList.size()).isEqualTo(195);
    }
}