package com.example.matchEngine.playerDecisions;

import com.example.matchEngine.engine.GameState;
import com.example.model.player.Player;
import com.example.team.Team;

public interface PlayerDecisions {
    GameState playerMakeDecision(GameState gameState);
}
