package com.example.matchEngine.engine;

import com.example.model.player.Player;
import com.example.team.Team;
import lombok.Getter;
import lombok.Setter;

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
}