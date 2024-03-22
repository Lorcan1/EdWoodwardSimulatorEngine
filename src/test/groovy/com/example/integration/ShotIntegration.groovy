package com.example.integration

import com.example.matchEngine.SaveCalculation
import com.example.matchEngine.engine.GameState
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotCalculations
import com.example.matchEngine.services.inGameActionCalculations.shotService.ShotService
import com.example.model.player.Goalkeeper
import com.example.model.player.OutfieldPlayer
import spock.lang.Specification

class ShotIntegration extends Specification{

        def "Shot Integration Test"(){
            def homeGoalkeeper = Mock(Goalkeeper)
            def awayGoalkeeper = Mock(Goalkeeper)

            ShotService shotService = new ShotService(new ShotCalculations(new SaveCalculation()), homeGoalkeeper, awayGoalkeeper);

            GameState gameState = new GameState()
            OutfieldPlayer attacker = new OutfieldPlayer()
            attacker.setComposure(10)
            attacker.setFinishing(10)
            gameState.setPlayerInPosses(attacker)

            gameState.setHomeTeamPoss(true)

            when:
            shotService.calculateShotChance(gameState, false, 1)

            then:
            assert true;

        }

}
