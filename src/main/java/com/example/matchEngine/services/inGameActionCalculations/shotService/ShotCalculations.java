package com.example.matchEngine.services.inGameActionCalculations.shotService;

import com.example.matchEngine.SaveCalculation;
import com.example.matchEngine.engine.GameState;
import com.example.model.playeraction.shot.Goal;
import com.example.model.playeraction.shot.Shot;
import com.example.model.player.Goalkeeper;
import com.example.model.player.OutfieldPlayer;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Component
public class ShotCalculations { //add oneOnOne calcs to this class
    SaveCalculation saveCalculation;

    public List<String> possLostValues = Arrays.asList("ballInDefence", "goalKick", "goal"); //currently theres no logic for players shooting and the same team
    //retaining poss

    public ShotCalculations(SaveCalculation saveCalculation ){ //want to try and sprinify this
        this.saveCalculation = saveCalculation;
    }

    //shots on target per team is hovering around 1/3 compared to total shots
    //making a seperate long shots function
    //the position on the pitch should matter a lot here
    public String isShotSuccesful(OutfieldPlayer attacker, int shotDifficulty, int shotSuccessful,  Shot shot, Goalkeeper goalkeeper) {
        int shotChance = (shotSuccessful + attacker.getComposure()) - (shotDifficulty - attacker.getFinishing());
        if (shotChance > 0 ){
            if(shotChance > 80){    //what is the likelihood of this, see python script. With the compusure and finishing seems to be abput 1/10
                //great finish      //weaker players should have less chance of scoring here but more chance against weaker goalkeepers
                //were going to need the passer for the key chances/ assists
                return "kickoff";
            } else{ //this means on target right?
                if(saveCalculation.isShotSaved(shotChance, goalkeeper)){
                    shot.setOnTarget(true);
                    return "ballInDefence";
                } else{
                    return "kickoff";
                }
            }

        } else{
            return "goalKick";
        }
    }

    public String isLongShotSuccesful(OutfieldPlayer attacker, int shotDifficulty, int shotSuccessful, Shot shot, Goalkeeper goalkeeper) {
        int shotChance = (shotSuccessful + attacker.getComposure()) - (shotDifficulty - attacker.getLongShots());

        if (shotChance > 0 ){
            if(shotChance > 95){    //what is the likelihood of this, see python script. With the compusure and finishing seems to be abput 1/10
                //great finish
                // weaker players should have less chance of scoring here but more chance against weaker goalkeepers
                return "kickoff";
            } else{

                if(saveCalculation.isLongShotSaved(shotChance, goalkeeper)){
                    //changePossesion
                    return "ballInDefence";
                } else{
                    return "kickoff";
                }
            }
        } else{
            return "goalKick";
        }
    }
    public Goal createGoal(GameState gameState, int time){
        return new Goal(gameState.getPlayerInPosses().getLastName(), gameState.getLastPasserName(), Integer.toString(time));
    }
}
