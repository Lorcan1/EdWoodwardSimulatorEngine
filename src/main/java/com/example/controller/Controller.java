package com.example.controller;

import com.example.simulation.Simulate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private ControllerLogic controllerLogic;

    public Controller(ControllerLogic controllerLogic) {
        this.controllerLogic = controllerLogic;
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