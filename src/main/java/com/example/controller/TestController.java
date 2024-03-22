package com.example.controller;

import com.example.simulation.Simulate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private final Simulate simulate;

    @Autowired
    public TestController(Simulate simulate) {
        this.simulate = simulate;
    }

    @PostMapping("/test")
    public void test(@RequestParam("homeTeam") String homeTeam, @RequestParam("awayTeam") String awayTeam) {
        // Use the service
        simulate.simulateMatch(homeTeam, awayTeam);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}