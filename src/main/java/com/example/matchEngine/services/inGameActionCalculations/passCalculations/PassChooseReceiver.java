package com.example.matchEngine.services.inGameActionCalculations.passCalculations;

import com.example.model.player.Player;
import com.example.team.Team;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PassChooseReceiver {
    private Random random;

    public PassChooseReceiver(Random random){
        this.random = random;

    }
    //to more realistically configure this should use the grid system if its ever implemented ...
    public Player whichPlayerReceivesTheBall(Player playerInPoss ,Team attackingTeam, String position) { //this should be changed to reflect what runs an attacker is making

        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        Player player4  = null;

        Boolean passIsWithinSameTier = true; //ie Midfielder to Midfielder
        Boolean passToDefender = false;

        switch (position) {
            case "defender":
                for (Map.Entry<String, Player> entry : attackingTeam.getDefenders().entrySet()) {
                    if(playerInPoss.equals(entry.getValue())){
                        if(playerInPoss.getStartingPosition() == "DL"){
                            player1 = attackingTeam.getDcl();
                            player2 = attackingTeam.getDcr();
                        } else if(playerInPoss.getStartingPosition() == "DCL"){
                            player1 = attackingTeam.getDl();
                            player2 = attackingTeam.getDr();
                        } else if(playerInPoss.getStartingPosition() ==("DCR")){
                            player1 = attackingTeam.getDr();
                            player2 = attackingTeam.getDcl();
                        }else if(playerInPoss.getStartingPosition() ==("DR")){
                            player1 = attackingTeam.getDcr();
                            player2 = attackingTeam.getDcl();
                        } else{
                            player1 = attackingTeam.getDcr();
                            player2 = attackingTeam.getDr();
                            player3 = attackingTeam.getDl();
                            player4 = attackingTeam.getDcl();
                            passIsWithinSameTier = false;
                            passToDefender = true;
                        }
                    }
                }
                break;
            case "midfielder":
                if(playerInPoss.getStartingPosition() == "DM"){
                    player1 = attackingTeam.getMcl();
                    player2 = attackingTeam.getMcr();
                } else if(playerInPoss.getStartingPosition() == "MCR"){
                    player1 = attackingTeam.getMcl();
                    player2 = attackingTeam.getDm();
                } else if(playerInPoss.getStartingPosition() == "MCL"){
                    player1 = attackingTeam.getMcr();
                    player2 = attackingTeam.getDm();
                } else {
                    player1 = attackingTeam.getDm();
                    player2 = attackingTeam.getMcl();
                    player3 = attackingTeam.getMcr();
                    passIsWithinSameTier = false;
                }
                break;
            case "attacker":
                if(playerInPoss.getStartingPosition() == "ML"){
                    player1 = attackingTeam.getSt();
                    player2 = attackingTeam.getMr();
                } else if(playerInPoss.getStartingPosition() == "MR"){
                    player1 = attackingTeam.getSt();
                    player2 = attackingTeam.getMr();
                } else if(playerInPoss.getStartingPosition() == "St"){
                    player1 = attackingTeam.getMr();
                    player2 = attackingTeam.getMl();
                } else {
                    player1 = attackingTeam.getMr();
                    player2 = attackingTeam.getMl();
                    player3 = attackingTeam.getSt();
                    passIsWithinSameTier = false;
                }
        }
        Player player = calcMostProbablePassReciever(player1, player2, player3, player4, passIsWithinSameTier, passToDefender);
        return player;
    }

    public Player calcMostProbablePassReciever(Player player1, Player player2, Player player3, Player player4, Boolean passIsWithinSameTier, Boolean passToDefender){
        if(passIsWithinSameTier){
            int diffBetweenForwardsOveralls = Math.abs(player1.getOverall()- player2.getOverall());
            double num = Math.random();
            int randInt = (int)(num*100+1);

            if((50 + diffBetweenForwardsOveralls) > randInt){
                if(player1.getOverall() > player2.getOverall()){
                    return player1;
                }
                else{
                    return player2;
                }
            }
            else{
                if(player1.getOverall() < player2.getOverall()){
                    return player1;
                }
                else{
                    return player2;
                }
            }
        } else if(!passToDefender) {
            List<Player> myList = new ArrayList<>(); //change for 4 players
            myList.add(player1);
            myList.add(player2);
            myList.add(player3); //this player has the greatest overall right

            Collections.sort(myList);
            int diffHighestOthers = player3.getOverall() -((player2.getOverall() + player1.getOverall())/2);
            int diffMiddleLowest = player2.getOverall() - player1.getOverall();

            int randomChance = random.nextInt(100) + 1;

            if(randomChance - diffHighestOthers < 33){
                return player3;
            } else if(randomChance - diffMiddleLowest < 66){
                return player2;
            } else{
                return player1;
            }
        } else{
            List<Player> myList = new ArrayList<>(); //change for 4 players
            myList.add(player1);
            myList.add(player2);
            myList.add(player3); //this player has the greatest overall right
            myList.add(player4);

            Collections.sort(myList);
            int diffHighestOthers = player4.getOverall() -((player3.getOverall() + player2.getOverall() + player1.getOverall())/3);
            int diffMiddleLowest = player3.getOverall() - player2.getOverall();

            int randomChance = random.nextInt(100) + 1;

            if (randomChance - diffHighestOthers < 25) {
                return player4;
            } else if (randomChance - diffMiddleLowest < 50) {
                return player3;
            } else if (randomChance - diffMiddleLowest < 75) {
                return player2;
            } else {
                return player1;
            }

        }
    }
}
