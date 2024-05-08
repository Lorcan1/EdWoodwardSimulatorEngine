package com.example.services

import com.example.matchEngine.engine.GameState
import com.example.model.playeraction.PlayerAction
import com.example.model.playeraction.pass.Pass
import com.example.services.feedservice.FeedService
import com.example.services.feedservice.FeedServiceHelper
import spock.lang.Specification;

class FeedServiceTest extends Specification{

    def "get valid response gamestata"(){
        given:
        FeedService feedService = new FeedService(new Random());
        feedService.initilizeFeedService()
        Pass pass = new Pass();
        pass.setPlayer1("testPlayerPasser")
        pass.setPlayer2("testPlayerReciever")

        GameState gameState = new GameState()
        HashMap<String, PlayerAction> playerActions = new HashMap<>();
        playerActions.put("testPlayerPasser", pass)
        gameState.setPlayerActions(playerActions)

        feedService.getRandomResponse(gameState, "MCFC")

        expect:
        assert feedService.getFeedServiceHelperList() != null
    }


    def 'test reducePassString'(){
        given:
        List testList = new ArrayList()
        FeedService feedService = new FeedService(new Random())
        feedService.setFeedList(testList)

        FeedServiceHelper feedServiceHelper1 = new FeedServiceHelper("pass", "its a pass from Dave")
        FeedServiceHelper feedServiceHelper2 = new FeedServiceHelper("pass", "its a pass from Mike")
        FeedServiceHelper feedServiceHelper3 = new FeedServiceHelper("shot", "its a shot")
        FeedServiceHelper feedServiceHelper4 = new FeedServiceHelper("pass", "its a pass from John")
        FeedServiceHelper feedServiceHelper5 = new FeedServiceHelper("pass", "its a pass from Mark")
        FeedServiceHelper feedServiceHelper6 = new FeedServiceHelper("pass", "its a pass from Mick")

       List<FeedServiceHelper> feedServiceHelperList = new ArrayList()

        feedServiceHelperList.add(feedServiceHelper1)
        feedServiceHelperList.add(feedServiceHelper2)
        feedServiceHelperList.add(feedServiceHelper3)
        feedServiceHelperList.add(feedServiceHelper4)
        feedServiceHelperList.add(feedServiceHelper5)
        feedServiceHelperList.add(feedServiceHelper6)

        feedService.initilizeFeedService()
        feedService.reducePassString(feedServiceHelperList)


    }

}