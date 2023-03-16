package matchEngine;

import players.*;
import team.Team;
import team.TeamSetup;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class MatchEngine {
    public  Team homeTeam;
    public  Team awayTeam;

    public boolean homeTeamHasPossession = true;

    public int homeScore;
    public int awayScore;

    public MatchEngine(Team homeTeam,Team awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;

    }

    public void getMarkers(Team attackingTeam, Team defendingTeam){
        Midfielder[] midfielders = new Midfielder[4];
        midfielders[0] = attackingTeam.m1;
        midfielders[1] = attackingTeam.m2;
        midfielders[2] = defendingTeam.m1;
        midfielders[3] = defendingTeam.m2;

        TeamSetup teamSetup = new TeamSetup();
        teamSetup.addMarkers(midfielders);

    }

    public void runMatchEngine() {

        getMarkers(homeTeam,awayTeam);

        playBall(homeTeam,awayTeam);
        playBall(awayTeam,homeTeam);

        try {
            appendScoretoFile(homeScore,awayScore);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        System.out.println("Final Score: " + homeScore + "-" + awayScore);
    }

    public void appendScoretoFile(int homeScore,int awayScore) throws IOException {

            FileWriter fw = new FileWriter("Final Score", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Final Score: " + homeScore + "," + awayScore);
            bw.newLine();
            bw.close();
    }

    public void playBall(Team attackingTeam,Team defendingTeam){
        securePossession(attackingTeam,defendingTeam,attackingTeam.m1);
    }
    public void updateScore(Team attackingTeam){
        if(attackingTeam.teamName.equals(homeTeam.teamName)){
            homeScore +=1;
        }
        else{
            awayScore+=1;
        }
    }

    public void securePossession(Team attackingTeam, Team defendingTeam, Midfielder playerInPosession){
        //ball passed between midfielders with attacking team starting with ball,
        //if a certain check is passed then a throughball should be played,
        //otherwise the function is called with the other team in possesion
        //or the ball is played back to the defence and isPlayOutFromBackSuccesful called
        double luckyBounce = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;

        Midfielder marker = playerInPosession.getMarker();

        if(playerInPosession.getDribbling() < marker.getTackling() && marker.getTackling() < luckyBounce){
            securePossession(defendingTeam,attackingTeam,marker);
        }
        else if ((firstTouchFailure > 10)){  //get vision is higher than pass.difficulty + some movement check on the attacker = off the ball which can be influenced by high pace
        throughBallOutcome(attackingTeam,defendingTeam,playerInPosession);}
        //if N can they pass to a teamate
            //secure Possesion with different player
        else{
            securePossession(attackingTeam,defendingTeam,attackingTeam.m2);
        }
        //if N playoutfromtheback

    }

    public Forward whichAttackerReceivesTheBall(Team attackingTeam){
        int diffBetweenForwardsOveralls = Math.abs(attackingTeam.f1.getOvr() - attackingTeam.f2.getOvr());
        double num = Math.random();
        int randInt = (int)(num*100+1);
        if((50 + diffBetweenForwardsOveralls) > randInt){
            if(attackingTeam.f1.getOvr() > attackingTeam.f2.getOvr()){
                return attackingTeam.f1;
            }
            else{
                return attackingTeam.f2;
            }
        }
        else{
            if(attackingTeam.f1.getOvr() < attackingTeam.f2.getOvr()){
                return attackingTeam.f1;
            }
            else{
                return attackingTeam.f2;
            }
        }
    }
    public void throughBallOutcome(Team attackingTeam, Team defendingTeam, Player playerInPossession){
        double passFailure = Math.random()*10*2;
        double counterAttackChance = Math.random()*10*2;
        double shotFailure = Math.random()*10*2;
        double composureCheck = Math.random()*10*2;
        Forward forwardInPossession = whichAttackerReceivesTheBall(attackingTeam);
        double dmPositioningDebuff = positioningDebuffCalc(defendingTeam.dm);
        double gkSavingDebuff = positioningDebuffCalc(defendingTeam.g);

        if(playerInPossession.getPassing() > (passFailure + defendingTeam.dm.getPositioning()/dmPositioningDebuff)){ //workrate of the defensive player should influence this

            if(( forwardInPossession.getFinishing() > (shotFailure + defendingTeam.g.getSaving()/gkSavingDebuff)) && (forwardInPossession.getComposure() > composureCheck)) { // should be a defender marking check also a composure check on both keeper and striker
                System.out.println("Goal Scored! Scorer:" + forwardInPossession.getFirstName() + " " + forwardInPossession.getLastName() + " Assist: " + playerInPossession.getFirstName() + " " + playerInPossession.getLastName());
                updateScore(attackingTeam);
            }
            else if (playerInPossession instanceof Midfielder) { //forward doesnt score but ball breaks to attacking team, should be a random chance it goes to defending team added
                // calculate which forward picks up the loose ball based on positioning
                Forward forwardNotInPossession = whoPicksUpLooseBall(attackingTeam);
                double num = Math.random();
                int randInt = (int) (num * 100 + 1);//random chance that the attacker gets an assist.
                if (randInt > 50) {
                    throughBallOutcome(attackingTeam, defendingTeam, forwardInPossession);//decides whether ball breaks to attacking team forwards or midfield, should add opponents defence/midfield too with an emphasis on defensive midfielders
                } else {
                    securePossession(attackingTeam, defendingTeam, (Midfielder) playerInPossession); //should this be attacking team or defending team retaining possesion, probably defending team?
                }
            }
            else{
                if(counterAttackChance > 7){
                    isPlayOutFromBackSuccesful(defendingTeam,attackingTeam,defendingTeam.d1);
                }

            }

        }
        else{
            if(counterAttackChance> 7) {
                isPlayOutFromBackSuccesful(defendingTeam, attackingTeam, defendingTeam.d1);
            }// should be the marker with maybe a marking and tackling check
        }
    }

    public double positioningDebuffCalc(Player player){ //should take into account posititioning and mental stats - higher should return closer to 0, lower to three
//        double num = Math.random();
//        int randInt = (int)(num*2+1);
//        if(randInt <1){
//
//        } else if () {
//
//        }
//        else {
//
//        }
//        return 3;

        int goalsConceded;
        if(player.getClub().equals(homeTeam.nameAbrev)){
            goalsConceded = awayScore;
        } else{
            goalsConceded = homeScore;
        }
        double positionDebuff = 3; //lower is better - 1 is best, higher is worse as its being used to divide the keepers saving
        if(goalsConceded == 4){
            return 2;
        } else if (goalsConceded ==  5) {
            return 1;
        }
        else if (goalsConceded > 5){
            return 0.5;
        }else{
            return 3;
        }


    }

    public Forward whoPicksUpLooseBall(Team attackingTeam){ //could probably do a loop with an array of the attacking players on both teams
        double num = Math.random();
        int randInt = (int)(num*100+1);
        double positoningCheck = Math.random()*10*2;

        if((randInt < 33) && ((attackingTeam.f1.getPositioning()/2) > positoningCheck)){
            return attackingTeam.f1;
        } else if ((randInt>33 && randInt < 66)  && ((attackingTeam.f2.getPositioning()/2) > positoningCheck)) {
            return attackingTeam.f2;
        }else{
            return attackingTeam.f1; //should be third forward
        }
    }

    public void isPlayOutFromBackSuccesful(Team attackingTeam, Team defendingTeam, Player playerInPossession){ //need to add midfielder tackling
        double passFailure = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;

        if(((Defender) playerInPossession).getPassing() > passFailure && attackingTeam.m1.getFirstTouch() > firstTouchFailure){
            securePossession(attackingTeam,defendingTeam,attackingTeam.m1);
        }
        securePossession(defendingTeam,attackingTeam,defendingTeam.m1); //which midfielder should be returned here

    }

}