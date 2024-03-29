package com.example.services;

import java.util.*;

public class FeedService {
    private List feed;

    private Map<String, List<String>> responses;

    public FeedService(List feed) {
        this.responses = new HashMap<String, List<String>>();
        responses.put("action1", Arrays.asList(" is tackled and looses the ball. Great tackle by player2 ", " is dispossessed by player2 ", " looses it. Solid challenge from player2 "));
        responses.put("goal",Arrays.asList(" scores", " finishes a fine move", " puts it past player2", " smashes it past player2"));
        this.feed = feed;
    }



    public void ResponsePicker(String playerName, String club, String action){
        //there should probably be a number of reactions to a certain event, like if someone is tavckled and
        //looses the ball we can say x was tackled or x was dispossessed etc

//        responses.put("action1", Arrays.asList("is tackled and looses the ball. Great tackle by", "is dispossessed by"));
//        responses.put("action2", Arrays.asList("Response4", "Response5", "Response6"));
//        responses.put("action3", Arrays.asList("Response7", "Response8", "Response9"));
    }

    private void responseGenerator(String time, String club, String player1, String player2, String initalResponse){
        String staticResponse = time + " - " + club + " - ";
        String addedPlayer2 = initalResponse.replace("player2", player2);
        feed.add(staticResponse + player1 + addedPlayer2);

    }
    public void getRandomResponse(String time, String club, String player1, String player2, String action) {
        List<String> responseList = responses.get(action);
        Random rand = new Random();
        String initialResponse =  responseList.get(rand.nextInt(responseList.size()));
        responseGenerator(time,club,player1,player2,initialResponse);
    }
}
