package unit.matchEngine.services.UpdateStats

import com.example.matchEngine.services.UpdateStats.UpdateInGameMatchStats
import com.example.model.Goal
import com.example.model.InGameMatchStats
import com.example.model.Shot
import spock.lang.Specification

class UpdateInGameMatchStatsTest extends Specification {

    def "Test Possession"(){
        UpdateInGameMatchStats updateInGameMatchStats = new UpdateInGameMatchStats(new InGameMatchStats())
        Boolean homeTeamPoss = Boolean.TRUE
        HashMap<String, String> playersStatsToBeUpdated = new HashMap<>()
        Shot shot = null

        updateInGameMatchStats.updateMatchStats(homeTeamPoss,playersStatsToBeUpdated, shot)

        expect:
        updateInGameMatchStats.getInGameMatchStats().getHomePoss() == 100
        updateInGameMatchStats.getInGameMatchStats().getAwayPoss() == 0

    }

    def "Test Shot"(){
        UpdateInGameMatchStats updateInGameMatchStats = new UpdateInGameMatchStats(new InGameMatchStats())
        Boolean homeTeamPoss = Boolean.TRUE
        HashMap<String, String> playersStatsToBeUpdated = new HashMap<>()
        Shot shot = new Shot(0.5, true, false, null)

        playersStatsToBeUpdated.put("Temp", "shot")

        updateInGameMatchStats.updateMatchStats(homeTeamPoss,playersStatsToBeUpdated, shot)

        expect:
        updateInGameMatchStats.getInGameMatchStats().getHomeShots() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomeShotsOnT() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomexG() == 0.5

    }


    def "Test Goal"(){
        UpdateInGameMatchStats updateInGameMatchStats = new UpdateInGameMatchStats(new InGameMatchStats())
        Boolean homeTeamPoss = Boolean.TRUE
        HashMap<String, String> playersStatsToBeUpdated = new HashMap<>()
        Goal goal = new Goal("temp", "temp", "1")
        Shot shot = new Shot(0.5, true, true, goal)

        playersStatsToBeUpdated.put("temp", "goal")

        updateInGameMatchStats.updateMatchStats(homeTeamPoss,playersStatsToBeUpdated, shot)

        expect:
        updateInGameMatchStats.getInGameMatchStats().getHomeShots() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomeShotsOnT() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomexG() == 0.5
        updateInGameMatchStats.getInGameMatchStats().getHomeScore() == 1
        updateInGameMatchStats.getInGameMatchStats().getHomeGoals().get(0) == goal

    }

}
