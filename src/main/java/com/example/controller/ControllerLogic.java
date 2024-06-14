package com.example.controller;

import com.example.matchEngine.services.matchjsonservice.MatchJSONService;
import com.example.matchEngine.services.matchrepositoryservice.MatchRepositoryService;
import com.example.model.results.Results;
import com.example.simulation.Simulate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ControllerLogic {

    @Autowired
    private final Simulate simulate;

    @Autowired
    private MatchRepositoryService matchRepositoryService;

    @Autowired
    MatchJSONService matchJSONService;

    public ControllerLogic(Simulate simulate){
        this.simulate = simulate;
    }

    public JSONObject initiateMatch(String homeTeamNameAbbrev, String awayTeamNameAbbrev){
        return simulate.initiateMatch(homeTeamNameAbbrev, awayTeamNameAbbrev);
    }

    public JSONObject processMatch(String homeTeam, String awayTeam) {
        JSONObject jsonObject = simulate.simulateMatch(homeTeam, awayTeam);
        return jsonObject;
    }

    public JSONObject fetchMatches(){
        List<Results> list= matchRepositoryService.fetchMatches();
        return matchJSONService.processMatches(list);
    }
}
