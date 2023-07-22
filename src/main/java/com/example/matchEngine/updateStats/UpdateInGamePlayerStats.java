package com.example.matchEngine.updateStats;

import com.example.model.player.InGamePlayerStats;

import java.util.Map;

public class UpdateInGamePlayerStats {

    private Map<String, InGamePlayerStats> homePlayersMatchStatsMap;
    private Map<String,InGamePlayerStats> awayPlayersMatchStatsMap;

    public UpdateInGamePlayerStats(Map<String, InGamePlayerStats> homePlayersMatchStatsMap, Map<String,InGamePlayerStats> awayPlayersMatchStatsMap){
        this.homePlayersMatchStatsMap = homePlayersMatchStatsMap;
        this.awayPlayersMatchStatsMap = awayPlayersMatchStatsMap;
    }
    public void updatePassStat(String playerName){
        int passes = homePlayersMatchStatsMap.get(playerName).getPasses();
        passes ++;
        homePlayersMatchStatsMap.get(playerName).setPasses(passes);
    }

    public void updateTouchStat(String playerName){
        int touches = homePlayersMatchStatsMap.get(playerName).getTouches();
        touches ++;
        homePlayersMatchStatsMap.get(playerName).setPasses(touches);
    }
}
