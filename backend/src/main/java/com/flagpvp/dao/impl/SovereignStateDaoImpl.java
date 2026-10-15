package com.flagpvp.dao.impl;

import com.flagpvp.domain.entity.SovereignState;
import com.flagpvp.dao.SovereignStateDao;
import com.flagpvp.mapper.SovereignStateDaoMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DAO implementation for the SovereignState entity. 
 */
@Repository 
public class SovereignStateDaoImpl implements SovereignStateDao{

    @Autowired 
    private JdbcTemplate jdbcTemplate;

    private final SovereignStateDaoMapper sovereignStateDaoMapper = new SovereignStateDaoMapper();

    public SovereignState findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM sovereign_state WHERE name = ?", sovereignStateDaoMapper, name);
    }
}
