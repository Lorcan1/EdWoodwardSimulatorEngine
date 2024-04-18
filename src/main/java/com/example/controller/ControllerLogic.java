package com.example.controller;

import com.example.model.Match;
import com.example.simulation.Simulate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerLogic {

    @Autowired
    private final Simulate simulate;

    public ControllerLogic(Simulate simulate){
        this.simulate = simulate;
    }

    public void processMatch(String homeTeam, String awayTeam){
        simulate.simulateMatch(homeTeam, awayTeam);


    }
}
