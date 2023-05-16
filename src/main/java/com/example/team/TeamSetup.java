package com.example.team;

import com.example.model.Player;
import com.example.repository.GoalkeeperRepository;
import com.example.repository.OutfieldPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TeamSetup {
    //10 outfield players and a goalkeeper
    //once there are more than 10 players in a team, a #first eleven starter will have to be
    private OutfieldPlayerRepository outfieldPlayerRepository;
    private GoalkeeperRepository goalkeeperRepository;


    public TeamSetup(OutfieldPlayerRepository outfieldPlayerRepository, GoalkeeperRepository goalkeeperRepository) {
        this.outfieldPlayerRepository = outfieldPlayerRepository;
        this.goalkeeperRepository = goalkeeperRepository;
    }




    public List<Player> returnStartingEleven(String club){
        List<Player> outfieldPlayers = new ArrayList<Player>();
        List<Player> goalkeepers = new ArrayList<Player>();
        outfieldPlayers = outfieldPlayerRepository.findAllPlayersClub(club);
        goalkeepers = goalkeeperRepository.findAllPlayersClub(club);
        Player goalkeeper = returnStartingKeeper(goalkeepers);
        List<Player> allPlayers = new ArrayList<Player>();
        allPlayers.add(goalkeeper);
        allPlayers.addAll(outfieldPlayers);
        return allPlayers;
    }

    public Player returnStartingKeeper(List<Player> goalkeepers){
        int highestOverall = 0;
        Player currentNumber1 = null;
        for(Player goalkeeper: goalkeepers){
            if(goalkeeper.getOverall() > highestOverall){
                currentNumber1 = goalkeeper;
                highestOverall = goalkeeper.getOverall();
            }
        }
        return currentNumber1;

    }
}


