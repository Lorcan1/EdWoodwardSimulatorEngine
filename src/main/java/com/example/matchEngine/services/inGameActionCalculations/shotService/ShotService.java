package com.example.matchEngine.services.inGameActionCalculations.shotService;

import com.example.matchEngine.engine.GameState;
import com.example.model.playeraction.shot.Goal;
import com.example.model.playeraction.shot.Shot;
import com.example.model.player.Goalkeeper;
import com.example.model.player.OutfieldPlayer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Setter
@Component
public class ShotService {
    private Random random = new Random();

    ShotCalculations shotCalculations;

    private Goalkeeper homeGoalkeeper;
    private Goalkeeper awayGoalkeeper;

    public ShotService(ShotCalculations shotCalculations){
        this.shotCalculations = shotCalculations;
    }

    public GameState calculateShotChance(GameState gameState, boolean isLongShot, int time){
        Shot shot = new Shot();
        int shotDifficulty = random.nextInt(100) + 1; //certain players with good decision-making probably won't take really unlikely shots?
        int shotSuccessful = random.nextInt(100) + 1;

        Double xG = (double) (shotDifficulty / 100);
        shot.setXG(xG);
        shot.setPlayer1(gameState.getPlayerInPosses().getLastName());
        String action;
        Goalkeeper goalkeeper = getGoalkeeper(gameState.getHomeTeamPoss());
        if(isLongShot)
            action = shotCalculations.isLongShotSuccesful((OutfieldPlayer) gameState.getPlayerInPosses(), shotDifficulty, shotSuccessful, shot, goalkeeper);
        else
            action = shotCalculations.isShotSuccesful((OutfieldPlayer) gameState.getPlayerInPosses(), shotDifficulty, shotSuccessful,  shot, goalkeeper);
        if(shotCalculations.possLostValues.contains(action))
            gameState.setPossLost("temp"); //how will this be recoreded in player stats? maybe we can create a blocked shot object?
        gameState.setAction(action);
        if(action.equals("kickOff")){
            Goal goal = shotCalculations.createGoal(gameState, time);
            goal.setPlayer1(gameState.getPlayerInPosses().getLastName());
            shot.setGoal(goal);
            shot.setIsGoal(true);
            gameState.setIsGoal(true);
            if(gameState.getHomeTeamPoss())
                goal.setPlayer2(awayGoalkeeper.getLastName());
            else
                goal.setPlayer2(homeGoalkeeper.getLastName());
            gameState.getPlayerActions().put(gameState.getPlayerInPosses().getLastName(), shot);
            gameState.getPlayerActions().put(gameState.getPlayerInPosses().getLastName(),goal);
        } else{
            gameState.getPlayerActions().put(gameState.getPlayerInPosses().getLastName(), shot);

        }


        return gameState;

    }

    public Goalkeeper getGoalkeeper(Boolean homeTeamPoss){
        if(homeTeamPoss)
            return homeGoalkeeper;
        else
            return awayGoalkeeper;

    }
}
