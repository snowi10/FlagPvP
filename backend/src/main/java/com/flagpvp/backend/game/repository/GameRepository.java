package com.flagpvp.backend.game.repository;
import java.util.Optional;

import com.flagpvp.backend.game.domain.entity.Game;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;

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
