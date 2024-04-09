package com.example.matchEngine.services.playerDecisions

import com.example.matchEngine.engine.GameState
import com.example.matchEngine.services.inGameActionCalculations.carryCalculations.CarryCalculations
import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassCalculateSuccess
import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassChooseReceiver
import com.example.matchEngine.services.inGameActionCalculations.tackleCalculations.TackleCalculateSuccess
import com.example.matchEngine.services.playerDecisions.DefenderDecisions
import com.example.model.player.Player
import com.example.team.Team
import spock.lang.Specification

class DefenderDecisionsTest extends Specification {

    def "Pass to Goalkeeper - Random Chance < 18"() {
        given: "A mocked GameState and Random"
        def gameState = Mock(GameState)
        def random = Mock(Random)
        def passCalculateSuccess = Mock(PassCalculateSuccess)
        def passChooseReceiver = Mock(PassChooseReceiver)
        def carryCalculations = Mock(CarryCalculations)
        def tackleCalculateSuccess = Mock(TackleCalculateSuccess)

        def attackingTeam = Mock(Team)
        def defendingTeam = Mock(Team)
        def gk = Mock(Player)
        def st = Mock(Player)

        and: "A playerMakeDecision method instance"
        def defenderDecisions = new DefenderDecisions(passCalculateSuccess, passChooseReceiver, tackleCalculateSuccess, carryCalculations,random)

        when: "playerMakeDecision is called"
        random.nextInt(100) >> 10 // Simulate a random chance that would lead to passing to the goalkeeper
        gameState.getHomeTeamPoss() >> true
        passCalculateSuccess.calcPassSuccess(_, _, _, _) >> true
        gameState.getAttackingTeam() >> attackingTeam // Ensure attackingTeam is not null
        gameState.getDefendingTeam() >> defendingTeam
        attackingTeam.getGk() >> gk
        defendingTeam.getSt() >> st
        defenderDecisions.playerMakeDecision(gameState)

        then: "The GameState is updated accordingly"
        // Assertions to verify the GameState is updated as expected
        1 * gameState.setPitchPoss(0) // Assuming the home team possesses the ball
        1 * gameState.setAction("ballOnTheLine") // Assuming the pass is successful
    }
}