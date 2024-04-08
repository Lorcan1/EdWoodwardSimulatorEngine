package com.example.matchEngine.services.inGameActionCalculations.ShotService

import com.example.matchEngine.engine.GameState
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotCalculations
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotService
import com.example.model.player.Goalkeeper
import spock.lang.Specification

class ShotServiceTest extends Specification {
    def "Test ShotService"(){
        def shotCalculations = Mock(ShotCalculations)
        def homeGoalkeeper = Mock(Goalkeeper)
        def awayGoalkeeper = Mock(Goalkeeper)
        ShotService shotService = new ShotService(shotCalculations)
        shotService.setHomeGoalkeeper(homeGoalkeeper)
        shotService.setAwayGoalkeeper(awayGoalkeeper)
        GameState gameState = new GameState();
        gameState.setHomeTeamPoss(true)

        when:
        shotCalculations.isShotSuccesful(_, _, _, _, _)  >> "goal"
        shotCalculations.possLostValues = ["goal"]
        shotService.calculateShotChance(gameState, false, 1)

        then:
        assert gameState.getShot().getIsGoal() == true

    }
}
