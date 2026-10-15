package com.flagpvp.dao.impl;

import com.flagpvp.domain.entity.Region;
import com.flagpvp.dao.RegionDao;
import com.flagpvp.domain.RegionName;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RegionDaoImplTests {

    @Autowired
    private RegionDao regionDao; 

    @Test 
    public void shouldGetAllRegions() {

        // Get AMERICAS region.
        Region americas = regionDao.findByName(RegionName.AMERICAS);
        assertThat(americas.getName()).isEqualTo(RegionName.AMERICAS);
        assertThat(americas.getStatesCount()).isEqualTo(35);

        // Get EUROPE region. 
        Region europe = regionDao.findByName(RegionName.EUROPE);
        assertThat(europe.getName()).isEqualTo(RegionName.EUROPE);
        assertThat(europe.getStatesCount()).isEqualTo(44);

        // Get AFRICA region.
        Region africa = regionDao.findByName(RegionName.AFRICA);
        assertThat(africa.getName()).isEqualTo(RegionName.AFRICA);
        assertThat(africa.getStatesCount()).isEqualTo(54);

        // Get ASIA region.
        Region asia = regionDao.findByName(RegionName.ASIA);
        assertThat(asia.getName()).isEqualTo(RegionName.ASIA);
        assertThat(asia.getStatesCount()).isEqualTo(48);

        // Get OCEANIA region.
        Region oceania = regionDao.findByName(RegionName.OCEANIA);
        assertThat(oceania.getName()).isEqualTo(RegionName.OCEANIA);
        assertThat(oceania.getStatesCount()).isEqualTo(14);
    }
}
