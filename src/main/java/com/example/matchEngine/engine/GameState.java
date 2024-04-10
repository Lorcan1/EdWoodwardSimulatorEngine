package com.example.matchEngine.engine;

import com.example.model.playeraction.PlayerAction;
import com.example.model.player.Player;
import com.example.team.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class GameState {
    Player playerInPosses;
    String lastPasserName;
    Team attackingTeam;
    Team defendingTeam;
    Boolean homeTeamPoss;
    int pitchPoss;
    String action;
    String possLost = " ";
    HashMap<String, String> playerStatsToBeUpdated = new HashMap();
    HashMap<String, PlayerAction> playerActions = new HashMap<>();
    PlayerAction playerAction;
    int time = 0;
    Boolean isGoal = Boolean.FALSE;
}