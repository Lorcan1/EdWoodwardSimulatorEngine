package com.example.matchEngine.services.matchjsonservice;


import com.example.matchEngine.services.UpdateStats.UpdateInGameMatchStats;
import com.example.model.player.InGamePlayerStats;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@Slf4j
public class MatchJSONService {

    JSONObject jsonObject = new JSONObject();
    ObjectMapper objectMapper = new ObjectMapper();

    public JSONObject processInitialMatchResponse(HashMap<String, InGamePlayerStats> homeTeamPlayersStats, HashMap<String,InGamePlayerStats> awayTeamPlayersStats,
    UpdateInGameMatchStats updateInGameMatchStats){
        List<InGamePlayerStats> homePlayerStats = sortPlayers(new ArrayList<>(homeTeamPlayersStats.values()));
        List<InGamePlayerStats> awayPlayerStats = sortPlayers(new ArrayList<>(awayTeamPlayersStats.values()));
        homePlayerStats.addAll(awayPlayerStats);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("players", homePlayerStats);
        jsonObject.put("match", updateInGameMatchStats.getInGameMatchStats());
        return jsonObject;
//        processScore(homeTeamPlayersStats);
//        processScore(awayTeamPlayersStats);

    }

    public List<InGamePlayerStats> sortPlayers(List<InGamePlayerStats> unsortedArray){
        List<String> orderedList = Arrays.asList("GK", "DL", "DCL", "DCR","DR","DM","MR","MCR","MCL","ML","ST");

        List<InGamePlayerStats> sortedPlayers = unsortedArray.stream()
                .sorted(Comparator.comparingInt(o -> orderedList.indexOf(o.getPos())))
                .collect(Collectors.toList());

        return sortedPlayers;
    }

    public JSONObject processFinalMatchResponse(HashMap<String, InGamePlayerStats> homeTeamPlayersStats, HashMap<String,InGamePlayerStats> awayTeamPlayersStats,
                                                  UpdateInGameMatchStats updateInGameMatchStats, List feedList){
        List<InGamePlayerStats> homePlayerStats = sortPlayers(new ArrayList<>(homeTeamPlayersStats.values()));
        List<InGamePlayerStats> awayPlayerStats = sortPlayers(new ArrayList<>(awayTeamPlayersStats.values()));
        homePlayerStats.addAll(awayPlayerStats);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("players", homePlayerStats);
        jsonObject.put("match", updateInGameMatchStats.getInGameMatchStats());
        updateInGameMatchStats.getInGameMatchStats().getHomeGoals().addAll(updateInGameMatchStats.getInGameMatchStats().getAwayGoals());
        jsonObject.put("score", updateInGameMatchStats.getInGameMatchStats().getHomeGoals());
        jsonObject.put("feed", feedList);

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
