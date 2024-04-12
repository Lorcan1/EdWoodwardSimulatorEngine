//package com.example.matchEngine.playerDecisions;
//
//import com.example.matchEngine.engine.GameState;
//
//import com.example.matchEngine.services.inGameActionCalculations.carryCalculations.CarryCalculations;
//import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassCalculateSuccess;
//import com.example.matchEngine.services.inGameActionCalculations.passCalculations.PassChooseReceiver;
//import com.example.matchEngine.services.inGameActionCalculations.tackleCalculations.TackleCalculateSuccess;
//import com.example.matchEngine.services.playerDecisions.PlayerDecisions;
//import com.example.model.player.Player;
//import com.example.team.Team;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Random;
//@Getter
//@Setter
//@Component
//public class MidfielderDecisions implements PlayerDecisions {
//
//    private Random random;
//    private PassChooseReceiver passChooseReceiver;
//    private PassCalculateSuccess passCalculateSuccess;
//    private TackleCalculateSuccess tackleCalculateSuccess;
//    private CarryCalculations carryCalculations;
//
//
//    @Autowired
//    public MidfielderDecisions(PassCalculateSuccess passCalculateSuccess, PassChooseReceiver passChooseReceiver, TackleCalculateSuccess tackleCalculateSuccess, CarryCalculations carryCalculations, Random random) {
//        this.passCalculateSuccess = passCalculateSuccess;
//        this.passChooseReceiver = passChooseReceiver;
//        this.tackleCalculateSuccess = tackleCalculateSuccess;
//        this.carryCalculations = carryCalculations;
//        this.random = random;
//    }
//    // *** this should be superclassed
//    public GameState playerMakeDecision(GameState gameState) {
//        //possible decisions, pass to goalkeeper, pass to  defender, pass to other midfielder, get tackles, carry the ball forward
//        //but the decision depends on what area of the pitch the player is in
//
//        //how do we pick which player has recieved the ball?
//
//        //probably should have a function here - does player receive the pass or do they put themselves under pressure increasing the risk of a dodgy pass or do they loose it
//        //or should that be incorporated in the switch statement
//        // lets try three tiers for now, good pass, loose pass, wayward pass
//        //lets try two for now
//        // there needs to be information passed between functions about the passers stats and the pass recievers stats
//        //lets just assign them random chance for now
//        int randomChance = random.nextInt(100) + 1; // 1-100 -fluenced by pitch strata,a as in there shouldnt be any chance of shooting unless the defender is in midfield or attack.
//        if(randomChance <= 18) { //pass to goalkeeper
//            gameState.setPitchPoss(gameState.getHomeTeamPoss() ? 0 : 4);
//            gameState.setAction(passCalculateSuccess.calcPassSuccess(gameState, gameState.getAttackingTeam().getGk(), gameState.getDefendingTeam().getSt(), "Very Low") ? "ballOnTheLine" : "oneOnOne");
//        } else if(randomChance <= 36) {//pass to defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
//            gameState.setPitchPoss(gameState.getHomeTeamPoss() ? 1 : 3);
//            Player passReceiverDef = passChooseReceiver.whichPlayerReceivesTheBall(gameState.getAttackingTeam(), "defender");
//
//            return calcPassSuccess(playerInPosses, passReceiverDef, defendingTeam.getSt(), "Low") ? "ballInDefence" : "ballInAttack";
//        } else if(randomChance <= 54 ) {  //pass to other midfielder
//            matchEngine.setPitchPos(2);
//            Player passReceiverMid = calcPassReceiver(playerInPosses,attackingTeam, "midfielder");
//            return calcPassSuccess(playerInPosses, passReceiverMid, defendingTeam.getSt(), "Medium") ? "ballInMidfield" : "ballInMidfield";
//        } else if(randomChance <= 72 ) {  //pass to attacker
//            matchEngine.setPitchPos(homeTeamPoss ? 3 : 1);//this doesnt need to be sent to the next function, ball will still be in that zone it's just a matter of whos in possess
//            Player passReceiverAtt = calcPassReceiver(playerInPosses,attackingTeam, "attacker");
//            return calcPassSuccess(playerInPosses, passReceiverAtt, defendingTeam.getSt(), "High") ? "ballInAttack" : "ballInDefence";
//        } else if(randomChance <= 90) { //attempts a carry
//            return calcCarrySuccess(playerInPosses, defendingTeam.getSt()) ? "ballInAttack" : "counterAttack"; //only counter if looses the ball between certain strata?
//        } else if (randomChance <= 95) //should be a bad touch check too/ incoporated
//            return calcTackleResult(pitchPos,playerInPosses);
//        else{
//            //has a shot - the randomChance should have this at 0 if it isnt in the midfield
//            return "shot";
//        }
//    }
//
//
//
//    //these should be put in a super class. Also a good chance to work on changing logic from a parent class
//    //in a child class
//
//    public Player calcPassReceiver(Player playerInPoss, Team attackingTeam, String position){
//        //how should playerInPoss affect it?
//        // I assume DRs rarely (if ever) pass to dcls more than dcrs
//                return passCalculations.whichPlayerReceivesTheBall(attackingTeam, position);
//        }
//
//    public boolean calcCarrySuccess(Player playerInPoss, Player marker){
//        //should tackles only be possible here? According to tackle definition the ball can go to either team
//        //but must be removed from the player currently in posession
//        //for attackers this can be changed to dribble
//        //change strata to midfield
//        return true;
//    }
//
//    public String calcTackleResult(int pitchPos, Player playerInPosses){
//        if(true)
//        return "ballInDefence"; //tackled but recovered ball
//        else {
//            matchEngine.changePossession("tackled");
//            return "ballInAttack";
//        }
//
//
//    }
//
//
//
//}