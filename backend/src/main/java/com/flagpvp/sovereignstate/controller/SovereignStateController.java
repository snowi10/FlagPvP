package com.flagpvp.sovereignstate.controller;

import java.util.Optional;

import com.flagpvp.sovereignstate.domain.entity.SovereignState;
import com.flagpvp.sovereignstate.repository.SovereignStateRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles HTTP requests for retrieving and returning 
 * Sovereign State objects. 
 */
@RestController
@RequestMapping("/sovereignState")
public class SovereignStateController {

    // The repository that will be used to get the sovereign state.
    private final SovereignStateRepository sovereignStateRepository;

    public SovereignStateController(SovereignStateRepository sovereignStateRepository) {
        this.sovereignStateRepository = sovereignStateRepository;
    }
 
    /**
     * Gets the specifed sovereign state.
     * 
     * @param name the name of the sovereign state.
     * @return a Response Entity with an HTTP status of 'OK' and the sovereign state object or
     *         a Response Entity with an HTTP status of 'NOT FOUND'.
     */
    @GetMapping("/{name}")
    public ResponseEntity<SovereignState> getSovereignState(@PathVariable String name) {
        Optional<SovereignState> sovereignState = sovereignStateRepository.findByName(name);

        if (sovereignState.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(sovereignState.get());
        }
    }
}