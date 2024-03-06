package com.example.matchEngine.engine;

import com.example.matchEngine.playerDecisions.DefenderDecisions;
import com.example.matchEngine.playerDecisions.PlayerDecisions;
import com.example.matchEngine.updateStats.UpdateInGamePlayerStats;
import com.example.team.Team;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

//
//import players.*;
//import team.Team;
//import team.TeamSetup;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Random;
//
//
@Getter
@Setter
@Slf4j
public class MatchEngineLogic {

    Team homeTeam;
    Team awayTeam;

    GameState gameState = new GameState();

    private boolean gameFinished = false;
    private boolean startOfGame = true;

    String homeTeamName;
    String awayTeamName;

    int time = 0;

    private PlayerDecisions defenderDecisions = new DefenderDecisions();

    private UpdateInGamePlayerStats updateInGamePlayerStats = new UpdateInGamePlayerStats(homeTeam.getPlayers(), awayTeam.getPlayers());

    public MatchEngineLogic(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
    }

    public void simulateMatch() {
        playGame("kickOff");
//
    }



    public String kickOff() {
        if (startOfGame) {
            coinflip();
        }
        gameState.setPlayerInPosses(choosePlayerInPosses());
        //prob should turn posses over
        return "ballInDefence";
    }

    public void coinflip() { // add the logic here
        gameState.setAttackingTeam(homeTeam);

        gameState.setDefendingTeam(awayTeam);
        gameState.setHomeTeamPoss(true);
        gameState.setPitchPoss(1);
        ;
    }

    public Player choosePlayerInPosses() {
        return gameState.getAttackingTeam().getDcr();
    }

    public void playGame(String action) { //how do we deal with teamInPossesion/ attacking team? - keep it as an instance variable?
        while (!gameFinished) {
            switch (action) {
                case "goalKick":
                    //
                case "kickOff":
                    gameState.setAction(kickOff());
                    startOfGame = false;
                    break;
                case "ballOnTheLine":
                    if (gameState.getHomeTeamPoss())
                        gameState.setPlayerInPosses(homeTeam.getDcl());
                    else
                        gameState.setPlayerInPosses(awayTeam.getDcr());
                    gameState.setAction("ballInDefence"); //needs to be coded
                    break;
                case "ballInDefence":
                    gameState = defenderDecisions.playerMakeDecision(gameState);
                    break;
                //currently i am passing in those 5 values, why not make a gamestate object or add them to match and pass it in and return it??

            }

            updateInGamePlayerStats.updateInGamePlayerStats(gameState.getPlayerStatsToBeUpdated());
            action = gameState.getAction();
            if(!gameState.getPossLost().isEmpty()){
                changePossession(gameState.getPossLost());
                gameState.setPossLost(" ");
            }


            time = time + 1;
            if(time > 20){
                gameFinished = true;
            }



        }

    }

        public void changePossession(String howBallWasLost){ //is this attempting something similar to the next functiom
        Team temp = gameState.getAttackingTeam(); //temp = man
        gameState.setAttackingTeam(gameState.getDefendingTeam()); //attacking team = to
        gameState.setDefendingTeam(temp);

        if(howBallWasLost.equals("pass") || howBallWasLost.equals("tackle") || howBallWasLost.equals("temp")) { //other teams player needs to have ball, hardcode for now, should be in a new function which decided which oppo player gets ball
            if(gameState.getHomeTeamPoss()){ //obvs tackle and pass should be different aswell - NEEDS TO BE CHANGED
                gameState.setPlayerInPosses(awayTeam.getDcr());
            } else{
                gameState.setPlayerInPosses(homeTeam.getDcr());
            }
        }
        gameState.setHomeTeamPoss(!gameState.getHomeTeamPoss());

    }
}


