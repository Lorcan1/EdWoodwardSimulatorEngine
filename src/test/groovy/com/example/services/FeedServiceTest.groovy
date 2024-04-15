package com.example.services

import com.example.matchEngine.engine.GameState
import com.example.model.playeraction.PlayerAction
import com.example.model.playeraction.pass.Pass
import com.example.services.FeedService
import spock.lang.Specification;

class FeedServiceTest extends Specification{

    def "get valid response gamestata"(){
        given:
        List testList = new ArrayList();
        FeedService feedService = new FeedService(new Random());
        feedService.setFeed(testList)
        Pass pass = new Pass();
        pass.setPlayer1("testPlayerPasser")
        pass.setPlayer2("testPlayerReciever")

        GameState gameState = new GameState();
        HashMap<String, PlayerAction> playerActions = new HashMap<>();
        playerActions.put("testPlayerPasser", pass)
        gameState.setPlayerActions(playerActions)

        feedService.getRandomResponse(gameState, "MCFC")

        expect:
        assert feedService.getFeed() != null
    }

}