package com.example.repository;

import com.example.model.player.Goalkeeper;
import com.example.model.player.OutfieldPlayer;
import com.example.model.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private OutfieldPlayerRepository outfieldPlayerRepository;

    @Autowired
    private GoalkeeperRepository goalkeeperRepository;

    public List<OutfieldPlayer> findOutfieldPlayersClub(String club){
        return outfieldPlayerRepository.findOutfieldPlayersClub(club);

    }

    public List<Player> findAllOutfieldPlayersClub(String club){
        return  outfieldPlayerRepository.findAllPlayersClub(club);
    }

    public List<Goalkeeper> findGoalkeeperPlayersClub(String club){
        return goalkeeperRepository.findGoalkeeperPlayersClub(club);
    }

    public List<Player> findAllGoalkeepersClub (String club){
        return  goalkeeperRepository.findAllPlayersClub(club);
    }

    public Iterable<OutfieldPlayer> findALlOutfield(){
        return outfieldPlayerRepository.findAll();
    }

    public Iterable<Goalkeeper> findALlGoalkeeper(){
        return goalkeeperRepository.findAll();
    }

}
