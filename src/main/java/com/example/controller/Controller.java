package com.example.controller;

import com.example.simulation.Simulate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private ControllerLogic controllerLogic;

    public Controller(ControllerLogic controllerLogic) {
        this.controllerLogic = controllerLogic;
    }

    @PostMapping("/initiate-match")
    public JSONObject initiateMatch(@RequestParam("homeTeam") String homeTeam, @RequestParam("awayTeam") String awayTeam) {
        return controllerLogic.initiateMatch(homeTeam,awayTeam);
    }

    @PostMapping("/test")
    public String test(@RequestParam("homeTeam") String homeTeam, @RequestParam("awayTeam") String awayTeam) {
        controllerLogic.processMatch(homeTeam,awayTeam);
        return "hello";
    }

    @GetMapping("/hello")
    public String hello() {
        System.out.println("This is the controller");
        return "Hello World";
    }
}