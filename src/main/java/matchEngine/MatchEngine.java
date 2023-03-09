package matchEngine;

import players.Defender;
import players.Forward;
import players.Goalkeeper;
import players.Midfielder;

import java.util.logging.Logger;

public class MatchEngine {
    public Goalkeeper hG1, aG1;
    public Defender hD1, hD2, aD1, aD2;
    public Midfielder hM1, hM2, aM1, aM2;
    public Forward hF1, hF2, aF1, aF2;

    public int homeScore;
    public int awayScore;

    public MatchEngine(Goalkeeper hG1, Goalkeeper aG1, Defender hD1, Defender hD2, Defender aD1, Defender aD2, Midfielder hM1, Midfielder hM2, Midfielder aM1, Midfielder aM2, Forward hF1, Forward hF2, Forward aF1, Forward aF2) {
        this.hG1 = hG1;
        this.aG1 = aG1;
        this.hD1 = hD1;
        this.hD2 = hD2;
        this.aD1 = aD1;
        this.aD2 = aD2;
        this.hM1 = hM1;
        this.hM2 = hM2;
        this.aM1 = aM1;
        this.aM2 = aM2;
        this.hF1 = hF1;
        this.hF2 = hF2;
        this.aF1 = aF1;
        this.aF2 = aF2;
    }

    public void runMatchEngine(){
        double passFailure = 0;

        homeScore = possessionOutcome(hM1,hF1,aG1,homeScore);
        awayScore = possessionOutcome(aM1,aF1,hG1,awayScore);

        System.out.println("Final Score: " + homeScore + "-" + awayScore);

    }

    public int possessionOutcome(Midfielder aM, Forward aF, Goalkeeper dG, int score){ //attacking players and defending players
        double passFailure = Math.random()*10*2;
        double shotFailure = Math.random()*10*2;
        System.out.println("Pass Failure: " + passFailure + "\n" + "Shot Failure: " + shotFailure);
        if(aM.getPassing() > passFailure){
            if(aF.getFinishing() > dG.getSaving() && aF.getFinishing() > shotFailure){
                score+=1;

            }
        }
        return score;
    }

}