package com.example.matchEngine.services.inGameActionCalculations.tackleCalculations;

import com.example.model.player.Player;
import org.springframework.stereotype.Component;

@Component
public class TackleCalculateSuccess {

    public String calcTackleResult(int pitchPos, Player playerInPosses) {
        if (true)
            return "ballInDefence"; //tackled but recovered ball
        else {
//            gameState.setPossLost("tackled");
            return "ballInAttack";
        }
    }
}
