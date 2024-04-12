package com.example.matchEngine.services.inGameActionCalculations.passCalculations

import com.example.matchEngine.engine.GameState
import com.example.model.player.Player
import com.example.model.playeraction.PlayerAction
import com.example.team.Team
import spock.lang.Specification

class PassChooseReceiverTest extends Specification{

    def "Sample Name"() {
        given: "A mocked GameState and Random"
        def gameState = Mock(GameState)
        def random = Mock(Random)
        Player playerInPoss = Mock(Player)
        Team attackingTeam = Mock(Team)
        String position = "defender"
        Player playerToBeReturned = Mock(Player)

        def passChooseReceiver = Mock(PassChooseReceiver)
        def mockPassChooseReceiver = Spy(PassChooseReceiver)

        playerInPoss.setStartingPosition("DCL")


        HashMap<String, Player> hashmap = new HashMap<>();
        hashmap.put("temp", playerInPoss)
        attackingTeam.setDefenders(hashmap)

        and:""
        mockPassChooseReceiver.calcMostProbablePassReciever(*_) >> playerToBeReturned

        when:""
        attackingTeam.getDefenders() >> hashmap;
        playerInPoss.getStartingPosition() >> "DCL"

        Player returnedPlayer = mockPassChooseReceiver.whichPlayerReceivesTheBall(playerInPoss, attackingTeam, position)

        then:""
        assert returnedPlayer == (playerToBeReturned)

    }


}
