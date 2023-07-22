package com.example.matchEngine.updateStats;

import com.example.model.Match;

public class UpdateInGameMatchStats {

    private Match match;
    public UpdateInGameMatchStats(Match match){
        this.match = match;
    }

    public void updateMatchStats(String action){
        switch (action){
            case "kickOff":
                kickOff();
                break;
            case "homeDefencePoss":
                break;
            case "midfieldPoss":
            case "loosBallMidfield":
                break;
            case "attackerRecievesBall": //  it's not a throughBall
                break;
            case "throughBall": //not all attackers recieve perfect through balls, some have to create own chances with ball to feet/dribbling/pace
                break;
        }
    }
    public void kickOff(){

    }
}
