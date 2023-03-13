package matchEngine;

import team.Team;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
        securePossession(attackingTeam,defendingTeam);
    }

//            if(isPlayOutFromBackSuccesful(attackingTeam,defendingTeam)){ //ball starts with home team defenders currently
//                String Outcome = throughBallOutcome(attackingTeam,defendingTeam);
//                if(Outcome.equals("Goal")) {
//                    updateScore(attackingTeam);
//                    // kick off
//
//                }
//                else if(Outcome.equals("Goal Kick")){
//                    securePossession(attackingTeam,defendingTeam);
//                }
//                else if(Outcome.equals("Def Poss")){ //what if they retain possession
//                    playBall(defendingTeam,attackingTeam);
//
//                }
//
//            }
//
//        }
    public void updateScore(Team attackingTeam){
        if(attackingTeam.teamName.equals(homeTeam.teamName)){
            homeScore +=1;
        }
        else{
            awayScore+=1;
        }
    }

    public void securePossession(Team attackingTeam, Team defendingTeam){
        //ball passed between midfielders with attacking team starting with ball,
        //if a certain check is passed then a throughball should be played,
        //otherwise the function is called with the other team in possesion
        //or the ball is played back to the defence and isPlayOutFromBackSuccesful called
        double luckyBounce = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;
        if(attackingTeam.m1.getDribbling() < defendingTeam.m1.getTackling() && defendingTeam.m1.getTackling() < luckyBounce){ //and player is in same area
            securePossession(defendingTeam,attackingTeam);
        }
        else if ((firstTouchFailure > 10)){  //get vision is higher than pass.difficulty?
        throughBallOutcome(attackingTeam,defendingTeam);}
        //if N can they pass to a teamate
            //secure Possesion with different player
        //if N playoutfromtheback

    }
    public void throughBallOutcome(Team attackingTeam, Team defendingTeam){
        double passFailure = Math.random()*10*2;
        double shotFailure = Math.random()*10*2;
        System.out.println("Pass Failure: " + passFailure + "\n" + "Shot Failure: " + shotFailure);
        if(attackingTeam.m1.getPassing() > passFailure){
            if(attackingTeam.f1.getFinishing() > defendingTeam.g.getSaving() && attackingTeam.f1.getFinishing() > shotFailure) {
                updateScore(attackingTeam);
            }
            else{
                securePossession(attackingTeam,defendingTeam);
            }

        }
        else{
            isPlayOutFromBackSuccesful(attackingTeam,defendingTeam);
        }

    }

    public void isPlayOutFromBackSuccesful(Team attackingTeam, Team defendingTeam){ //need to add midfielder tackling
        double passFailure = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;
        System.out.println("Pass Failure: " + passFailure + "\n" + "First Touch Failure: " + firstTouchFailure);
        if(attackingTeam.d1.getPassing() > passFailure && attackingTeam.m1.getFirstTouch() > firstTouchFailure){
            securePossession(attackingTeam,defendingTeam);
        }
        securePossession(defendingTeam,attackingTeam);

    }

}