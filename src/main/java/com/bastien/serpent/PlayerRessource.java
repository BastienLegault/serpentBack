package com.bastien.serpent;

import com.bastien.serpent.model.Player;
import com.bastien.serpent.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerRessource {

    @Autowired
    private final PlayerService playerService;

    public PlayerRessource(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers () {
        List<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Player> getPlayerById (@PathVariable("id") Long id) {
        Player player = playerService.findPlayerById(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer (@RequestBody Player player) {
        log.info("ressource addPlayer");
        log.info(player.toString());
        Player newPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Player> updatePlayer (@RequestBody Player player) {
        log.info("ressource : " + player.toString());
        Player updatePlayer = playerService.updatePlayer(player);
        return new ResponseEntity<>(updatePlayer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Player> deletePlayer (@PathVariable("id") Long id) {
        playerService.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/generate/{nbPoule}")
    public ResponseEntity<List<List<Player>>> generatePoule(@RequestBody List<Player> players, @PathVariable Integer nbPoule) {
        log.info(players.toString());
        List<List<Player>> poules = playerService.generatePoule(players, nbPoule);
        return new ResponseEntity<>(poules, HttpStatus.OK);
    }
}
