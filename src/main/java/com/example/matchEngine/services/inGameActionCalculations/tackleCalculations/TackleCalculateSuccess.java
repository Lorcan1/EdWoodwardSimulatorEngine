package com.example.matchEngine.services.inGameActionCalculations.tackleCalculations;

import com.example.matchEngine.engine.GameState;
import com.example.model.player.Player;
import com.example.model.playeraction.tackle.Tackle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TackleCalculateSuccess {

    Random random;

    @Autowired
    public TackleCalculateSuccess(Random random){
        this.random = random;
    }

    public String calcTackleResult(GameState gameState, int pitchPos, Player playerInPosses) {
        int randomChance = random.nextInt(100) + 1;

        if (randomChance<= 50)
            return "ballInDefence"; //tackled but recovered ball
        else {
            gameState.setPossLost("tackle");
            Tackle tackle = Tackle.builder()
                    .player1(playerInPosses.getLastName())
                    .player2("Choose Tackler")
                    .build();
            gameState.getPlayerActions().put(playerInPosses.getLastName(), tackle);
            return "ballInAttack";
        }
    }
}
