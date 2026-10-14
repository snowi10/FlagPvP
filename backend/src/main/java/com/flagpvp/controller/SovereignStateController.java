package com.flagpvp.controller;

import java.util.Optional;

import com.flagpvp.domain.entity.SovereignState;
import com.flagpvp.repository.SovereignStateRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handle HTTP requests for returning SovereignState objects. 
 */
@RestController
@RequestMapping("/sovereignState")
public class SovereignStateController {

    // The repository that will be used to get the sovereign state object.
    private final SovereignStateRepository sovereignStateRepository;

    public SovereignStateController(SovereignStateRepository sovereignStateRepository) {
        this.sovereignStateRepository = sovereignStateRepository;
    }
 
    /**
     * Get a SovereignState object.
     * 
     * @param name - the name of the sovereign state.
     * @return a response entity with an HTTP status of 'OK' and the SovereignState object or
     *         a response entity with an HTTP status of 'NOT FOUND'.
     */
    @GetMapping("/{name}")
    public ResponseEntity<SovereignState> getSovereignState(@PathVariable String name) {
        Optional<SovereignState> sovereignState = sovereignStateRepository.findByName(name);

        if (sovereignState.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(sovereignState.get());
    }
}