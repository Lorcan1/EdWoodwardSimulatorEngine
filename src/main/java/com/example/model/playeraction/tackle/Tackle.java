package com.example.model.playeraction.tackle;

import com.example.model.playeraction.PlayerAction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Tackle extends PlayerAction {
    String player2;
    String type = "tackle";

    @Override
    public List getResponseList(List responses) {
        responses.add(" is tackled and looses the ball. Great tackle by player2");
        responses.add(" is dispossessed by player2" );
        responses.add("looses it. Solid challenge from player2 ");
        return responses;
    }
}
