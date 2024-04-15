package com.example.model.playeraction.pass;

import com.example.model.player.InGamePlayerStats;
import com.example.model.playeraction.PlayerAction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Pass extends PlayerAction {
    String player2;
    String type =  "pass";

    @Override
    public List getResponseList(List responses) {
        responses.add(" passes to player2");
        return responses;

    }
}
