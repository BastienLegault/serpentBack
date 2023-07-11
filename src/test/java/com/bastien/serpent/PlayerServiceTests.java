package com.bastien.serpent;

import static org.junit.jupiter.api.Assertions.*;

import com.bastien.serpent.model.Player;
import com.bastien.serpent.repo.PlayerRepo;
import com.bastien.serpent.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PlayerServiceTests {

    private PlayerService playerService;

    @Autowired
    private PlayerRepo playerRepo;

    @BeforeEach
    void init() {
        playerService = new PlayerService(playerRepo);
        log.info("je fais l'init");
    }

    @Test
    void testAddPlayer() {
        assertEquals(0, playerService.findAllPlayers().size());
        Player player = new Player();
        player.setName("joueur test");
        player.setClassement(2000);
        playerService.addPlayer(player);

        assertEquals(1, playerService.findAllPlayers().size());
        assertEquals(player.getClassement(), playerService.findAllPlayers().get(0).getClassement());
        assertEquals(player.getName(), playerService.findAllPlayers().get(0).getName());

    }
}
