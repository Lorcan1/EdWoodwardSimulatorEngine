package matchEngine;

import players.Defender;
import players.Forward;
import players.Goalkeeper;
import players.Midfielder;
import team.Team;

public class MatchEngine {
    public Team homeTeam;
    public Team awayTeam;

    public boolean homeTeamHasPossession = true;

    public int homeScore;
    public int awayScore;

    public MatchEngine(Team homeTeam,Team awayTeam) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore= homeScore;
    this.awayScore = awayScore;

    }

    public void runMatchEngine() {

        playBall(homeTeam,awayTeam);
        playBall(awayTeam,homeTeam);

        System.out.println("Final Score: " + homeScore + "-" + awayScore);
    }

    public void playBall(Team attackingTeam,Team defendingTeam){
            if(isPlayOutFromBackSuccesful(attackingTeam,defendingTeam)){ //ball starts with home team defenders currently
                String Outcome = throughBallOutcome(attackingTeam,defendingTeam);
                if(Outcome.equals("Goal")) {
                    updateScore(attackingTeam);
                    // kick off

                }
                else if(Outcome.equals("Goal Kick")){
                    //Goal Kick
                }
                else if(Outcome.equals("Def Poss")){ //what if they retain possession
                    playBall(defendingTeam,attackingTeam);

                }

            }

        }
    public void updateScore(Team attackingTeam){
        if(attackingTeam.teamName == homeTeam.teamName){
            homeScore +=1;
        }
        else{
            awayScore+=1;
        }
    }
    public String throughBallOutcome(Team attackingTeam, Team defendingTeam){ //attacking players and defending players
        double passFailure = Math.random()*10*2;
        double shotFailure = Math.random()*10*2;
        System.out.println("Pass Failure: " + passFailure + "\n" + "Shot Failure: " + shotFailure);
        if(attackingTeam.m1.getPassing() > passFailure){
            if(attackingTeam.f1.getFinishing() > defendingTeam.g.getSaving() && attackingTeam.f1.getFinishing() > shotFailure) {
                return "Goal";
            }
            else{
                return "Goal Kick";
            }
        }
        return "Def Poss";
    }

    public boolean isPlayOutFromBackSuccesful(Team attackingTeam, Team defendingTeam){ //need to add midfielder tackling
        double passFailure = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;
        System.out.println("Pass Failure: " + passFailure + "\n" + "First Touch Failure: " + firstTouchFailure);
        if(attackingTeam.d1.getPassing() > passFailure && attackingTeam.m1.getFirstTouch() > firstTouchFailure){
            return true;
        }
        return false ;

    }

}