package com.flagpvp.dao.impl;

import com.flagpvp.domain.RegionName;
import com.flagpvp.domain.entity.SovereignState;
import com.flagpvp.dao.SovereignStateDao;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest 
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class SovereignStateDaoImplTests {

    @Autowired
    SovereignStateDao sovereignStateDao;

    // TODO: Add more sovereign states to test.
    @Test 
    public void shouldGetASovereignStatesThatExist() {
        SovereignState ethiopia = sovereignStateDao.findByName("Ethiopia");
        assertThat(ethiopia.getName()).isEqualTo("Ethiopia");
        assertThat(ethiopia.getRegion()).isEqualTo(RegionName.AFRICA);
    }

    // TODO: Create test for not getting sovereign states that do not exist.
}
