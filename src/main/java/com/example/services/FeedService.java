package com.example.services;

import com.example.matchEngine.engine.GameState;
import com.example.model.playeraction.PlayerAction;
import com.example.model.playeraction.pass.Pass;
import com.example.model.playeraction.tackle.Tackle;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Getter
@Setter
public class FeedService {

    private Random random;
    private List feed = new ArrayList();
    private Map<String, List<String>> responses = new HashMap<String, List<String>>();

    @Autowired
    public FeedService(Random random) {
        this.random = random;
    }

    public void feedServiceSetup(){
        responses.put("Tackle", Arrays.asList(" is tackled and looses the ball. Great tackle by player2 ", " is dispossessed by player2 ", " looses it. Solid challenge from player2 "));
        responses.put("Goal",Arrays.asList(" scores", " finishes a fine move", " puts it past player2", " smashes it past player2"));
        responses.put("Pass",Arrays.asList(" passes to player2"));
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
        String initialResponse =  responseList.get(random.nextInt(responseList.size()));
        responseGenerator(time,club,player1,player2,initialResponse);
    }

    public void getRandomResponse(GameState gameState, String club) {
        HashMap<String, PlayerAction> playerActions = gameState.getPlayerActions();
        if(playerActions == null) {
            return;
        }
        for (Map.Entry<String, PlayerAction> entry : playerActions.entrySet()) {
            String key = entry.getKey();
            PlayerAction playerAction = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + playerAction);

            List<String> responseList = responses.get(playerAction.getClassName());
            String initialResponse =  responseList.get(random.nextInt(responseList.size()));

            if(playerAction instanceof Pass){
                responseGenerator(String.valueOf(gameState.getTime()), club, playerAction.getPlayer1(), ((Pass) playerAction).getPlayer2(), initialResponse);
            } else if(playerAction instanceof Tackle){ //this will be exactly the same - can probably be split into a list of actions with/without "player2"
                responseGenerator(String.valueOf(gameState.getTime()), club, playerAction.getPlayer1(), ((Tackle) playerAction).getPlayer2(), initialResponse);

            }
        }
//        String initialResponse =  responseList.get(random.nextInt(responseList.size()));
//        responseGenerator(gameState.getTime(),club,gameState.getPlayerStatsToBeUpdated(),player2,initialResponse);
        //depending on the action, different logic needs to be implemented, ie if a goal is scored, player2 should be a goalkeeper
        //not sure about tackling and how that will work
    }
}
