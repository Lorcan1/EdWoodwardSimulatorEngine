package com.example.matchEngine.engine;

import com.example.matchEngine.services.playerDecisions.AttackerDecisions;
import com.example.matchEngine.services.playerDecisions.DefenderDecisions;
import com.example.matchEngine.services.playerDecisions.MidfielderDecisions;
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotService;
import com.example.matchEngine.services.UpdateStats.UpdateInGameMatchStats;
import com.example.matchEngine.services.UpdateStats.UpdateInGamePlayerStats;
import com.example.model.InGameMatchStats;
import com.example.model.player.Goalkeeper;
import com.example.model.playeraction.PlayerAction;
import com.example.services.FeedService;
import com.example.team.Team;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@Slf4j
public class MatchEngineLogic {

    @Autowired
    private DefenderDecisions defenderDecisions;

    @Autowired
    private MidfielderDecisions midfielderDecisions;

    @Autowired
    private AttackerDecisions attackerDecisions;

    @Autowired
    private UpdateInGamePlayerStats updateInGamePlayerStats;

    @Autowired
    private UpdateInGameMatchStats updateInGameMatchStats;

    @Autowired
    ShotService shotService;

    @Autowired
    FeedService feedService;


    Team homeTeam;
    Team awayTeam;

    GameState gameState;

    private boolean startOfGame = true;

    String homeTeamName;
    String awayTeamName;

    String homeTeamNameAbbrev;
    String awayTeamNameAbbrev;

    int time;


    public void setupMatch() {
        updateInGamePlayerStats.setHomeTeamPlayersStats(updateInGamePlayerStats.initializeInGamePlayerStats(homeTeam.getPlayers(), true));
        updateInGamePlayerStats.setAwayTeamPlayersStats(updateInGamePlayerStats.initializeInGamePlayerStats(awayTeam.getPlayers(), false));
        shotService.setHomeGoalkeeper((Goalkeeper) homeTeam.getGk());
        shotService.setAwayGoalkeeper((Goalkeeper) awayTeam.getGk());
        updateInGameMatchStats.setInGameMatchStats(new InGameMatchStats());
        updateInGameMatchStats.getInGameMatchStats().setHomeTeam(homeTeamName);
        updateInGameMatchStats.getInGameMatchStats().setAwayTeam(awayTeamName);
    }

    public void simulateMatch(){
        time = 0;
        startOfGame = true;
        gameState = new GameState();
        playGame("kickOff", false);
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

    public void playGame(String action, Boolean gameFinished) { //how do we deal with teamInPossesion/ attacking team? - keep it as an instance variable?
        while (!gameFinished) {
            switch (action) {
                case "goalKick":
                    //
                case "kickOff":
                    gameState.setAction(kickOff());
                    startOfGame = false;
                    break;
                case "ballOnTheLine": //add logic here
                    if (gameState.getHomeTeamPoss())
                        gameState.setPlayerInPosses(homeTeam.getDcl());
                    else
                        gameState.setPlayerInPosses(awayTeam.getDcr());
                    gameState.setAction("ballInDefence"); //needs to be coded
                    break;
                case "ballInDefence":
                    gameState = defenderDecisions.playerMakeDecision(gameState);
                    break;
                case "ballInMidfield":
                    gameState = midfielderDecisions.playerMakeDecision(gameState);
                    break;
                case "ballInAttack":
                    gameState = attackerDecisions.playerMakeDecision(gameState);
                case "oneOnOne":
                    //implemnt logic
                    gameState.setAction("ballInAttack"); //placeholder
                case "shot":
                    gameState = shotService.calculateShotChance(gameState, false, time);

                    //currently i am passing in those 5 values, why not make a gamestate object or add them to match and pass it in and return it??
            }

            action = gameState.getAction();
            log.info(action);
            for (Map.Entry<String, PlayerAction> entry : gameState.getPlayerActions().entrySet()) {
                log.info("Key: {}, Value: {}", entry.getKey(), entry.getValue());
            }

            //should the above be before or after the updating of the stats. HomeTeamPoss is changed above and is used
            // to decide who took the actions in the below
            //can actions from both teams be present in PlayersStatsToBeUpdated?

            //if a player attempts a pass and fails then what happens currently and what should happen?


            if(gameState.getIsGoal()) {
                updateInGameMatchStats.updateMatchStats(gameState.homeTeamPoss, gameState.getPlayerActions());
                gameState.setIsGoal(false);
            }

            updateInGamePlayerStats.updateInGamePlayerStats(gameState.getPlayerActions()); //going to remove PlayerActions here

            if(gameState.getHomeTeamPoss()){
                feedService.getRandomResponse(gameState, homeTeamNameAbbrev);
            } else{
                feedService.getRandomResponse(gameState, awayTeamNameAbbrev);
            }



            if (!gameState.getPossLost().isEmpty()) {

                changePossession(gameState.getPossLost());
                gameState.setPossLost("");
                log.info("Possesion Lost, Player in Poss:" + gameState.getPlayerInPosses().getLastName());
            }

            gameState.setPlayerActions(new HashMap<>());
            //we need to clear a lot of things from gamestate now !!!
            time = time + 1;
            if (time > 120) {
                gameFinished = true;
            }
            gameState.setTime(time);
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