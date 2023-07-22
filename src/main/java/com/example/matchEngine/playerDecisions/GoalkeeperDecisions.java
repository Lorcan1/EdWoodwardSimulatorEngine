package com.example.matchEngine.playerDecisions;

import com.example.matchEngine.engine.MatchEngine;
import com.example.matchEngine.updateStats.UpdateInGamePlayerStats;
import com.example.model.player.Player;
import com.example.team.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalkeeperDecisions {



    private UpdateInGamePlayerStats updateInGamePlayerStats;
    private MatchEngine matchEngine;


    public GoalkeeperDecisions(UpdateInGamePlayerStats updateInGamePlayerStats, MatchEngine matchEngine) {
        this.updateInGamePlayerStats = updateInGamePlayerStats;
        this.matchEngine = matchEngine;
    }


    public String goalkeeperMakeDecision(int pitchPos, boolean homeTeamPoss, Player playerInPosses, Team attackingTeam,
                                       Team defendingTeam) {
        //possible decisions, pass to Defender, pass to Midfielder, pass to attacker
        //obvs alot of the passes to Midfielders and attackers go astray
        //goo time to talk about traits, David de Gea probably hoofs it fairly often

        //how do we pick which player has recieved the ball?


        // there needs to be information passed between functions about the passers stats and the pass recievers stats


        int randomChance = 1;
        switch (randomChance) {
            case 1: //pass to other defender/fullback - how do they pick another defender - fullbacks shouldn't pass to the other full back that much
                pitchPos = homeTeamPoss ? 1 : 3;
                Player passReceiverDef = calcPassReceiver(attackingTeam);
                return calcPassSuccess(playerInPosses, passReceiverDef, defendingTeam.getSt()) ? "ballInDefence" : "ballInAttack";
            case 2: //pass to midfielder
                pitchPos = 2;
                Player passReceiverMid = calcPassReceiver(attackingTeam);
                return calcPassSuccess(playerInPosses, passReceiverMid, defendingTeam.getSt()) ? "ballInMidfield" : "ballInMidfield";
            case 3: //pass to attacker
                pitchPos = homeTeamPoss ? 3 : 1; //this doesnt need to be sent to the next function, ball will still be in that zone it's just a matter of whos in possess
                Player passReceiverAtt = calcPassReceiver(attackingTeam);
                return calcPassSuccess(playerInPosses, passReceiverAtt, defendingTeam.getSt()) ? "ballInAttack" : "ballInDefence";
            case 4: //attempts a carry
                return calcCarrySuccess(playerInPosses, defendingTeam.getSt()) ? "ballInMidfield" : "counterAttack";
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

    public Player calcPassReceiver(Team attackingTeam){
        return attackingTeam.getGk();
    }

    public boolean calcCarrySuccess(Player playerInPoss, Player marker){
        return true;
    }

}
