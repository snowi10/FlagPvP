package com.flagpvp.controller;

import com.flagpvp.domain.RegionName;
import com.flagpvp.domain.entity.Region;
import com.flagpvp.dao.impl.RegionDaoImpl;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

/**
 * Handle HTTP requests for returning Region objects. 
 * 
 * TODO: Create test class for this.
 */
@RestController 
@RequestMapping("/region")
public class RegionController {

    private final RegionDaoImpl regionDao;

    public RegionController(RegionDaoImpl regionDao) {
        this.regionDao = regionDao;
    }

    /**
     * Get a Region object.
     * 
     * @param name - The name of the region.
     * @return a response entity with an http status of 'OK' and the Region object or
     *         a response entity with an http status of 'NOT FOUND'. 
     */
    @GetMapping("/{name}") 
    public ResponseEntity<Region> getRegion(@PathVariable RegionName name) {

        Region region = regionDao.findByName(name);

        if (region == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(region);
    }
}
