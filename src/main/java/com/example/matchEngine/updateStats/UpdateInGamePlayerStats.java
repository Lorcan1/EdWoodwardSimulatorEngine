package com.example.matchEngine.updateStats;

import com.example.matchEngine.engine.MatchEngine;
import com.example.model.player.InGamePlayerStats;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UpdateInGamePlayerStats {

    private Map<String, InGamePlayerStats> homePlayersMatchStatsMap;
    private Map<String,InGamePlayerStats> awayPlayersMatchStatsMap;

    private Map<String,InGamePlayerStats> allPlayersMatchStatsMap;

    private MatchEngine matchEngine;

//    public UpdateInGamePlayerStats(Map<String, InGamePlayerStats> homePlayersMatchStatsMap, Map<String,InGamePlayerStats> awayPlayersMatchStatsMap){
//        this.homePlayersMatchStatsMap = homePlayersMatchStatsMap;
//        this.awayPlayersMatchStatsMap = awayPlayersMatchStatsMap;
////        this.allPlayersMatchStatsMap = new HashMap<>(homePlayersMatchStatsMap);
////        this.allPlayersMatchStatsMap.putAll(awayPlayersMatchStatsMap);
//
//    }

    public UpdateInGamePlayerStats(MatchEngine matchEngine){
        this.matchEngine = matchEngine;

    }
    public void updatePassStat(String playerName){

        try {

            if (matchEngine.getHomePlayersMatchStatsMap().containsKey(playerName)) {
                int passes = matchEngine.getHomePlayersMatchStatsMap().get(playerName).getPasses();
                passes++;
                matchEngine.getHomePlayersMatchStatsMap().get(playerName).setPasses(passes);
            } else {
                int passes = matchEngine.getAwayPlayersMatchStatsMap().get(playerName).getPasses();
                passes++;
                matchEngine.getAwayPlayersMatchStatsMap().get(playerName).setPasses(passes);
            }
        } catch (Exception e){
            log.error(e + playerName);
        }
    }

    public void updateTouchStat(String playerName){
        try {
            if (matchEngine.getHomePlayersMatchStatsMap().containsKey(playerName)) {
                int touches = matchEngine.getHomePlayersMatchStatsMap().get(playerName).getTouches();
                touches++;
                matchEngine.getHomePlayersMatchStatsMap().get(playerName).setTouches(touches);
            } else {
                int touches = matchEngine.getAwayPlayersMatchStatsMap().get(playerName).getPasses();
                touches++;
                matchEngine.getAwayPlayersMatchStatsMap().get(playerName).setTouches(touches);
            }
        } catch (Exception e){
            log.error(e + playerName);
        }
    }

    public void updateShotStat(String playerName){

    }

    public void updateGoalStat(String playerName){
        if (matchEngine.getHomePlayersMatchStatsMap().containsKey(playerName)) {
            int goals = matchEngine.getHomePlayersMatchStatsMap().get(playerName).getGoals();
            goals++;
            matchEngine.getHomePlayersMatchStatsMap().get(playerName).setGoals(goals);
        } else {
            int goals = matchEngine.getAwayPlayersMatchStatsMap().get(playerName).getGoals();
            goals++;
            matchEngine.getAwayPlayersMatchStatsMap().get(playerName).setGoals(goals);
        }

    }

    public void updateAssistStat(String playerName ){

    }
}
