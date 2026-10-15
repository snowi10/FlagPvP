package com.flagpvp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.flagpvp.mapper.RegionDaoMapper;
import com.flagpvp.domain.entity.Region;
import com.flagpvp.domain.RegionName;

/**
 * Map Region database entity to Region Java object. 
 */
@Component  
public class RegionDaoMapper implements RowMapper<Region> {

    public Region mapRow(ResultSet rs, int rowNum) {
        Region region = null;

        try {

            // Get values of 'name' and 'statesCount' columns.
            RegionName name = RegionName.valueOf(rs.getString("name"));
            int statesCount = rs.getInt("states_count");

            // Create new Region object from values.
            region = new Region(name, statesCount);
        
            return region;
        }
        catch (SQLException sqlE) {
            System.out.println("Error retrieving Region entity from database.");
        }       
        
        return region;
    }
}

