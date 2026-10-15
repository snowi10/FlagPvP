package com.flagpvp.dao.impl; 

import com.flagpvp.domain.entity.Region;
import com.flagpvp.domain.RegionName;
import com.flagpvp.dao.RegionDao;
import com.flagpvp.mapper.RegionDaoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation for the Region entity.
 */ 
@Repository 
public class RegionDaoImpl implements RegionDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired  
    private RegionDaoMapper regionDaoMapper;

    public Region findByName(RegionName name) {
        return jdbcTemplate.queryForObject("SELECT * from region WHERE name = ?", regionDaoMapper, name.toString());
    }
}