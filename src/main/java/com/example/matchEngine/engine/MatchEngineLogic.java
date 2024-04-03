package com.example.matchEngine.engine;

import com.example.matchEngine.services.inGameActionCalculations.carryCalculations.CarryCalculations;
import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassCalculateSuccess;
import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassChooseReceiver;
import com.example.matchEngine.services.playerDecisions.DefenderDecisions;
import com.example.matchEngine.services.playerDecisions.PlayerDecisions;
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotCalculations;
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotService;
import com.example.matchEngine.services.UpdateStats.UpdateInGameMatchStats;
import com.example.matchEngine.services.UpdateStats.UpdateInGamePlayerStats;
import com.example.matchEngine.services.inGameActionCalculations.tackleCalculations.TackleCalculateSuccess;
import com.example.model.InGameMatchStats;
import com.example.model.player.Goalkeeper;
import com.example.team.Team;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
@Getter
@Setter
@Component
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

    @Autowired
    private PlayerDecisions defenderDecisions;

    @Autowired
    private UpdateInGamePlayerStats updateInGamePlayerStats;

    @Autowired
    private UpdateInGameMatchStats updateInGameMatchStats;

    @Autowired
    ShotService shotService;

    public void simulateMatch(String homeTeamName, String awayTeamName) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        updateInGamePlayerStats.setHomeTeamPlayersStats(updateInGamePlayerStats.initializeInGamePlayerStats(homeTeam.getPlayers()));
        updateInGamePlayerStats.setAwayTeamPlayersStats(updateInGamePlayerStats.initializeInGamePlayerStats(awayTeam.getPlayers()));
        playGame("kickOff");
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
                case "shot":
                    gameState = shotService.calculateShotChance(gameState, false, time);

                    //currently i am passing in those 5 values, why not make a gamestate object or add them to match and pass it in and return it??
            }

            action = gameState.getAction();
            log.info(action);

            //feedService here


            //should the above be before or after the updating of the stats. HomeTeamPoss is changed above and is used
            // to decide who took the actions in the below
            //can actions from both teams be present in PlayersStatsToBeUpdated?

            //if a player attempts a pass and fails then what happens currently and what should happen?

            updateInGamePlayerStats.updateInGamePlayerStats(gameState.getPlayerStatsToBeUpdated());
            updateInGameMatchStats.updateMatchStats(gameState.homeTeamPoss, gameState.getPlayerStatsToBeUpdated(), gameState.getShot());


            if (!gameState.getPossLost().isEmpty()) {
                changePossession(gameState.getPossLost());
                gameState.setPossLost(" ");
            }
            //we need to clear a lot of things from gamestate now !!!
            time = time + 1;
            if (time > 20) {
                gameFinished = true;
            }
        }
    }

    public void changePossession(String howBallWasLost) { //should prob be in a separate class
        Team temp = gameState.getAttackingTeam();
        gameState.setAttackingTeam(gameState.getDefendingTeam()); //attacking team = to
        gameState.setDefendingTeam(temp);

        if (howBallWasLost.equals("pass") || howBallWasLost.equals("tackle") || howBallWasLost.equals("temp")) { //other teams player needs to have ball, hardcode for now, should be in a new function which decided which oppo player gets ball
            if (gameState.getHomeTeamPoss()) { //obvs tackle and pass should be different aswell - NEEDS TO BE CHANGED
                gameState.setPlayerInPosses(awayTeam.getDcr());
            } else {
                gameState.setPlayerInPosses(homeTeam.getDcr());
            }
        }
        gameState.setHomeTeamPoss(!gameState.getHomeTeamPoss());

    }
}