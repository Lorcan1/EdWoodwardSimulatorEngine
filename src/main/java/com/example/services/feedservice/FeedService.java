package com.example.services.feedservice;

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
    private List feedList;
    private List feedServiceHelperList;
    private int counter;


    @Autowired
    public FeedService(Random random) {
        this.random = random;
    }

    public void initilizeFeedService() {
        feedList = new ArrayList();
        feedServiceHelperList = new ArrayList<FeedServiceHelper>();
        this.counter = 0;
    }

    public void getRandomResponse(GameState gameState, String club) {
        HashMap<String, PlayerAction> playerActions = gameState.getPlayerActions();
        if (playerActions == null) {
            return;
        }
        for (Map.Entry<String, PlayerAction> entry : playerActions.entrySet()) {
            String key = entry.getKey();
            PlayerAction playerAction = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + playerAction);
            List emptyList = new ArrayList();
            List responses = playerAction.getResponseList(emptyList);

            String initialResponse = (String) responses.get(random.nextInt(responses.size()));

            if (playerAction.getType() == "pass") {
                responseGenerator(String.valueOf(gameState.getTime()), club, playerAction.getPlayer1(), ((Pass) playerAction).getPlayer2(), initialResponse, playerAction.getType());
            } else if (playerAction.getType() == "tackle") { //this will be exactly the same - can probably be split into a list of actions with/without "player2"
                responseGenerator(String.valueOf(gameState.getTime()), club, playerAction.getPlayer1(), ((Tackle) playerAction).getPlayer2(), initialResponse, playerAction.getType());
            }
        }
    }

    private void responseGenerator(String time, String club, String player1, String player2, String initalResponse, String type) {
        String staticResponse = time + " - " + club + " - ";
        String addedPlayer2 = initalResponse.replace("player2", player2);
        feedServiceHelperList.add(new FeedServiceHelper(type, staticResponse + player1 + addedPlayer2));

    }

    public List postProcess(List<FeedServiceHelper> feedServiceHelperList) {
        return reducePassString(feedServiceHelperList);
    }

    public List reducePassString(List<FeedServiceHelper> feedInnerClassList) {
        for (int currentPointer = 0; currentPointer <= (feedInnerClassList.size() -1); currentPointer++) {
            if(feedInnerClassList.get(currentPointer).type.equals("pass")){
                for(int nextNonPass = currentPointer + 1; nextNonPass<= (feedInnerClassList.size() - 1); nextNonPass++){
                    if(!feedInnerClassList.get(nextNonPass).type.equals("pass") || nextNonPass == (feedInnerClassList.size()-1)){
                        feedList.add(feedInnerClassList.get(currentPointer).feedResponse);
                        feedList.add("They're keeping possession well");
                        feedList.add(feedInnerClassList.get(nextNonPass).feedResponse);
                        currentPointer = nextNonPass;
                        break;
                    }
                }
            } else{
                feedList.add(feedInnerClassList.get(currentPointer).feedResponse);
            }

        }
        return (List) feedList;
    }

}