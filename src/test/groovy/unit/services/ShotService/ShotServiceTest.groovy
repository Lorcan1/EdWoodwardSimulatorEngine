package unit.services.ShotService

import com.example.matchEngine.engine.GameState
import com.example.matchEngine.services.ShotService.ShotCalculations
import com.example.matchEngine.services.ShotService.ShotService
import com.example.model.Shot
import com.example.model.player.Goalkeeper
import spock.lang.Specification

class ShotServiceTest extends Specification {
    def "Test ShotService"(){
        def shotCalculations = Mock(ShotCalculations)
        def homeGoalkeeper = Mock(Goalkeeper)
        def awayGoalkeeper = Mock(Goalkeeper)
        ShotService shotService = new ShotService(shotCalculations, homeGoalkeeper, awayGoalkeeper)
        Shot shot = new Shot()
        GameState gameState = new GameState();
        gameState.setHomeTeamPoss(true)



        when:
        shotCalculations.isShotSuccesful(_, _, _, _, _)  >> "goal"
        shotService.calculateShotChance(gameState, false, 1)
//        shotService.calculateShotChance(_ as OutfieldPlayer, 1, 1, true, shot) >> "goal"

        then:
        assert gameState.getShot().getIsGoal() == true

    }
}
