package com.example.services;

import java.util.*;

public class FeedService {
    private List feed = new ArrayList();
    private Map<String, List<String>> responses;

    public FeedService() {
        this.responses = new HashMap<String, List<String>>();
        responses.put("action1", Arrays.asList(" is tackled and looses the ball. Great tackle by ", " is dispossessed by ", " looses it. Solid challenge from "));
    }



    public void ResponsePicker(String playerName, String club, String action){
        //there should probably be a number of reactions to a certain event, like if someone is tavckled and
        //looses the ball we can say x was tackled or x was dispossessed etc

//        responses.put("action1", Arrays.asList("is tackled and looses the ball. Great tackle by", "is dispossessed by"));
//        responses.put("action2", Arrays.asList("Response4", "Response5", "Response6"));
//        responses.put("action3", Arrays.asList("Response7", "Response8", "Response9"));
    }

    public List responseGenerator(String time, String club, String player1, String player2, String initalResponse){
        String staticResponse = time + " - " + club + " - ";
        feed.add(staticResponse + player1 + initalResponse + player2);
        return feed;

    }
    public List getRandomResponse(String time, String club, String player1, String player2, String action) {
        List<String> responseList = responses.get(action);
        Random rand = new Random();
        String initialResponse =  responseList.get(rand.nextInt(responseList.size()));
        return responseGenerator(time,club,player1,player2,initialResponse);
    }
}
