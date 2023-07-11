package com.bastien.serpent.service;

import com.bastien.serpent.exceptions.UserNotFoundException;
import com.bastien.serpent.model.Player;
import com.bastien.serpent.repo.PlayerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PlayerService {

    @Autowired
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player addPlayer(Player player) {
        log.info("service addPlayer");
        log.info(player.toString());
        return playerRepo.save(player);
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

    public Player updatePlayer (Player player) {
        log.info("service : " + player.toString());
        return playerRepo.save(player);
    }

    public Player findPlayerById(Long id) {
        return playerRepo.findPlayerById(id).
                orElseThrow(() -> new UserNotFoundException("Player by id " + id + " was not found"));
    }

    public void deletePlayer(Long id) {
        playerRepo.deleteById(id);
    }

    public List<List<Player>> generatePoule(List<Player> players, int nbPoule) {
        log.info(""+nbPoule);
        log.info("liste des joueurs");
        log.info(players.toString());
        List<List<Player>> result = new ArrayList<>();
        for(int i = 0; i < nbPoule; i++) {
            boolean bool = true;
            int j = i;
            List<Player> poule = new ArrayList<>();
            while(j < players.size()) {
                poule.add(players.get(j));
                if(bool) {
                    j += 2*nbPoule - (2*i + 1);
                } else {
                    j += 2*i + 1;
                }
                bool = !bool;
            }
            result.add(poule);
        }
        log.info(result.toString());
        return result;
    }
}
