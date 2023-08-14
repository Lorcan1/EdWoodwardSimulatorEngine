package com.example.matchEngine.playerDecisions;

import com.example.model.player.Player;
import com.example.team.Team;

public interface PlayerDecisions {
    String playerMakeDecision(int pitchPos, boolean homeTeamPoss, Player playerInPoss, Team attackingTeam, Team defendingTeam);
}
