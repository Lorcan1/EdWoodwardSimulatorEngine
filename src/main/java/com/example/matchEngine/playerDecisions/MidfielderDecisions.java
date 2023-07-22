package com.example.matchEngine.playerDecisions;

import com.example.matchEngine.engine.MatchEngine;
import com.example.matchEngine.updateStats.UpdateInGamePlayerStats;
import com.example.model.player.Player;
import com.example.team.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;
@Getter
@Setter
public class MidfielderDecisions extends PlayerDecisions {
    private UpdateInGamePlayerStats updateInGamePlayerStats;
    private MatchEngine matchEngine;
    private Random random = new Random();


    public MidfielderDecisions(UpdateInGamePlayerStats updateInGamePlayerStats, MatchEngine matchEngine) {
        this.updateInGamePlayerStats = updateInGamePlayerStats;
        this.matchEngine = matchEngine;
    }

    // *** this should be superclassed
    public String midfielderMakeDecision(int pitchPos, boolean homeTeamPoss, Player playerInPosses, Team attackingTeam,
                                       Team defendingTeam) {
        //possible decisions, pass to goalkeeper, pass to  defender, pass to other midfielder, get tackles, carry the ball forward
        //but the decision depends on what area of the pitch the player is in

        //how do we pick which player has recieved the ball?

        //probably should have a function here - does player receive the pass or do they put themselves under pressure increasing the risk of a dodgy pass or do they loose it
        //or should that be incorporated in the switch statement
        // lets try three tiers for now, good pass, loose pass, wayward pass
        //lets try two for now
        // there needs to be information passed between functions about the passers stats and the pass recievers stats
        //lets just assign them random chance for now
        int randomChance = random.nextInt(100) + 1; // 1-100 -fluenced by pitch strata,a as in there shouldnt be any chance of shooting unless the defender is in midfield or attack.
        if(randomChance <= 20) { //pass to goalkeeper
            matchEngine.setPitchPos(homeTeamPoss ? 0 : 4);
            return calcPassSuccess(playerInPosses, attackingTeam.getGk(), defendingTeam.getSt(), "Very Low") ? "ballOnTheLine" : "oneOnOne";
        } else if(randomChance <= 40) {//pass to other defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
            matchEngine.setPitchPos(homeTeamPoss ? 1 : 3);
            Player passReceiverDef = calcPassReceiver(attackingTeam);
            return calcPassSuccess(playerInPosses, passReceiverDef, defendingTeam.getSt(), "Low") ? "defenderPoss" : "ballInAttack";
        } else if(randomChance <= 60 ) {  //pass to midfielder
            matchEngine.setPitchPos(2);
            Player passReceiverMid = calcPassReceiver(attackingTeam);
            return calcPassSuccess(playerInPosses, passReceiverMid, defendingTeam.getSt(), "Medium") ? "ballInMidfield" : "ballInMidfield";
        } else if(randomChance <= 80 ) {  //pass to attacker
            matchEngine.setPitchPos(homeTeamPoss ? 3 : 1);//this doesnt need to be sent to the next function, ball will still be in that zone it's just a matter of whos in possess
            Player passReceiverAtt = calcPassReceiver(attackingTeam);
            return calcPassSuccess(playerInPosses, passReceiverAtt, defendingTeam.getSt(), "High") ? "ballInAttack" : "ballInDefence";
        } else if(randomChance <= 95) { //attempts a carry
            return calcCarrySuccess(playerInPosses, defendingTeam.getSt()) ? "defenderPos" : "counterAttack"; //only counter if looses the ball between certain strata?
        }else{
            //has a shot - the randomChance should have this at 0 if it isnt in the midfield
            return "shot";
        }
    }

    public boolean calcPassSuccess(Player playerInPoss, Player passReceiver, Player marker, String possibleRisk){
        //going to vary depending on the part of the pitch, ignore for now
        //there should be a much higher chance of getting turned over in attack than in defence
        //if it fails then change the attacking team
        int randomChance = 0;
        switch (possibleRisk) { // use other factor to make the random chance is interesting
            case "Very Low": //pass to other defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
                //players attributes should have a say here
                randomChance = random.nextInt(200) + 1; //increasing the bound increases the chance of this happening
                break;
            // if the stat is two low then they miss the pass
            // the players skill stats should affect it here
            case "Low":
                randomChance = random.nextInt(150) + 1;
                break;
            case "Medium":
                randomChance = random.nextInt(100) + 1;
                break;
            case "High":
                randomChance = random.nextInt((6)) + 1;
                break;

        }

        if(randomChance > 1){ //pass is succesful
            playerInPoss = passReceiver;
            updateInGamePlayerStats.updatePassStat(playerInPoss.getLastName());
            updateInGamePlayerStats.updateTouchStat(passReceiver.getLastName());
            return true;
        } else{
            matchEngine.changePossession();
            return false;
        }
    }

    //these should be put in a super class. Also a good chance to work on changing logic from a parent class
    //in a child class

    public Player calcPassReceiver(Team attackingTeam){
        return attackingTeam.getGk();
    }

    public boolean calcCarrySuccess(Player playerInPoss, Player marker){
        //should tackles only be possible here? According to tackle definition the ball can go to either team
        //but must be removed from the player currently in posession
        //for attackers this can be changed to dribble
        //change strata to midfield
        return true;
    }


}
