package com.example.matchEngine.services

import com.example.services.FeedService
import spock.lang.Specification;

class FeedServiceTest extends Specification{

    def "get valid response"(){
        given:
        List testList = new ArrayList();
        FeedService feedService = new FeedService(new Random());
        feedService.setFeed(testList)
        feedService.feedServiceSetup()
        feedService.getRandomResponse("1", "MCFC", "De Bruyne", "Romero", "action1")


        expect:
        assert feedService.getFeed() != null
    }

}