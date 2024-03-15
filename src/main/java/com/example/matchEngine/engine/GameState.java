package com.example.matchEngine.engine;

import com.example.model.Shot;
import com.example.model.player.Player;
import com.example.team.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class GameState {
    Player playerInPosses;
    Team attackingTeam;
    Team defendingTeam;
    Boolean homeTeamPoss;
    int pitchPoss;
    String action;
    String possLost = " ";
    HashMap<String, String> playerStatsToBeUpdated = new HashMap();
    Shot shot;
}