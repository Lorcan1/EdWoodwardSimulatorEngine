package com.example.matchEngine.updateStats;

import com.example.model.player.InGamePlayerStats;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UpdateInGamePlayerStats {

    private Map<String, InGamePlayerStats> homePlayersMatchStatsMap;
    private Map<String,InGamePlayerStats> awayPlayersMatchStatsMap;

    private Map<String,InGamePlayerStats> allPlayersMatchStatsMap;

    public UpdateInGamePlayerStats(Map<String, InGamePlayerStats> homePlayersMatchStatsMap, Map<String,InGamePlayerStats> awayPlayersMatchStatsMap){
        this.homePlayersMatchStatsMap = homePlayersMatchStatsMap;
        this.awayPlayersMatchStatsMap = awayPlayersMatchStatsMap;
        this.allPlayersMatchStatsMap = new HashMap<>(homePlayersMatchStatsMap);
        this.allPlayersMatchStatsMap.putAll(awayPlayersMatchStatsMap);

    }
    public void updatePassStat(String playerName){
        try {
            int passes = allPlayersMatchStatsMap.get(playerName).getPasses();
            passes++;
            allPlayersMatchStatsMap.get(playerName).setPasses(passes);
        } catch (Exception e){
            log.error(e + playerName);
        }
    }

    public void updateTouchStat(String playerName){
        try {
            int touches = allPlayersMatchStatsMap.get(playerName).getTouches();
            touches++;
            allPlayersMatchStatsMap.get(playerName).setPasses(touches);
        } catch (Exception e){
            log.error(e + playerName);
        }
    }
}
