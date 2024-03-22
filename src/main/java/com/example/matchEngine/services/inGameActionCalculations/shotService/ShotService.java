package com.example.matchEngine.services.inGameActionCalculations.shotService;

import com.example.matchEngine.engine.GameState;
import com.example.model.Shot;
import com.example.model.player.Goalkeeper;
import com.example.model.player.OutfieldPlayer;

import java.util.Random;


public class ShotService {
    private Random random = new Random();
    ShotCalculations shotCalculations;
    private Goalkeeper homeGoalkeeper;
    private Goalkeeper awayGoalkeeper;

    public GameState calculateShotChance(GameState gameState, boolean isLongShot, int time){
        Shot shot = new Shot();
        int shotDifficulty = random.nextInt(100) + 1; //certain players with good decision-making probably won't take really unlikely shots?
        int shotSuccessful = random.nextInt(100) + 1;

        Double xG = (double) (shotDifficulty / 100);
        shot.setXG(xG);
        String action;
        Goalkeeper goalkeeper = getGoalkeeper(gameState.getHomeTeamPoss());
        if(isLongShot)
            action = shotCalculations.isLongShotSuccesful((OutfieldPlayer) gameState.getPlayerInPosses(), shotDifficulty, shotSuccessful, shot, goalkeeper);
        else
            action = shotCalculations.isShotSuccesful((OutfieldPlayer) gameState.getPlayerInPosses(), shotDifficulty, shotSuccessful,  shot, goalkeeper);
        if(shotCalculations.possLostValues.contains(action))
            gameState.setPossLost("temp");
        gameState.setAction(action);
        if(action.equals("goal")){
            shot.setGoal(shotCalculations.createGoal(gameState, time));
            shot.setIsGoal(true);
        }
        gameState.setShot(shot);
        return gameState;

    }

    public Goalkeeper getGoalkeeper(Boolean homeTeamPoss){
        if(homeTeamPoss)
            return homeGoalkeeper;
        else
            return awayGoalkeeper;

    }
}
