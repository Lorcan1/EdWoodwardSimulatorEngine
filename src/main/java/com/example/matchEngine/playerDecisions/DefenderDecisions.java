package com.example.matchEngine.playerDecisions;

import com.example.matchEngine.engine.MatchEngine;
import com.example.matchEngine.updateStats.UpdateInGamePlayerStats;
import com.example.model.player.Player;
import com.example.team.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefenderDecisions {
    private UpdateInGamePlayerStats updateInGamePlayerStats;
    private MatchEngine matchEngine;


    public DefenderDecisions(UpdateInGamePlayerStats updateInGamePlayerStats, MatchEngine matchEngine) {
        this.updateInGamePlayerStats = updateInGamePlayerStats;
        this.matchEngine = matchEngine;
    }


    public String defenderMakeDecision(int pitchPos, boolean homeTeamPoss, Player playerInPosses, Team attackingTeam,
                                       Team defendingTeam) {
        //possible decisions, pass to goalkeeper, pass to other defender, pass to midfielder, get tackles, carry the ball forward
        //but the decision depends on what area of the pitch the player is in

        //how do we pick which player has recieved the ball?

        //probably should have a function here - does player receive the pass or do they put themselves under pressure increasing the risk of a dodgy pass or do they loose it
        //or should that be incorporated in the switch statement
        // lets try three tiers for now, good pass, loose pass, wayward pass
        //lets try two for now
        // there needs to be information passed between functions about the passers stats and the pass recievers stats
            //lets just assign them random chance for now

        int randomChance = 0; // - influenced by pitch strata
        switch (randomChance) {
            case 0: //pass to goalkeeper
                pitchPos = homeTeamPoss ? 0 : 4;
                return calcPassSuccess(playerInPosses, attackingTeam.getGk(), defendingTeam.getSt()) ? "ballOnTheLine" : "oneOnOne";
            case 1: //pass to other defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
                pitchPos = homeTeamPoss ? 1 : 3;
                Player passReceiverDef = calcPassReceiver(attackingTeam);
                return calcPassSuccess(playerInPosses, passReceiverDef, defendingTeam.getSt()) ? "defenderPoss" : "ballInAttack";
            case 2: //pass to midfielder
                pitchPos = 2;
                Player passReceiverMid = calcPassReceiver(attackingTeam);
                return calcPassSuccess(playerInPosses, passReceiverMid, defendingTeam.getSt()) ? "ballInMidfield" : "ballInMidfield";
            case 3: //pass to attacker
                pitchPos = homeTeamPoss ? 3 : 1; //this doesnt need to be sent to the next function, ball will still be in that zone it's just a matter of whos in possess
                Player passReceiverAtt = calcPassReceiver(attackingTeam);
                return calcPassSuccess(playerInPosses, passReceiverAtt, defendingTeam.getSt()) ? "ballInAttack" : "ballInDefence";
            case 4: //attempts a carry
                return calcCarrySuccess(playerInPosses, defendingTeam.getSt()) ? "defenderPos" : "counterAttack";
            case 5:
                //has a shot - the randomChance should have this at 0 if it isnt in the midfield

        }

        return "temp";
    }

    public boolean calcPassSuccess(Player playerInPoss, Player passReceiver, Player marker){
        //going to vary depending on the part of the pitch, ignore for now
        //there should be a much higher chance of getting turned over in attack than in defence
        //if it fails then change the attacking team
        int randomChance = 1;
        if(randomChance == 1){ //pass is succesful
            playerInPoss = passReceiver;
            updateInGamePlayerStats.updatePassStat(playerInPoss.getLastName());
            updateInGamePlayerStats.updateTouchStat(passReceiver.getLastName());
            return true;
        } else{
            matchEngine.setAttackingTeam(matchEngine.getDefendingTeam());
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
