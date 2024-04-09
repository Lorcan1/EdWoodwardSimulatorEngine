package com.example.matchEngine.services.UpdateStats;

import com.example.model.InGameMatchStats;
import com.example.model.playeraction.PlayerAction;
import com.example.model.playeraction.shot.Shot;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Getter
@Component
public class UpdateInGameMatchStats {

    InGameMatchStats inGameMatchStats;
    int totalTime;
    int[] possession;
    int[] totalPossession;

    public UpdateInGameMatchStats(InGameMatchStats inGameMatchStats){
        this.inGameMatchStats = inGameMatchStats;
        this.totalTime = 90;
        this.possession = new int[2]; // Array to store possession for each team
        this.totalPossession = new int[2]; // Array to store total possession for each team


    }

    public void updateMatchStats(Boolean homeTeamPoss, HashMap<String, String> playersStatsToBeUpdated, PlayerAction shot){
      updatePossessionStats(homeTeamPoss);
      updateShotAndGoalStats(playersStatsToBeUpdated, homeTeamPoss, (Shot) shot);
    }

    private void updatePossessionStats(Boolean homeTeamPoss){
        int team;
        if(homeTeamPoss)
            team = 0;
        else

            team = 1;
        possession[team]++;

        // Update total possession for the team
        totalPossession[team] += possession[team];

        // Calculate possession percentage for each team
        double possessionPercentageTeamA = (double) totalPossession[0] / (totalPossession[0] + totalPossession[1]) * 100;
        double possessionPercentageTeamB = (double) totalPossession[1] / (totalPossession[0] + totalPossession[1]) * 100;


        // Calculate possession percentage for both teams
        inGameMatchStats.setHomePoss((int) possessionPercentageTeamA) ;
        inGameMatchStats.setAwayPoss((int) possessionPercentageTeamB) ;
    }

    private void updateShotAndGoalStats(HashMap<String, String> playersStatsToBeUpdated, Boolean homeTeamPoss, Shot shot){
        for (String playerName : playersStatsToBeUpdated.keySet()) {
            if(playersStatsToBeUpdated.get(playerName).equals("shot")){
                updateShots(playerName,homeTeamPoss, shot);
            } else if(playersStatsToBeUpdated.get(playerName).equals("goal")){
                updateShots(playerName,homeTeamPoss, shot);
                updateGoals(playerName, homeTeamPoss, shot);
            }
        }
    }

    private void updateShots(String playerName, Boolean homeTeamPoss, Shot shot) {
        if (homeTeamPoss) {
            inGameMatchStats.setHomeShots(inGameMatchStats.getHomeShots() + 1);
            inGameMatchStats.setHomexG(inGameMatchStats.getHomexG() + shot.getXG());
            if (shot.getOnTarget()) {
                inGameMatchStats.setHomeShotsOnT(inGameMatchStats.getHomeShotsOnT() + 1);
            } else {
                inGameMatchStats.setAwayShots(inGameMatchStats.getAwayShots() + 1);
                inGameMatchStats.setAwayxG(inGameMatchStats.getAwayxG() + shot.getXG());
                if (shot.getOnTarget()) {
                    inGameMatchStats.setAwayShotsOnT(inGameMatchStats.getAwayShotsOnT() + 1);
                }
            }
        }
    }

    private void updateGoals(String scorerName, Boolean homeTeamPoss, Shot goal){
        if(!scorerName.equals(goal.getGoal().getScorerName())){
            throw new IllegalArgumentException("Temp");
        }
        if(homeTeamPoss) {
            inGameMatchStats.getHomeGoals().add(goal.getGoal());
            inGameMatchStats.setHomeScore(inGameMatchStats.getHomeScore() + 1);
        }else {
            inGameMatchStats.getAwayGoals().add(goal.getGoal());
            inGameMatchStats.setAwayScore(inGameMatchStats.getAwayScore() + 1);
        }
    }
}
