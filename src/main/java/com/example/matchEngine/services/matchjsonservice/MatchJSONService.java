package com.example.matchEngine.services.matchjsonservice;


import com.example.matchEngine.services.UpdateStats.UpdateInGameMatchStats;
import com.example.model.Match;
import com.example.model.player.InGamePlayerStats;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Getter
@Setter
@Slf4j
public class MatchJSONService {

    Match match;
    JSONObject jsonObject = new JSONObject();
    ObjectMapper objectMapper = new ObjectMapper();

    public JSONObject processInitialMatchResponse(HashMap<String, InGamePlayerStats> homeTeamPlayersStats, HashMap<String,InGamePlayerStats> awayTeamPlayersStats,
    UpdateInGameMatchStats updateInGameMatchStats){
        List<InGamePlayerStats> homePlayerStats = new ArrayList<>(homeTeamPlayersStats.values());
        List<InGamePlayerStats> awayPlayerStats = new ArrayList<>(awayTeamPlayersStats.values());
        homePlayerStats.addAll(awayPlayerStats);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("players", homePlayerStats);
        jsonObject.put("match", updateInGameMatchStats);
        return jsonObject;
//        processScore(homeTeamPlayersStats);
//        processScore(awayTeamPlayersStats);

    }

    public void processScore(Object object){
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
       log.info(jsonString);


    }
}
