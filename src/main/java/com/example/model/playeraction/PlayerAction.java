package com.example.model.playeraction;

import com.example.model.player.InGamePlayerStats;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class PlayerAction {
    Boolean homeTeamPoss;
    String player1;
    String type;

    public abstract List getResponseList(List response);

//    public abstract void updateStats(InGamePlayerStats playerStats);
//
//    public abstract void getClassName

}