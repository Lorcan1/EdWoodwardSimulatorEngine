package com.example.matchEngine.engine;

import com.example.model.player.Player;


public interface MatchEngineDecisions {
    int pitchPos = 0;
    Player playerInPosses = null;

    void setPlayerInPosses(Player player);
    void changePossession(String howBallWasLost);

    void setPitchPos(int pos);
}
