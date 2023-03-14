package matchEngine;

import players.Defender;
import players.Forward;
import players.Midfielder;
import players.Player;
import team.Team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class MatchEngine {
    public Team homeTeam;
    public Team awayTeam;

    public boolean homeTeamHasPossession = true;

    public int homeScore;
    public int awayScore;

    public MatchEngine(Team homeTeam,Team awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;

    }

    public void runMatchEngine() {

        playBall(homeTeam,awayTeam);
        playBall(awayTeam,homeTeam);

//        try {
//            appendScoretoFile(homeScore,awayScore);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        System.out.println("Final Score: " + homeScore + "-" + awayScore);
    }

    public void appendScoretoFile(int homeScore,int awayScore) throws IOException {

            FileWriter fw = new FileWriter("Final Score", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Final Score: " + homeScore + "-" + awayScore);
            bw.newLine();
            bw.close();
    }

    public void playBall(Team attackingTeam,Team defendingTeam){
        securePossession(attackingTeam,defendingTeam,attackingTeam.m1,defendingTeam.m2);
    }
    public void updateScore(Team attackingTeam){
        if(attackingTeam.teamName.equals(homeTeam.teamName)){
            homeScore +=1;
        }
        else{
            awayScore+=1;
        }
    }

    public void securePossession(Team attackingTeam, Team defendingTeam, Midfielder playerInPosession, Midfielder marker){
        //ball passed between midfielders with attacking team starting with ball,
        //if a certain check is passed then a throughball should be played,
        //otherwise the function is called with the other team in possesion
        //or the ball is played back to the defence and isPlayOutFromBackSuccesful called
        double luckyBounce = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;

        if(playerInPosession.getDribbling() < marker.getTackling() && marker.getTackling() < luckyBounce){ //and player is in same area
            securePossession(defendingTeam,attackingTeam,marker,playerInPosession);
        }
        else if ((firstTouchFailure > 10)){  //get vision is higher than pass.difficulty?
        throughBallOutcome(attackingTeam,defendingTeam);}     //how do we decide what attacker should get the ball, assign every player an overall and weight it off that?
        //if N can they pass to a teamate
            //secure Possesion with different player
        else if ((firstTouchFailure > 2)){
            securePossession(attackingTeam,defendingTeam,attackingTeam.m2,defendingTeam.m2);
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
    public void throughBallOutcome(Team attackingTeam, Team defendingTeam){
        double passFailure = Math.random()*10*2;
        double shotFailure = Math.random()*10*2;
        Player playerInPossession = whichAttackerReceivesTheBall(attackingTeam;

        if(attackingTeam.m1.getPassing() > passFailure){
            if(((Forward) playerInPossession).getFinishing() > (shotFailure + defendingTeam.g.getSaving()/2)) {
                System.out.println("Goal Scored! Scorer:" + playerInPossession.getFirstName() + " " + playerInPossession.getLastName());
                updateScore(attackingTeam);
            }
            else{
                securePossession(defendingTeam,attackingTeam,defendingTeam.m1,attackingTeam.m1); //should this be attacking team or defending team retaining possesion
            }

        }
        else{
            if(playerInPossession == attackingTeam.f1){
                playerInPossession = defendingTeam.d1;
            }
            else {
                playerInPossession = defendingTeam.d2;
            }
            isPlayOutFromBackSuccesful(defendingTeam,attackingTeam,playerInPossession); //same as above
        }

    }

    public void isPlayOutFromBackSuccesful(Team attackingTeam, Team defendingTeam, Player playerInPossession){ //need to add midfielder tackling
        double passFailure = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;

        if(((Defender) playerInPossession).getPassing() > passFailure && attackingTeam.m1.getFirstTouch() > firstTouchFailure){
            securePossession(attackingTeam,defendingTeam,attackingTeam.m1,defendingTeam.m1);
        }
        securePossession(defendingTeam,attackingTeam,defendingTeam.m1,attackingTeam.m1); //which midfielder should be returned here

    }

}