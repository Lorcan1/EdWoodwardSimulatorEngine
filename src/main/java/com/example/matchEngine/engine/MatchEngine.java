package com.example.matchEngine.engine;

import com.example.matchEngine.matchSetup.MatchSetup;
import com.example.matchEngine.playerDecisions.AttackerDecisions;
import com.example.matchEngine.playerDecisions.DefenderDecisions;
import com.example.matchEngine.playerDecisions.MidfielderDecisions;
import com.example.matchEngine.playerDecisions.PlayerDecisions;
import com.example.matchEngine.shotCalculations.ShotCalculations;
import com.example.matchEngine.updateStats.UpdateInGameMatchStats;
import com.example.matchEngine.updateStats.UpdateInGamePlayerStats;
import com.example.model.InGameMatchStats;
import com.example.model.Match;
import com.example.model.player.OutfieldPlayer;
import com.example.model.player.InGamePlayerStats;
import com.example.team.PlayersMatchStats;
import com.example.team.Team;
import com.example.team.TeamSetup;
import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
public class MatchEngine {

    private Match match = new Match();//need to set an Id and give it to the player stats
    private UpdateInGameMatchStats updateInGameMatchStats  = new UpdateInGameMatchStats(match);
    private UpdateInGamePlayerStats updateInGamePlayerStats = new UpdateInGamePlayerStats(this);

    //need to change the design so that an endpoint can be hit and every time its hit the gamestate moves on a chunk of time until the game is over
    private final TeamSetup teamSetup;
    private Team homeTeam;

    private Team awayTeam;

    public String homeTeamName;
    public String awayTeamName;

    private Map<String, Player> positionsMapAway;
    private Map<String, Player> positionsMapHome;


    public int homeScore = 0;
    public int awayScore = 0;

    private List<String> homeScorers = new ArrayList<>();
    private List<String> awayScorers= new ArrayList<>();
    private List<String> homeAssisters= new ArrayList<>();
    private List<String> awayAssisters= new ArrayList<>();

    private List<com.example.matchEngine.observerPattern.Observer> observers;

    HashMap<Player, Player> markers = new HashMap<>();   // this needs to be put in its own function



    private Map<String,InGamePlayerStats> homePlayersMatchStatsMap;
    private Map<String,InGamePlayerStats> awayPlayersMatchStatsMap;

    private InGameMatchStats inGameMatchStats = new InGameMatchStats();

    private float time;
    private float addedTime;
    private boolean gameFinished = false;

    private Team attackingTeam;
    private Team defendingTeam;

    private String attackingTeamName;
    private String defendingTeamName;

    private String lastPasserName;

    private boolean homeTeamPoss;

    private boolean startOfGame = true;

    PlayersMatchStats playersMatchStats = new PlayersMatchStats();

    private int pitchPos;
    private Player playerInPosses = null;

    private PlayerDecisions defenderDecisions = new DefenderDecisions(updateInGamePlayerStats, this);
    private PlayerDecisions midfielderDecisions = new MidfielderDecisions(this.updateInGamePlayerStats, this);
    private PlayerDecisions attackerDecisions = new AttackerDecisions(this.updateInGamePlayerStats, this);
    private ShotCalculations shotCalculations = new ShotCalculations(this,this.updateInGamePlayerStats);
    private MatchSetup matchSetup = new MatchSetup(playersMatchStats);


    public MatchEngine(TeamSetup teamSetup, String homeTeamName, String awayTeamName) {
        this.teamSetup = teamSetup;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeam = teamSetup.createTeam(homeTeamName); //should the next three be in teamSetup/ new class?
        this.awayTeam = teamSetup.createTeam(awayTeamName);
        initilizeMarkers();
        this.homePlayersMatchStatsMap = playersMatchStats.createMapfromArray(this.matchSetup.assignPlayersToMatch(this.homeTeam,true).getInGamePlayerStatsArray()); //decipher this
        this.awayPlayersMatchStatsMap= playersMatchStats.createMapfromArray(this.matchSetup.assignPlayersToMatch(this.awayTeam,false).getInGamePlayerStatsArray());
    }


    public void initilizeMarkers() {
        this.markers.put(this.homeTeam.getMcl(), this.awayTeam.getMcr());
        this.markers.put(this.homeTeam.getMcr(), this.awayTeam.getMcl());
        this.markers.put(this.awayTeam.getMcr(), this.homeTeam.getMcl());
        this.markers.put(this.awayTeam.getMcr(), this.homeTeam.getMcl());
    }

    public InGameMatchStats updateInGameMatchStatsTemp(){
        this.inGameMatchStats.setHomePoss(51);
        this.inGameMatchStats.setAwayPoss(49);
        return this.inGameMatchStats;
    }



//
    public void newRunMatchEngine(){
        playGame("kickOff");
    }

    public void playGame(String action){ //how do we deal with teamInPossesion/ attacking team? - keep it as an instance variable?

        while(gameFinished != true){
            if (playerInPosses != null) {
                System.out.println(playerInPosses.getClub());
            } else{
                System.out.println("null");
            }

            System.out.println(homeTeamPoss);
            System.out.println(action);
            System.out.println("///////////////////");
            switch (action){
                case "goalKick":
                case "kickOff":
                    action = kickOff();
                    startOfGame = false;
                    break;
                case "ballOnTheLine":
                    if(homeTeamPoss)
                        playerInPosses = homeTeam.getDcl();
                    else
                        playerInPosses = awayTeam.getDcr();
                    action = "ballInDefence"; //needs to be coded
                    break;
                case "ballInDefence":
                    action = defenderDecisions.playerMakeDecision(pitchPos,homeTeamPoss,playerInPosses,attackingTeam,defendingTeam);
                    break;
                case "ballInDefenceOutWide":
//                    action = fullbackMakeDecisiom();
                case "ballInMidfield":
                    action = midfielderDecisions.playerMakeDecision(pitchPos,homeTeamPoss,playerInPosses,attackingTeam,defendingTeam);
                    break;
                case "looseBallMidfield":
//                    action = looseBallMidfield(); //need to code this
                    break;
                case "ballInAttack": //  it's not a throughBall
                    action = attackerDecisions.playerMakeDecision(pitchPos,homeTeamPoss,playerInPosses,attackingTeam,defendingTeam);
                    break;
                case "oneOnOne": //not all attackers recieve perfect through balls, some have to create own chances with ball to feet/dribbling/pace
//                    action = throughBallOutcome2();
                    action = kickOff();
                    break;
                case "counterAttack":
                    break;
                case "shot": //isnt one on one just a nice shot - no blocks i guess // shouldnt I move this inside the other logic
                    action = shotCalculations.calculateShotChance((OutfieldPlayer)playerInPosses, false);
                    break;
            }
            //increment time somehow - should probably depend on the action and there should be an element of randomness
            if(time > (90 + addedTime)){
                gameFinished = true;
                log.info(action);
                //add the final stats to matchStats and playerStats
                //there are two different types of matchStats - the ingame ones that move ie possesion, goals, xG
                //  and the ones that will be available after the game
                // I think actually these are the same
            }
            time = (float) (time + 0.1);
//            updateInGamePlayerStats.updatePlayerStats(action);
//            updateInGameMatchStats.updateMatchStats(action);

        }

    }

    public String kickOff(){
        if(startOfGame == true){
            coinflip();
        }
        playerInPosses = choosePlayerInPosses();
        //prob should turn posses over
        return "ballInDefence";
    }

    public void coinflip(){ // add the logic here
        attackingTeam = homeTeam;
        attackingTeamName = homeTeamName;
        defendingTeam = awayTeam;
        defendingTeamName = awayTeamName;
        homeTeamPoss = true;
        pitchPos = 1;
    }


    public Player choosePlayerInPosses(){
        return attackingTeam.getDcr();
    }





//

    public String looseBallMidfield(){ //build logic
        return "Placeholder";
    }

    public String isPlayOutFromBackSuccesful2() { //need to add midfielder tackling, should probably  be successful pretty often?
        double passFailure = Math.random() * 10 * 2;
        double firstTouchFailure = Math.random() * 10 * 2;

        OutfieldPlayer playerInPossession = (OutfieldPlayer) attackingTeam.getDcl(); //how to represent this, probably should get an average of all defenders or something, what happens if one defender has 10 less passing than another

        if (playerInPossession.getPassing() > passFailure && attackingTeam.getMcr().getFirstTouch() > firstTouchFailure) { //should be a random midfielder/ a similar algo to the forward/ based on off the ball and stuff, should take into account other midfielder pressure
            return "midfieldPoss";

        }
        return "looseBallMidfield"; //which midfielder should be returned here

    }

    public JSONObject runMatchEngine() {
        Random r = new Random();

//        playBall(homeTeam,awayTeam);
//        playBall(awayTeam,homeTeam);
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
        if(homeScore != 0) {
            for (int i = 0; i < homeScore; i++) {
                JSONObject scorerHome = new JSONObject();
                JSONArray goalsHome = new JSONArray();
                scorerHome.put("name", homeScorers.get(i));
                scorerHome.put("team", "home");
                //I should make a class that does all the random number generation
                int result = r.nextInt(90);
                goalsHome.add(result);
                scorerHome.put("goals", goalsHome);
                scorers.add(scorerHome);

            }
        } else {
            for (int i = 0; i < awayScore; i++) {
                JSONObject scorerHomeNull = new JSONObject();
                JSONArray goalsHomeNull = new JSONArray();
                scorerHomeNull.put("name", "");
                scorerHomeNull.put("team", "home");
                //I should make a class that does all the random number generation
                scorerHomeNull.put("goals", " ");
                scorers.add(scorerHomeNull);
            }

        }
            if(awayScore != 0){
                for(int i = 0; i< awayScore; i++ ) {
                    JSONObject scorerHome = new JSONObject();
                    JSONArray goalsHome = new JSONArray();
                    scorerHome.put("name", awayScorers.get(i));
                    scorerHome.put("team", "away");
                    //I should make a class that does all the random number generation
                    int result = r.nextInt(90);
                    goalsHome.add(result);
                    scorerHome.put("goals", goalsHome);
                    scorers.add(scorerHome);
                }

        }else {
                for (int i = 0; i < homeScore; i++) {
                    JSONObject scorerHomeNull = new JSONObject();
                    JSONArray goalsHomeNull = new JSONArray();
                    scorerHomeNull.put("name", "");
                    scorerHomeNull.put("team", "away");
                    //I should make a class that does all the random number generation
                    scorerHomeNull.put("goals", " ");
                    scorers.add(scorerHomeNull);
                }

            }

//        JSONObject scorerAway = new JSONObject();
//        JSONArray goalsAway = new JSONArray();
//        if(awayScore != 0){
//            for(int i = 0; i< awayScore; i++ ){
//                scorerAway.put("name", awayScorers.get(i));
//                scorerAway.put("team","away");
//                //I should make a class that does all the random number generation
//                int result = r.nextInt(90);
//                goalsAway.add(result);
//                scorerAway.put("goals",goalsAway);
//                scorers.add(scorerAway);
//
//            }
//
//        }
//        scorer1.put("name","Son");
//        scorer1.put("team","home");
//        JSONArray goals1 = new JSONArray();
//        goals1.add("15'");
//        goals1.add("30'");
//        scorer1.put("goals",goals1);

//        JSONObject scorer2 = new JSONObject();
//        scorer2.put("name","Kane");
//        scorer2.put("team","home");
//        JSONArray goals2 = new JSONArray();
//        goals2.add("40'");
//        scorer2.put("goals",goals2);
//
//        JSONObject scorer3 = new JSONObject();
//        scorer3.put("name","Haaland");
//        scorer3.put("team","away");
//        JSONArray goals3 = new JSONArray();
//        goals3.add("40'");
//        scorer3.put("goals",goals3);
//        obj.put("homeScorers", homeScorers.toString().replace("[", "").replace("]", ""));
//        obj.put("awayScorers",awayScorers.toString().replace("[", "").replace("]", ""));
//        obj.put("homeScorers",homeScorers);
//        obj.put("awayScorers",awayScorers);
//        scorers.add(scorer1);
//        scorers.add(scorer2);
//        scorers.add(scorer3);

        JSONObject obj = new JSONObject();
        obj.put("homeTeam",homeTeamName);
        obj.put("awayTeam",awayTeamName);
        obj.put("awayTeamScore",awayScore);
        obj.put("homeTeamScore",homeScore);
        obj.put("Scorer",scorers);
        return obj ;
    }
    public void changePossession(String howBallWasLost){ //is this attempting something similar to the next functiom
        Team temp = this.attackingTeam; //temp = man
        this.attackingTeam = this.defendingTeam; //attacking team = tot
        this.defendingTeam = temp;

        if(howBallWasLost.equals("pass") || howBallWasLost.equals("tackle") || howBallWasLost.equals("temp")) { //other teams player needs to have ball, hardcode for now, should be in a new function which decided which oppo player gets ball
            if(homeTeamPoss){ //obvs tackle and pass should be different aswell - NEEDS TO BE CHANGED
                playerInPosses = awayTeam.getDcr();
            } else{
                playerInPosses = homeTeam.getDcr();
            }
        }
        homeTeamPoss = !homeTeamPoss;

    }

    public void whoHasPossesion(String howBallWasLost){
        if(howBallWasLost == "tackle"){
            //pitch pos stays same
            playerInPosses = markers.get(playerInPosses);
        } else //must be pass for now
        playerInPosses = markers.get(playerInPosses); //same for now
    }

    public void goalScored(String goalScorer){
        log.info("Goal Scored!");
        updateInGamePlayerStats.updateGoalStat(goalScorer);
        if (lastPasserName != null)
            updateInGamePlayerStats.updateAssistStat(lastPasserName);
        if(homeTeamPoss) {
            homeScore ++;
            homeScorers.add(goalScorer);
            homeAssisters.add(lastPasserName); //if null just add null to the list
        } else {
            awayScore++;
            awayScorers.add(goalScorer);
            awayAssisters.add(lastPasserName);
        }
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