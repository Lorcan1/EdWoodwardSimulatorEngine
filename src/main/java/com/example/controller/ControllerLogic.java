package com.example.controller;

import com.example.model.Match;
import com.example.simulation.Simulate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerLogic {

    @Autowired
    private final Simulate simulate;

    public ControllerLogic(Simulate simulate){
        this.simulate = simulate;
    }

    public JSONObject initiateMatch(String homeTeamNameAbbrev, String awayTeamNameAbbrev){
        return simulate.initiateMatch(homeTeamNameAbbrev, awayTeamNameAbbrev);
    }

    public JSONObject processMatch(String homeTeam, String awayTeam){
        return simulate.simulateMatch(homeTeam, awayTeam);


    }
}
