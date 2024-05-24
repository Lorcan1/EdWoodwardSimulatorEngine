package com.example.matchEngine.services.conditioningservice;

import com.example.model.player.InGamePlayerStats;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConditioningService {


    public void conditioningService(List<InGamePlayerStats> playerStats){
        for(InGamePlayerStats inGamePlayerStats: playerStats){
            inGamePlayerStats.setCondition(18);
        }

    }
}
