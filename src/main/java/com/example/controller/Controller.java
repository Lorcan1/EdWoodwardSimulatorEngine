package com.example.controller;

import com.example.simulation.Simulate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    @Autowired
    private ControllerLogic controllerLogic;

    public Controller(ControllerLogic controllerLogic) {
        this.controllerLogic = controllerLogic;
    }

    @GetMapping("/initiate-match")
    public JSONObject initiateMatch(@RequestParam("homeTeam") String homeTeam, @RequestParam("awayTeam") String awayTeam) {
        return controllerLogic.initiateMatch(homeTeam,awayTeam);
    }

    @GetMapping("/process-match")
    public JSONObject processMatch(@RequestParam("homeTeam") String homeTeam, @RequestParam("awayTeam") String awayTeam) {
        return controllerLogic.processMatch(homeTeam,awayTeam);
    }

    @GetMapping("/fetch-matches")
    public JSONObject fetchMatches() {
        return controllerLogic.fetchMatches();
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("This is the controller");
        return "Hello World";
    }
}