package com.example.matchEngine.services.UpdateStats;

import com.example.model.playeraction.PlayerAction;
import com.example.model.player.InGamePlayerStats;
import com.example.model.player.Player;
import com.example.model.playeraction.pass.Pass;
import com.example.model.playeraction.touch.Touch;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Component
public class  UpdateInGamePlayerStats{

    HashMap<String,InGamePlayerStats> homeTeamPlayersStats;
    HashMap<String,InGamePlayerStats> awayTeamPlayersStats;

    public HashMap<String, InGamePlayerStats> initializeInGamePlayerStats(List<Player> players){
        HashMap<String,InGamePlayerStats> tempHashMap = new HashMap<>();
        for (Player player : players) {
            tempHashMap.put(player.getLastName(), new InGamePlayerStats());
        }
        return tempHashMap;
    }
    public void updateInGamePlayerStats(HashMap<String, PlayerAction> playersStatsToBeUpdated) {
        for (String playerName : playersStatsToBeUpdated.keySet()) {
            PlayerAction playerAction = playersStatsToBeUpdated.get(playerName);
            if (homeTeamPlayersStats.containsKey(playerName)) {
                updateInGamePlayerStatsLogic(playerName, playerAction, homeTeamPlayersStats);
            } else if (awayTeamPlayersStats.containsKey(playerName)) {
                updateInGamePlayerStatsLogic(playerName, playerAction, awayTeamPlayersStats);
            }
        }
    }
    public void updateInGamePlayerStatsLogic(String playerName, PlayerAction playerAction, HashMap<String,InGamePlayerStats> playerStatsHashMap ){
        InGamePlayerStats playerStats = playerStatsHashMap.get(playerName);
        if(playerAction instanceof Pass){
            playerStats.setPasses(playerStats.getPasses() + 1);
        } else if(playerAction instanceof Touch){
            playerStats.setTouches(playerStats.getTouches() + 1);
        } else {
            throw new IllegalArgumentException(); // more of a checker currently to nake sure every action is recorded
        }
    }
}
