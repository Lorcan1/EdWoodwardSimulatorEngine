package com.example.matchEngine;

import com.example.model.player.InGamePlayerStats;

import java.util.Map;

public class UpdateInGamePlayerStats {

    private Map<String, InGamePlayerStats> homePlayersMatchStatsMap;
    private Map<String,InGamePlayerStats> awayPlayersMatchStatsMap;

    public UpdateInGamePlayerStats(Map<String, InGamePlayerStats> homePlayersMatchStatsMap, Map<String,InGamePlayerStats> awayPlayersMatchStatsMap){
        this.homePlayersMatchStatsMap = homePlayersMatchStatsMap;
        this.awayPlayersMatchStatsMap = awayPlayersMatchStatsMap;
    }
    public void updatePlayerStats(String action){
        switch (action){
            case "kickOff":
                kickOff();
                break;
            case "homeDefencePoss":
                break;
            case "midfieldPoss":
            case "loosBallMidfield":
                break;
            case "attackerRecievesBall": //  it's not a throughBall
                break;
            case "throughBall": //not all attackers recieve perfect through balls, some have to create own chances with ball to feet/dribbling/pace
                break;
        }
    }
    public void kickOff(){

    }
}
