package com.example.matchEngine.matchSetup;

import com.example.model.InGameMatchStats;
import com.example.model.player.InGamePlayerStats;
import com.example.model.player.Player;
import com.example.team.PlayersMatchStats;
import com.example.team.Team;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MatchSetup {

    private PlayersMatchStats playersMatchStats;

    public PlayersMatchStats assignPlayersToMatch(Team team, Boolean home){ // make a match setup class
        ArrayList<Player> players = (ArrayList<Player>) team.getPlayers();
        List<String> orderedList = Arrays.asList("GK", "DL", "DCL", "DCR","DR","DM","MR","MCR","MCL","ML","ST");

        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparingInt(o -> orderedList.indexOf(o.getStartingPosition())))
                .collect(Collectors.toList());


        ArrayList<InGamePlayerStats> inGamePlayerStatsArray = new ArrayList();
        playersMatchStats.setInGamePlayerStatsArray(inGamePlayerStatsArray);
        for(Player player: sortedPlayers){
            //set matchID here
            InGamePlayerStats inGamePlayerStats = new InGamePlayerStats();
            inGamePlayerStats.setName(player.getLastName());
            inGamePlayerStats.setPos(player.getStartingPosition());
            inGamePlayerStats.setPlayerID(player.getId());
            inGamePlayerStats.setClub(player.getClubAbbrev());
            inGamePlayerStats.setAssists(1);
            playersMatchStats.getInGamePlayerStatsArray().add(inGamePlayerStats);

            inGamePlayerStats.setHome(home);
        }

        return playersMatchStats;
    }

    public List<InGamePlayerStats> sortPlayers(ArrayList<InGamePlayerStats> unsortedArray){
        List<String> orderedList = Arrays.asList("GK", "DL", "DCL", "DCR","DR","DM","MR","MCR","MCL","ML","ST");

        List<InGamePlayerStats> sortedPlayers = unsortedArray.stream()
                .sorted(Comparator.comparingInt(o -> orderedList.indexOf(o.getPos())))
                .collect(Collectors.toList());

        return sortedPlayers;
    }




}
