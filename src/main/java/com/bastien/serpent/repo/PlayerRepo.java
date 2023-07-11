package com.bastien.serpent.repo;

import com.bastien.serpent.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {

    Optional<Player> findPlayerById(Long id);
}
