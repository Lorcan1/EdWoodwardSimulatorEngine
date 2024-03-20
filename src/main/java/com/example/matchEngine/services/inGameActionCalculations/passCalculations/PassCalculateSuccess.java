package com.example.matchEngine.services.inGameActionCalculations.passCalculations;

import com.example.matchEngine.engine.GameState;
import com.example.model.player.Player;

import java.util.HashMap;
import java.util.Random;

public class PassCalculateSuccess {
    private Random random;
    public PassCalculateSuccess(Random random){
        this.random = random;
    }
    public boolean calcPassSuccess(GameState gameState, Player passReceiver, Player marker, String possibleRisk){
        //going to vary depending on the part of the pitch, ignore for now
        //there should be a much higher chance of getting turned over in attack than in defence
        //if it fails then change the attacking team
        int randomChance = 0;
        switch (possibleRisk) { // use other factor to make the random chance is interesting
            case "Very Low": //pass to other defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
                //players attributes should have a say here
                randomChance = random.nextInt(200) + 1; //increasing the bound increases the chance of this happening
                break;
            // if the stat is two low then they miss the pass
            // the players skill stats should affect it here
            case "Low":
                randomChance = random.nextInt(150) + 1;
                break;
            case "Medium":
                randomChance = random.nextInt(50) + 1;
                break;
            case "High":
                randomChance = random.nextInt((3)) + 1;
                break;
        }

        if(randomChance > 1){ //pass is succesful
            gameState.setPlayerInPosses(passReceiver);
            HashMap<String, String> playerStatsToBeUpdated = gameState.getPlayerStatsToBeUpdated();
            playerStatsToBeUpdated.put(gameState.getPlayerInPosses().getLastName(), "pass");
            playerStatsToBeUpdated.put(passReceiver.getLastName(), "touch");
            gameState.setPlayerStatsToBeUpdated(playerStatsToBeUpdated);
            return true;
        } else{
            gameState.setPossLost("pass");
            //who gets the ball?
            return false;
        }
    }
}