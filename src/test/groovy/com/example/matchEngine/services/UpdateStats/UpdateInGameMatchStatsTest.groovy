package com.example.matchEngine.services.UpdateStats

import com.example.model.playeraction.PlayerAction
import com.example.model.playeraction.shot.Goal
import com.example.model.InGameMatchStats
import com.example.model.playeraction.shot.Shot
import spock.lang.Specification

class UpdateInGameMatchStatsTest extends Specification {

    def "Test Possession"(){
        UpdateInGameMatchStats updateInGameMatchStats = new UpdateInGameMatchStats()
        updateInGameMatchStats.setInGameMatchStats(new InGameMatchStats())
        Boolean homeTeamPoss = Boolean.TRUE
        HashMap<String, PlayerAction> playersStatsToBeUpdated = new HashMap<>()

        updateInGameMatchStats.updateMatchStats(homeTeamPoss,playersStatsToBeUpdated)

        expect:
        updateInGameMatchStats.getInGameMatchStats().getHomePoss() == 100
        updateInGameMatchStats.getInGameMatchStats().getAwayPoss() == 0

    }

    def "Test Shot"(){
        UpdateInGameMatchStats updateInGameMatchStats = new UpdateInGameMatchStats()
        updateInGameMatchStats.setInGameMatchStats(new InGameMatchStats())
        Boolean homeTeamPoss = Boolean.TRUE
        HashMap<String, PlayerAction> playersStatsToBeUpdated = new HashMap<>()
        Shot shot = new Shot(0.5, true, false, null)

        playersStatsToBeUpdated.put("Temp", shot)

        updateInGameMatchStats.updateMatchStats(homeTeamPoss,playersStatsToBeUpdated)

        expect:
        updateInGameMatchStats.getInGameMatchStats().getHomeShots() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomeShotsOnT() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomexG() == 0.5


    }


    def "Test Goal"(){
        UpdateInGameMatchStats updateInGameMatchStats = new UpdateInGameMatchStats()
        updateInGameMatchStats.setInGameMatchStats(new InGameMatchStats())
        Boolean homeTeamPoss = Boolean.TRUE
        HashMap<String, PlayerAction> playersStatsToBeUpdated = new HashMap<>()
        Goal goal =  Goal.builder()
        .scorerName("tempGoal")
        .assisterName("temp")
        .time("1")
        .build()

        Shot shot = new Shot(0.5, true, true, goal)

        playersStatsToBeUpdated.put("tempShot", shot)
        playersStatsToBeUpdated.put("tempGoal", goal)

        updateInGameMatchStats.updateMatchStats(homeTeamPoss,playersStatsToBeUpdated)

        expect:
        updateInGameMatchStats.getInGameMatchStats().getHomeShots() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomeShotsOnT() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomexG() == 0.5
        updateInGameMatchStats.getInGameMatchStats().getHomeScore() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomeGoals().get(0) == goal

    }

}
