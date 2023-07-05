package com.example.matchEngine;

import com.example.model.Match;
import com.example.model.player.Goalkeeper;
import com.example.model.player.OutfieldPlayer;
import com.example.model.player.PlayerMatchStats;
import com.example.team.PlayersMatchStats;
import com.example.team.Team;
import com.example.team.TeamSetup;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.util.*;

//
//import players.*;
//import team.Team;
//import team.TeamSetup;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Random;
//
//
@Getter
@Setter
@Slf4j
public class MatchEngine implements Subject{




    //need to change the design so that an endpoint can be hit and every time its hit the gamestate moves on a chunk of time until the game is over
    private final TeamSetup teamSetup;
    private Team homeTeam;
    private Team awayTeam;

    public String homeTeamName;
    public String awayTeamName;

    private Map<String, Player> positionsMapAway;
    private Map<String, Player> positionsMapHome;

    public boolean homeTeamHasPossession = true;

    public int homeScore;
    public int awayScore;

    private List<String> homeScorers = new ArrayList<>();
    private List<String> awayScorers= new ArrayList<>();
    private List<String> homeAssisters= new ArrayList<>();
    private List<String> awayAssisters= new ArrayList<>();

    private List<Observer> observers;

    HashMap<Player, Player> markers;

    private Match match;

    private PlayersMatchStats homePlayersMatchStats;


    public MatchEngine(TeamSetup teamSetup, String homeTeamName, String awayTeamName) {
        this.teamSetup = teamSetup;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeam = new Team(homeTeamName,teamSetup);
        this.awayTeam = new Team(awayTeamName,teamSetup);
        this.markers = new HashMap<>();
        markers.put(homeTeam.getMcl() ,awayTeam.getMcr());
        markers.put(homeTeam.getMcr() ,awayTeam.getMcl());
        markers.put(awayTeam.getMcr() ,homeTeam.getMcl());
        markers.put(awayTeam.getMcr() ,homeTeam.getMcl());
        this.observers = new ArrayList<>();
        this.match = new Match();
        this.homePlayersMatchStats= assignPlayersToMatch(this.homeTeam);


    }

    public PlayersMatchStats assignPlayersToMatch(Team team){
        ArrayList<Player> players = (ArrayList<Player>) team.getPlayers();
        PlayersMatchStats playersMatchStats = new PlayersMatchStats();
        ArrayList<PlayerMatchStats> playerMatchStatsArray = new ArrayList();
        playersMatchStats.setPlayerMatchStatsArray(playerMatchStatsArray);
        for(Player player: players){
            PlayerMatchStats playerMatchStats = new PlayerMatchStats();
            playerMatchStats.setName(player.getLastName());
            playerMatchStats.setPos(player.getStartingPosition());
            playerMatchStats.setPlayerID(player.getId());
            playerMatchStats.setClub(player.getClubAbbrev());
            playersMatchStats.getPlayerMatchStatsArray().add(playerMatchStats);
        }

        return playersMatchStats;
    }

    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        int observerIndex = observers.indexOf(o);
        if(observerIndex >= 0){
            observers.remove(observerIndex);
        }
    }

    @Override
    public void notifyObservers(){
        observers.forEach(o->o.update(match));
    }



//


    public JSONObject runMatchEngine() {

        playBall(homeTeam,awayTeam);
        playBall(awayTeam,homeTeam);
//
//        try {
//            appendScoretoFile(homeScore,awayScore);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
        addMatchParameters();
        log.info("Final Score: " + homeScore + "-" + awayScore + homeScorers.toString() + awayScorers.toString());
        JSONArray scorers = new JSONArray();
        JSONObject scorer1 = new JSONObject();
        scorer1.put("name","Son");
        scorer1.put("team","home");
        JSONArray goals1 = new JSONArray();
        goals1.add("15'");
        goals1.add("30'");
        scorer1.put("goals",goals1);

        JSONObject scorer2 = new JSONObject();
        scorer2.put("name","Kane");
        scorer2.put("team","home");
        JSONArray goals2 = new JSONArray();
        goals2.add("40'");
        scorer2.put("goals",goals2);

        JSONObject scorer3 = new JSONObject();
        scorer3.put("name","Haaland");
        scorer3.put("team","away");
        JSONArray goals3 = new JSONArray();
        goals3.add("40'");
        scorer3.put("goals",goals3);
//        obj.put("homeScorers", homeScorers.toString().replace("[", "").replace("]", ""));
//        obj.put("awayScorers",awayScorers.toString().replace("[", "").replace("]", ""));
//        obj.put("homeScorers",homeScorers);
//        obj.put("awayScorers",awayScorers);
        scorers.add(scorer1);
        scorers.add(scorer2);
        scorers.add(scorer3);

        JSONObject obj = new JSONObject();
        obj.put("homeTeam","Tottenham Hotspur");
        obj.put("awayTeam","Manchester City");
        obj.put("awayTeamScore","1");
        obj.put("homeTeamScore","3");
        obj.put("Scorer",scorers);
        notifyObservers();
        return obj ;
    }
//
//    public void appendScoretoFile(int homeScore,int awayScore) throws IOException {
//
//            FileWriter fw = new FileWriter("Final Score", true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write("Final Score: " + homeScore + "," + awayScore);
//            bw.newLine();
//            bw.close();
//    }
//
    public void playBall(Team attackingTeam, Team defendingTeam){
        securePossession(attackingTeam,defendingTeam);
    }
    public void updateScore(Team attackingTeam, Player scorer, Player assister){
        if(attackingTeam.getTeamName().equals(homeTeam.getTeamName())){
            homeScore +=1;
            homeScorers.add(scorer.getLastName());
            homeAssisters.add(assister.getLastName());
        }
        else{
            awayScore+=1;
            awayScorers.add(scorer.getLastName());
            awayAssisters.add(assister.getLastName());

        }
    }

    public void securePossession(Team attackingTeam, Team defendingTeam){
        //ball passed between midfielders with attacking team starting with ball,
        //if a certain check is passed then a throughball should be played,
        //otherwise the function is called with the other team in possesion
        //or the ball is played back to the defence and isPlayOutFromBackSuccesful called
        double luckyBounce = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;


        OutfieldPlayer playerInPosession = (OutfieldPlayer) attackingTeam.getMcr();
        OutfieldPlayer marker = (OutfieldPlayer) markers.get(playerInPosession);

        if(playerInPosession.getDribbling() < marker.getTackling() && marker.getTackling() < luckyBounce){
            securePossession(defendingTeam,attackingTeam);
        }
        else if ((firstTouchFailure > 10)){  //get vision is higher than pass.difficulty + some movement check on the attacker = off the ball which can be influenced by high pace
        throughBallOutcome(attackingTeam,defendingTeam,playerInPosession);}
//        //if N can they pass to a teamate
//            //secure Possesion with different player
//        else{
//            securePossession(attackingTeam,defendingTeam,attackingTeam.m2);
//        }
        //if N playoutfromtheback

    }
//
    public OutfieldPlayer whichAttackerReceivesTheBall(Team attackingTeam){ //this should be changed to reflect what runs an attacker is making
        int diffBetweenForwardsOveralls = Math.abs(attackingTeam.getSt().getOverall()- attackingTeam.getMl().getOverall());
        double num = Math.random();
        int randInt = (int)(num*100+1);
        if((50 + diffBetweenForwardsOveralls) > randInt){
            if(attackingTeam.getSt().getOverall() > attackingTeam.getMl().getOverall()){
                return (OutfieldPlayer) attackingTeam.getSt();
            }
            else{
                return (OutfieldPlayer) attackingTeam.getMl();
            }
        }
        else{
            if(attackingTeam.getSt().getOverall() < attackingTeam.getMl().getOverall()){
                return (OutfieldPlayer) attackingTeam.getSt();
            }
            else{
                return (OutfieldPlayer) attackingTeam.getMl();
            }
        }
    }
    public void throughBallOutcome(Team attackingTeam, Team defendingTeam, Player playerInPossession){
        double passFailure = Math.random()*10*2;
        double counterAttackChance = Math.random()*10*2;
        double shotFailure = Math.random()*10*2;
        double composureCheck = Math.random()*10*2;
        OutfieldPlayer forwardInPossession = whichAttackerReceivesTheBall(attackingTeam);
        double dmPositioningDebuff = positioningDebuffCalc(defendingTeam.getDm());
        double gkSavingDebuff = positioningDebuffCalc(defendingTeam.getGk());
//
        if(playerInPossession.getPassing() > (passFailure + defendingTeam.getDm().getPositioning()/dmPositioningDebuff)){ //workrate of the defensive player should influence this
            Goalkeeper goalkeeper = (Goalkeeper) defendingTeam.getGk();
            if((forwardInPossession.getFinishing() > (shotFailure + goalkeeper.getReflexes()/gkSavingDebuff)) && (forwardInPossession.getComposure() > composureCheck)) { // should be a defender marking check also a composure check on both keeper and striker
                log.info("Goal Scored! Scorer:" + forwardInPossession.getFirstName() + " " + forwardInPossession.getLastName() + " Assist: " + playerInPossession.getFirstName() + " " + playerInPossession.getLastName());
                updateScore(attackingTeam, forwardInPossession, playerInPossession);
            }
//            else if (playerInPossession instanceof Midfielder) { //forward doesnt score but ball breaks to attacking team, should be a random chance it goes to defending team added
//                // calculate which forward picks up the loose ball based on positioning
//                Forward forwardNotInPossession = whoPicksUpLooseBall(attackingTeam);
//                double num = Math.random();
//                int randInt = (int) (num * 100 + 1);//random chance that the attacker gets an assist.
//                if (randInt > 50) {
//                    throughBallOutcome(attackingTeam, defendingTeam, forwardInPossession);//decides whether ball breaks to attacking team forwards or midfield, should add opponents defence/midfield too with an emphasis on defensive midfielders
//                } else {
//                    securePossession(attackingTeam, defendingTeam, (Midfielder) playerInPossession); //should this be attacking team or defending team retaining possesion, probably defending team?
//                }
//            }
            else{
                if(counterAttackChance > 7){
                    isPlayOutFromBackSuccesful(defendingTeam,attackingTeam,defendingTeam.getDcl());
                }
//
            }
//
        }
        else{
            if(counterAttackChance> 7) {
                isPlayOutFromBackSuccesful(defendingTeam, attackingTeam, defendingTeam.getDcl());
            }// should be the marker with maybe a marking and tackling check
        }
    }
//
//
////    public void crossOutcome(Team attackingTeam, Team defendingTeam, OutfieldPlayer playerInPossession){
////        /*
////        first stage is to determine if the player (fullback, winger, midfielder evades the tackle of the opposition fullback
////        chancetoblock random + fullback positioning, if tackling is high then chance to win the ball back? counter-attack chance
////
////            For the cross itself
////            Crossing and maybe a mental stat (vision?)
////
////                    Defender Clearance = Defender marking + positioning (headiing)
////
////                            Striker scoring = similar to function above
////
////                            Need to add -
////                            fullbacks - check
////                            markers to wingers and fullbacks
////                            everyonre  attribute - positioning
////                            outfield attribute- crossing
////                            whichDefenderClearstheBall function
////                            everyone attribute - heading
////                            which attacker goes to the wide channels to allow forwards to cross
////
////         */
////        Boolean goalScored = false;
////        Player marker;
////        double fullBackPositioningDebuff = positioningDebuffCalc(marker);
////        double passFailure = Math.random()*10*2;
////        double shotBlocked = Math.random()*10*2;
////        if(shotBlocked > (5 + (marker.getPositing/fullBackPositioningDebuff))){ //higher the positioning, higher the right side of the expression, statement will fail more
////            if(playerInPossession.getCrossing() > passFailure){
////                Defender defender = whichDefenderClearsTheBall(defendingTeam);
////                double clearanceFailure = Math.random()*10*2;
////                double defenderPositioningDebuff = positioningDebuffCalc(defender);
////                if(clearanceFailure > defender.getHeading/positioningDebuffCalc()){
////                    Forward forwardInPossession = whichAttackerReceivesTheBall(attackingTeam);
////                    double shotFailure = Math.random()*10*2;
////                    double gkSavingDebuff = positioningDebuffCalc(defendingTeam.g);
////                    double composureCheck = Math.random()*10*2;
////                    if((forwardInPossession.getFinishing() > (shotFailure + defendingTeam.g.getSaving()/gkSavingDebuff)) && (forwardInPossession.getComposure() > composureCheck)){
////                        System.out.println("Goal Scored! Scorer:" + forwardInPossession.getFirstName() + " " + forwardInPossession.getLastName() + " Assist: " + playerInPossession.getFirstName() + " " + playerInPossession.getLastName());
////                        updateScore(attackingTeam);
////                        goalScored = true;
////                    }
////
////                }
////            }
////
////        }
////        if(!goalScored){
////            /*
////            calculate who gets posession/if there is a chance for a counter attack
////             */
////        }
////
//    }
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
        if(player.getClub().equals(homeTeam.getTeamName())){
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
//
//    public Forward whoPicksUpLooseBall(Team attackingTeam){ //could probably do a loop with an array of the attacking players on both teams
//        double num = Math.random();
//        int randInt = (int)(num*100+1);
//        double positoningCheck = Math.random()*10*2;
//
//        if((randInt < 33) && ((attackingTeam.f1.getPositioning()/2) > positoningCheck)){
//            return attackingTeam.f1;
//        } else if ((randInt>33 && randInt < 66)  && ((attackingTeam.f2.getPositioning()/2) > positoningCheck)) {
//            return attackingTeam.f2;
//        }else{
//            return attackingTeam.f1; //should be third forwardd
//        }
//    }
//
    public void isPlayOutFromBackSuccesful(Team attackingTeam, Team defendingTeam, Player playerInPossession){ //need to add midfielder tackling
        double passFailure = Math.random()*10*2;
        double firstTouchFailure = Math.random()*10*2;

        if(playerInPossession.getPassing() > passFailure && attackingTeam.getMcr().getFirstTouch() > firstTouchFailure){ //should be a random midfielder/ a similar algo to the forward/ based on off the ball and stuff
            securePossession(attackingTeam,defendingTeam);
        }
        securePossession(defendingTeam,attackingTeam); //which midfielder should be returned here

    }

    public void addMatchParameters(){
        match.setHomeTeam(homeTeamName);
        match.setAwayTeam(awayTeamName);
        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
        match.setHomeScorers(homeScorers);
        match.setAwayScorers(awayScorers);
        match.setHomeAssisters(homeAssisters);
        match.setAwayAssisters(awayAssisters);

    }
//
}