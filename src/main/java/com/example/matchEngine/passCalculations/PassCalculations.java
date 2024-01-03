package com.example.matchEngine.passCalculations;

import com.example.model.player.Player;
import com.example.team.Team;

import java.util.*;

public class PassCalculations {
    private Random random = new Random();
    public Player whichPlayerReceivesTheBall(Team attackingTeam, String position) { //this should be changed to reflect what runs an attacker is making
        Player player1 = null;
        Player player2 = null;
        Player player3 = null;
        switch (position) {
            case "defender":
                player1 = attackingTeam.getDcr();
                player2 = attackingTeam.getDcl();
                break;
            case "midfielder":
                player1 = attackingTeam.getDm();
                player2 = attackingTeam.getMcl();
                player3 = attackingTeam.getMcr();
                break;
            case "attacker":
                player1 = attackingTeam.getMl();
                player2 = attackingTeam.getMr();
                player3 = attackingTeam.getSt();
                break;
        }
        Player player = calcMostProbablePassReciever(player1, player2, player3, position);
        return player;
    }

    public Player calcMostProbablePassReciever(Player player1, Player player2, Player player3, String position){
        if(position.equals("defender") || position.equals("fullBack")){
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
        } else {
            List<Player> myList = new ArrayList<>();
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
        }
    }
}
