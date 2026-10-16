package com.flagpvp.dao.impl;

import java.util.List;
import java.util.ArrayList;

import com.flagpvp.domain.RegionName;
import com.flagpvp.domain.entity.SovereignState;
import com.flagpvp.dao.SovereignStateDao;
import com.flagpvp.mapper.SovereignStateDaoMapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DAO 
 * implementation for the SovereignState entity. 
 */
@Repository 
public class SovereignStateDaoImpl implements SovereignStateDao {

    @Autowired 
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SovereignStateDaoMapper sovereignStateDaoMapper;

    public SovereignState findByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM sovereign_state WHERE name = ?", sovereignStateDaoMapper, name);
    }
     
    public List<SovereignState> findByRegions(List<RegionName> regionList) {
        StringBuilder regionConditions = new StringBuilder();
        List<String> regionStringList = new ArrayList<String>();

        for (int i = 0; i < regionList.size(); i++) {
            if (i == 0) {
                regionConditions.append("?");
            }
            else {
                regionConditions.append(", ?");
            }

            regionStringList.add(regionList.get(i).toString());
        }
        
        return jdbcTemplate.query(String.format("SELECT * FROM sovereign_state WHERE region IN (%s)",
                                    regionConditions.toString()), sovereignStateDaoMapper, regionStringList.toArray());
    }
}
