package com.example.matchEngine.services.inGameActionCalculations.ShotService

import com.example.matchEngine.engine.GameState
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotCalculations
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotService
import com.example.model.player.Goalkeeper
import com.example.model.player.OutfieldPlayer
import com.example.model.player.Player
import com.example.model.playeraction.shot.Shot
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
        def player = Mock(OutfieldPlayer)
        gameState.setPlayerInPosses(player)

        when:
        shotCalculations.isShotSuccesful(*_)  >> "kickOff"
        shotCalculations.possLostValues = ["kickOff"]
        player.getLastName() >> "scorer"
        shotService.calculateShotChance(gameState, false, 1)

        then:
        assert gameState.getPlayerActions().get("scorer").getType() == "shot"

        Shot shot =  gameState.getPlayerActions().get("scorer")
        assert shot.getIsGoal() == true

    }
}
