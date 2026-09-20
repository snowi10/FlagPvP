package com.flagpvp.player.repository;

import com.flagpvp.player.domain.entity.Player;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Repository for player entity.
 */
public interface PlayerRepository extends CrudRepository<Player, String> {

    // Finds a player by their ID.
    @NullMarked
    Optional<Player> findById(String id);

    // Deletes a player by their ID;
    @NullMarked
    void deleteById(String id);
}
