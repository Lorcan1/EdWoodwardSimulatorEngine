package com.example.matchEngine.services.playerDecisions;

import com.example.matchEngine.engine.GameState;
import com.example.matchEngine.services.inGameActionCalculations.carryCalculations.CarryCalculations;
import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassCalculateSuccess;
import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassChooseReceiver;
import com.example.matchEngine.services.inGameActionCalculations.tackleCalculations.TackleCalculateSuccess;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Setter
@Component
@Slf4j
public class DefenderDecisions implements PlayerDecisions {
    private Random random;
    private PassChooseReceiver passChooseReceiver;
    private PassCalculateSuccess passCalculateSuccess;
    private TackleCalculateSuccess tackleCalculateSuccess;
    private CarryCalculations carryCalculations;

    @Autowired
    public DefenderDecisions(PassCalculateSuccess passCalculateSuccess, PassChooseReceiver passChooseReceiver, TackleCalculateSuccess tackleCalculateSuccess, CarryCalculations carryCalculations, Random random) {
        this.passCalculateSuccess = passCalculateSuccess;
        this.passChooseReceiver = passChooseReceiver;
        this.tackleCalculateSuccess = tackleCalculateSuccess;
        this.carryCalculations = carryCalculations;
        this.random = random;

    }

    public GameState playerMakeDecision(GameState gameState) {
        //possible decisions, pass to goalkeeper, pass to other defender, pass to midfielder, get tackles, carry the ball forward
        //but the decision depends on what area of the pitch the player is in

        //how do we pick which player has received the ball?

        //probably should have a function here - does player receive the pass or do they put themselves under pressure increasing the risk of a dodgy pass or do they loose it
        //or should that be incorporated in the switch statement
        // lets try three tiers for now, good pass, loose pass, wayward pass
        //lets try two for now
        // there needs to be information passed between functions about the passers stats and the pass receivers stats
        //lets just assign them random chance for now
        int randomChance = random.nextInt(100) + 1; // 1-100 -influenced by pitch strata, as in there shouldn't be any chance of shooting unless the defender is in midfield or attack.
//        int randomChance = 54;
        log.info(String.valueOf(randomChance));
        if (randomChance <= 18) { //pass to goalkeeper
            gameState.setPitchPoss(gameState.getHomeTeamPoss() ? 0 : 4);
            gameState.setAction(passCalculateSuccess.calcPassSuccess(gameState, gameState.getAttackingTeam().getGk(), gameState.getDefendingTeam().getSt(), "Very Low") ? "ballOnTheLine" : "oneOnOne");
        } else if (randomChance <= 36) {//pass to other defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
            //defenders probably so this a lot
            gameState.setPitchPoss(gameState.getHomeTeamPoss() ? 1 : 3);
            Player passReceiverDef = passChooseReceiver.whichPlayerReceivesTheBall(gameState.getPlayerInPosses(),gameState.getAttackingTeam(), "defender");

            gameState.setAction(passCalculateSuccess.calcPassSuccess(gameState, passReceiverDef, gameState.getDefendingTeam().getSt(), "Low") ? "ballInDefence" : "ballInAttack");
        } else if (randomChance <= 54) {  //pass to midfielder
            gameState.setPitchPoss(2);
            Player passReceiverMid = passChooseReceiver.whichPlayerReceivesTheBall(gameState.getPlayerInPosses(),gameState.getAttackingTeam(), "midfielder");
            gameState.setAction(passCalculateSuccess.calcPassSuccess(gameState, passReceiverMid, gameState.getDefendingTeam().getSt(), "Medium") ? "ballInMidfield" : "ballInMidfield");
        } else if (randomChance <= 72) {  //pass to attacker
            gameState.setPitchPoss(gameState.getHomeTeamPoss() ? 3 : 1);//this doesn't need to be sent to the next function, ball will still be in that zone it's just a matter of who's in possess
            Player passReceiverAtt = passChooseReceiver.whichPlayerReceivesTheBall(gameState.getPlayerInPosses(),gameState.getAttackingTeam(), "attacker");
            gameState.setAction(passCalculateSuccess.calcPassSuccess(gameState, passReceiverAtt, gameState.getDefendingTeam().getSt(), "High") ? "ballInAttack" : "ballInDefence");
        } else if (randomChance <= 90) { //attempts a carry
            gameState.setAction(carryCalculations.calcCarrySuccess(gameState.getPlayerInPosses(), gameState.getDefendingTeam().getSt()) ? "ballInDefence" : "counterAttack"); //only counter if looses the ball between certain strata?
        } else if (randomChance <= 95) //should be a bad touch check too/ incorporated
            gameState.setAction(tackleCalculateSuccess.calcTackleResult(gameState,gameState.getPitchPoss(), gameState.getPlayerInPosses()));
        else {
            //has a shot - the randomChance should have this at 0 if it isn't in the midfield
            gameState.setAction("shot");
        }
        return gameState;
    }
}