package com.example.matchEngine.services.UpdateStats;

import com.example.model.player.InGamePlayerStats;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
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
    public void updateInGamePlayerStats(HashMap<String, String> playersStatsToBeUpdated) {
        for (String playerName : playersStatsToBeUpdated.keySet()) {
            String statToBeUpdated = playersStatsToBeUpdated.get(playerName);
            if (homeTeamPlayersStats.containsKey(playerName)) {
                updateInGamePlayerStatsLogic(playerName, statToBeUpdated, homeTeamPlayersStats);
            } else if (awayTeamPlayersStats.containsKey(playerName)) {
                updateInGamePlayerStatsLogic(playerName, statToBeUpdated, awayTeamPlayersStats);
            }
        }
    }
    public void updateInGamePlayerStatsLogic(String playerName,String statToBeUpdated,  HashMap<String,InGamePlayerStats> playerStatsHashMap ){
        InGamePlayerStats playerStats = playerStatsHashMap.get(playerName);
        if(statToBeUpdated.equals("pass")){
            playerStats.setPasses(playerStats.getPasses() + 1);
        } else if(statToBeUpdated.equals("touch")){
            playerStats.setTouches(playerStats.getTouches() + 1);
        }
    }
}
