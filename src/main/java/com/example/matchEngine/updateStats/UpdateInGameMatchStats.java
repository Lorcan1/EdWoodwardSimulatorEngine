package com.example.matchEngine.updateStats;

import com.example.model.InGameMatchStats;
import com.example.model.Shot;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class UpdateInGameMatchStats {

    InGameMatchStats inGameMatchStats;
    int homePossession;
    int awayPossession;
    int totalTime;

    public UpdateInGameMatchStats(InGameMatchStats inGameMatchStats){
        this.inGameMatchStats = inGameMatchStats;
        this.homePossession = 0;
        this.awayPossession = 0;
        this.totalTime = 90;


    }

    public void updateMatchStats(Boolean homeTeamPoss, HashMap<String, String> playersStatsToBeUpdated, Shot shot){
      updatePossessionStats(homeTeamPoss);
      updateShotAndGoalStats(playersStatsToBeUpdated, homeTeamPoss,shot);
    }

    public void updatePossessionStats(Boolean homeTeamPoss){
        if(homeTeamPoss) {
            homePossession += 10; //this is dependent on how many times the total number of run throughs divided by the total time
        }else {
            awayPossession += 10;
        }

        // Calculate possession percentage for both teams
        inGameMatchStats.setHomePoss(homePossession / totalTime * 100) ;
        inGameMatchStats.setAwayPoss(awayPossession / totalTime * 100) ;
    }

    public void updateShotAndGoalStats(HashMap<String, String> playersStatsToBeUpdated, Boolean homeTeamPoss, Shot shot){
        for (String playerName : playersStatsToBeUpdated.keySet()) {
            if(playersStatsToBeUpdated.get(playerName).equals("shot")){
                updateShots(playerName,homeTeamPoss, shot);
            } else if(playersStatsToBeUpdated.get(playerName).equals("goal")){
                updateShots(playerName,homeTeamPoss, shot);
                updateGoals(playerName, homeTeamPoss, shot);
            }
        }
    }

    public void updateShots(String playerName, Boolean homeTeamPoss, Shot shot) {
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

    public void updateGoals(String scorerName, Boolean homeTeamPoss, Shot goal){
        if(!scorerName.equals(goal.getGoal().getScorerName())){
            throw new IllegalArgumentException("Temp");
        }
        if(homeTeamPoss)
            inGameMatchStats.getHomeGoals().add(goal.getGoal());
        else
            inGameMatchStats.getAwayGoals().add(goal.getGoal());
    }
}
