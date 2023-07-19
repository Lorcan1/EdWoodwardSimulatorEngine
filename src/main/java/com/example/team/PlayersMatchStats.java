package com.example.team;

import com.example.model.player.InGamePlayerStats;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PlayersMatchStats {
    InGamePlayerStats inGamePlayerStats1;
    InGamePlayerStats inGamePlayerStats2;
    ArrayList<InGamePlayerStats> inGamePlayerStatsArray;

    public Map<String,InGamePlayerStats> createMapfromArray(ArrayList<InGamePlayerStats> inGamePlayerStatsArray){
        Map<String,InGamePlayerStats> inGamePlayerStatsMap = new HashMap<>();
        for(InGamePlayerStats player: inGamePlayerStatsArray ){
            inGamePlayerStatsMap.put(player.getName(),player);
        }
        return inGamePlayerStatsMap;
    }

}
