package com.flagpvp.mapper;

import com.flagpvp.domain.entity.SovereignState;
import com.flagpvp.domain.RegionName;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Map SovereignState database entity to SovereignState Java object.
 */
public class SovereignStateDaoMapper implements RowMapper<SovereignState> {

    public SovereignState mapRow(ResultSet rs, int rowNum) {

        SovereignState sovereignState = null;

        try {

            // Get values of 'name', 'region', and 'flag_url' columns.
            String name = rs.getString("name");
            RegionName region = RegionName.valueOf(rs.getString("region"));
            String flagUrl = rs.getString("flag_url");

            // Create new SovereignState object from values.
            sovereignState = new SovereignState(name, region, flagUrl);
        }
        catch (SQLException sqlE) {
            System.out.println("Error retrieving SovereignState entity from database.");
        }

        return sovereignState;
    } 
}
