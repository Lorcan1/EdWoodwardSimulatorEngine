package com.example.matchEngine.services.inGameActionCalculations.carryCalculations;

import com.example.model.player.Player;

public class CarryCalculations {
    public boolean calcCarrySuccess(Player playerInPoss, Player marker) {
        //should tackles only be possible here? According to tackle definition the ball can go to either team
        //but must be removed from the player currently in posession
        //for attackers this can be changed to dribble
        //change strata to midfield
        //harcoded for now - NEEDS TO BE CHANGED
        return true;

    }
}
