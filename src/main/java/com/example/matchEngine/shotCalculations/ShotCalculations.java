package com.example.matchEngine.shotCalculations;

import com.example.matchEngine.SaveCalculation;
import com.example.matchEngine.engine.MatchEngine;
import com.example.matchEngine.updateStats.UpdateInGamePlayerStats;
import com.example.model.player.Goalkeeper;
import com.example.model.player.OutfieldPlayer;
import com.example.model.player.Player;

import java.util.Random;

public class ShotCalculations { //add oneOnOne calcs to this class
    private Random random = new Random();
    SaveCalculation saveCalculation = new SaveCalculation();
    private MatchEngine matchEngine;
    private Goalkeeper goalkeeper;
    private UpdateInGamePlayerStats updateInGamePlayerStats;

    public ShotCalculations(MatchEngine matchEngine, UpdateInGamePlayerStats updateInGamePlayerStats){
        this.matchEngine = matchEngine;
        this.updateInGamePlayerStats = updateInGamePlayerStats;

    }


    public String calculateShotChance(OutfieldPlayer attacker, boolean isLongShot){
        updateInGamePlayerStats.updateShotStat(attacker.getLastName());
        int shotDifficulty = random.nextInt(100) + 1; //certain players with good decision-making probably won't take really unlikely shots?
        int shotSuccessful = random.nextInt(100) + 1;
        float xG = (shotDifficulty / 100);
        if(isLongShot)
           return isLongShotSuccesful(attacker, shotDifficulty, shotSuccessful);
        else
           return isShotSuccesful(attacker, shotDifficulty, shotSuccessful);
    }


    //shots on target per team is hovering around 1/3 compared to total shots
    //making a seperate long shots function
    //the position on the pitch should matter a lot here
    public String isShotSuccesful(OutfieldPlayer attacker, int shotDifficulty, int shotSuccessful) {
        int shotChance = (shotSuccessful + attacker.getComposure()) - (shotDifficulty - attacker.getFinishing());
        if(matchEngine.isHomeTeamPoss())
            goalkeeper = (Goalkeeper) matchEngine.getHomeTeam().getGk();
        else
            goalkeeper = (Goalkeeper) matchEngine.getAwayTeam().getGk();
        if (shotChance > 0 ){
            if(shotChance > 80){    //what is the likelihood of this, see python script. With the compusure and finishing seems to be abput 1/10
                //great finish      //weaker players should have less chance of scoring here but more chance against weaker goalkeepers
                //were going to need the passer for the key chances/ assists
                matchEngine.goalScored(attacker);
                return "kickOff";
            } else{

                if(saveCalculation.isShotSaved(shotChance, goalkeeper)){
                    matchEngine.changePossession("temp");
                    return "ballInDefence";
                } else{
                    matchEngine.goalScored(attacker);
                    return "kickOff";
                }
            }

        } else{
            return "goalKick";
        }
    }

    public String isLongShotSuccesful(OutfieldPlayer attacker, int shotDifficulty, int shotSuccessful) {
        int shotChance = (shotSuccessful + attacker.getComposure()) - (shotDifficulty - attacker.getLongShots());
        if(matchEngine.isHomeTeamPoss())
            goalkeeper = (Goalkeeper) matchEngine.getHomeTeam().getGk();
        else
            goalkeeper = (Goalkeeper) matchEngine.getAwayTeam().getGk();

        if (shotChance > 0 ){
            if(shotChance > 95){    //what is the likelihood of this, see python script. With the compusure and finishing seems to be abput 1/10
                //great finish
                // weaker players should have less chance of scoring here but more chance against weaker goalkeepers
                return "kickOff";
            } else{

                if(saveCalculation.isLongShotSaved(shotChance, goalkeeper)){
                    matchEngine.changePossession("temp");
                    return "ballInDefence";
                } else{
                    matchEngine.goalScored(attacker);
                    return "kickOff";

                }
            }

        } else{
            return "goalKick";
        }
    }
}
