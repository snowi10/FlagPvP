package com.flagpvp.repository;
import java.util.Optional;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;

import com.flagpvp.domain.Game;

/**
 * Repository for the game.
 */
public interface GameRepository extends CrudRepository<Game, Long> {

    // Finds a game by id.
    @NullMarked
    Optional<Game> findById(Long id);

    // Deletes a game by id.
    @NullMarked
    void deleteById(Long id);
}
