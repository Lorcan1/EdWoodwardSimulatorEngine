package com.example.matchEngine.services.UpdateStats;

import com.example.matchEngine.services.conditioningservice.ConditioningService;
import com.example.model.playeraction.PlayerAction;
import com.example.model.player.InGamePlayerStats;
import com.example.model.player.Player;
import com.example.model.playeraction.pass.Pass;
import com.example.model.playeraction.shot.Goal;
import com.example.model.playeraction.shot.Shot;
import com.example.model.playeraction.touch.Touch;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Component
@Slf4j
public class  UpdateInGamePlayerStats{

    @Autowired
    ConditioningService conditioningService;

    HashMap<String,InGamePlayerStats> homeTeamPlayersStats;
    HashMap<String,InGamePlayerStats> awayTeamPlayersStats;

    public HashMap<String, InGamePlayerStats> initializeInGamePlayerStats(List<Player> players, Boolean home){
        HashMap<String,InGamePlayerStats> tempHashMap = new HashMap<>();
        for (Player player : players) {
            InGamePlayerStats inGamePlayerStats = new InGamePlayerStats();
            inGamePlayerStats.setName(player.getLastName());
            inGamePlayerStats.setPos(player.getStartingPosition());
            inGamePlayerStats.setHome(home);
            tempHashMap.put(player.getLastName(), inGamePlayerStats);
        }
        return tempHashMap;
    }
    public void updateInGamePlayerStats(HashMap<String, PlayerAction> playersStatsToBeUpdated) {
        for (String playerName : playersStatsToBeUpdated.keySet()) {
            PlayerAction playerAction = playersStatsToBeUpdated.get(playerName);
            if (homeTeamPlayersStats.containsKey(playerName)) {
                updateInGamePlayerStatsLogic(playerName, playerAction, homeTeamPlayersStats);
            } else if (awayTeamPlayersStats.containsKey(playerName)) {
                updateInGamePlayerStatsLogic(playerName, playerAction, awayTeamPlayersStats);
            }
        }
    }
    public void updateInGamePlayerStatsLogic(String playerName, PlayerAction playerAction, HashMap<String,InGamePlayerStats> playerStatsHashMap ){
        InGamePlayerStats playerStats = playerStatsHashMap.get(playerName);
        if(playerAction.getType() ==  "pass") {
            playerStats.setPasses(playerStats.getPasses() + 1);
            playerStats.setTouches(playerStats.getTouches() + 1);
        }else if (playerAction.getType() ==  "missedPass") {
                playerStats.setMissedPasses(playerStats.getMissedPasses() + 1);

        } else if(playerAction.getType() == "touch") {
//            playerStats.setTouches(playerStats.getTouches() + 1);
            //no touch type
        } else  if(playerAction.getType() == "shot"){
            playerStats.setShots(playerStats.getShots() + 1);

            Shot shot = (Shot) playerAction;
            DecimalFormat df = new DecimalFormat("#.##");
            String formattedTotalXG = df.format(playerStats.getXG() + shot.getXG());
            playerStats.setXG(Double.parseDouble(formattedTotalXG));
            if(shot.getOnTarget())
                playerStats.setOnTarget(playerStats.getOnTarget() + 1);

            playerStats.setTouches(playerStats.getTouches() + 1);

        } else  if(playerAction.getType() == "goal"){
            playerStats.setGoals(playerStats.getGoals() + 1);
            Goal goal = (Goal) playerAction;
            if(goal.getAssisterName()!= null) {
                InGamePlayerStats assisterStats = playerStatsHashMap.get(goal.getAssisterName());
                assisterStats.setAssists(assisterStats.getAssists() + 1);
            }
        } else if(playerAction.getType() == "kickOff"){
            //implement logic
        }else if(playerAction.getType() == "tackle"){
            playerStats.setTackles(playerStats.getTackles() + 1);
        }else {
            throw new IllegalArgumentException(playerAction.getType()); // more of a checker currently to nake sure every action is recorded
        }
    }

    public void conditioning(){
        List<InGamePlayerStats> homeTeamPlayerStats = new ArrayList<>(homeTeamPlayersStats.values());
        List<InGamePlayerStats> awayTeamPlayerStats = new ArrayList<>(awayTeamPlayersStats.values());

        conditioningService.conditioningService(homeTeamPlayerStats);
        conditioningService.conditioningService(awayTeamPlayerStats);
    }

    public void calculatePassPercentage() {
        List<InGamePlayerStats> homeTeamPlayerStats = new ArrayList<>(homeTeamPlayersStats.values());
        List<InGamePlayerStats> awayTeamPlayerStats = new ArrayList<>(awayTeamPlayersStats.values());
        calculatePassPercentage(homeTeamPlayerStats);
        calculatePassPercentage(awayTeamPlayerStats);
    }

    public void calculatePassPercentage(List<InGamePlayerStats> teamPlayerStats){
        for(InGamePlayerStats inGamePlayerStats: teamPlayerStats){
            if(inGamePlayerStats.getPasses() == 0){
                inGamePlayerStats.setPassCompletionPercentage(100);
            } else if(inGamePlayerStats.getMissedPasses() == 0){
                inGamePlayerStats.setPassCompletionPercentage(100);
            } else {
                double percentage = (double) inGamePlayerStats.getPasses() / (inGamePlayerStats.getPasses() + inGamePlayerStats.getMissedPasses()) * 100;
                Double percentage1 = Double.parseDouble(String.format("%.2f", percentage));
                inGamePlayerStats.setPassCompletionPercentage(percentage1);
            }
        }

    }
}
