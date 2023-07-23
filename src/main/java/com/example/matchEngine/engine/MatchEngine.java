package com.example.matchEngine.engine;

import com.example.matchEngine.matchSetup.MatchSetup;
import com.example.matchEngine.observerPattern.Observer;
import com.example.matchEngine.observerPattern.Subject;
import com.example.matchEngine.playerDecisions.DefenderDecisions;
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
import java.util.stream.Collectors;

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
public class MatchEngine implements Subject {


    private UpdateInGameMatchStats updateInGameMatchStats;
    private UpdateInGamePlayerStats updateInGamePlayerStats;

    //need to change the design so that an endpoint can be hit and every time its hit the gamestate moves on a chunk of time until the game is over
    private final TeamSetup teamSetup;
    private Team homeTeam;

    private Team awayTeam;

    public String homeTeamName;
    public String awayTeamName;

    private Map<String, Player> positionsMapAway;
    private Map<String, Player> positionsMapHome;


    public int homeScore;
    public int awayScore;

    private List<String> homeScorers = new ArrayList<>();
    private List<String> awayScorers= new ArrayList<>();
    private List<String> homeAssisters= new ArrayList<>();
    private List<String> awayAssisters= new ArrayList<>();

    private List<com.example.matchEngine.observerPattern.Observer> observers;

    HashMap<Player, Player> markers;

    private Match match;

    private Map<String,InGamePlayerStats> homePlayersMatchStatsMap;
    private Map<String,InGamePlayerStats> awayPlayersMatchStatsMap;

    private InGameMatchStats inGameMatchStats;

    private float time;
    private float addedTime;
    private boolean gameFinished = false;

    private Team attackingTeam;
    private Team defendingTeam;

    private String attackingTeamName;
    private String defendingTeamName;

    private boolean homeTeamPoss;

    private boolean startOfGame;

    PlayersMatchStats playersMatchStats = new PlayersMatchStats();

    private int pitchPos;
    Player playerInPosses;

    private DefenderDecisions defenderDecisions;
    private MatchSetup matchSetup;


    public MatchEngine(TeamSetup teamSetup, String homeTeamName, String awayTeamName) {
        this.teamSetup = teamSetup;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeam = new Team(homeTeamName,teamSetup);
        this.awayTeam = new Team(awayTeamName,teamSetup);
        this.markers = new HashMap<>();   // this needs to be put in its own function
        markers.put(homeTeam.getMcl() ,awayTeam.getMcr());
        markers.put(homeTeam.getMcr() ,awayTeam.getMcl());
        markers.put(awayTeam.getMcr() ,homeTeam.getMcl());
        markers.put(awayTeam.getMcr() ,homeTeam.getMcl());
        this.observers = new ArrayList<>();
        this.match = new Match();//need to set an Id and give it to the player stats
        this.matchSetup = new MatchSetup(playersMatchStats);
        this.homePlayersMatchStatsMap = playersMatchStats.createMapfromArray(this.matchSetup.assignPlayersToMatch(this.homeTeam,true).getInGamePlayerStatsArray());
        this.awayPlayersMatchStatsMap= playersMatchStats.createMapfromArray(this.matchSetup.assignPlayersToMatch(this.awayTeam,false).getInGamePlayerStatsArray());
        this.inGameMatchStats = new InGameMatchStats();
        this.startOfGame = true;
        this.updateInGameMatchStats = new UpdateInGameMatchStats(this.match);
        this.updateInGamePlayerStats = new UpdateInGamePlayerStats(this.homePlayersMatchStatsMap,this.awayPlayersMatchStatsMap);
        this.defenderDecisions = new DefenderDecisions(this.updateInGamePlayerStats, this);
    }

    @Override
    public void registerObserver(com.example.matchEngine.observerPattern.Observer o){
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
    public void notifyObservers(){ //look at this for updating match at the end before pushing to db, what about UpdateMatchStats in this equation
        observers.forEach(o->o.update(match)); //need to review how this works
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
            switch (action){
                case "kickOff":
                    action = kickOff();
                    startOfGame = false;
                    break;
                case "ballOnTheLine":
                    action = "defenderPoss"; //needs to be coded
                    break;
                case "ballInDefence":
                    action = defenderDecisions.defenderMakeDecision(pitchPos,homeTeamPoss,playerInPosses,attackingTeam,defendingTeam);
                    break;
                case "ballInDefenceOutWide":
//                    action = fullbackMakeDecisiom();
                case "ballInMidfield":
                    action = "Midfield pending";
                    break;
                case "looseBallMidfield":
//                    action = looseBallMidfield(); //need to code this
                    break;
                case "ballInAttack": //  it's not a throughBall
//                    action = attakerRecievesBall
                    break;
                case "oneOnOne": //not all attackers recieve perfect through balls, some have to create own chances with ball to feet/dribbling/pace
//                    action = throughBallOutcome2();
                    break;
                case "counterAttack":
                    break;
            }
            //increment time somehow - should probably depend on the action and there should be an element of randomness
            if(time > (90 + addedTime)){
                gameFinished = true;
                //add the final stats to matchStats and playerStats
                //there are two different types of matchStats - the ingame ones that move ie possesion, goals, xG
                //  and the ones that will be available after the game
                // I think actually these are the same
            }
            time = time + 50;
//            updateInGamePlayerStats.updatePlayerStats(action);
//            updateInGameMatchStats.updateMatchStats(action);

        }

    }

    public String kickOff(){
        if(startOfGame == true){
            coinflip();
        }
        Player playerInPosses = choosePlayerInPosses();
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

    public void changePossession(){
        Team temp = this.attackingTeam;
        this.attackingTeam = this.defendingTeam;
        this.defendingTeam = temp;
        homeTeamPoss = !homeTeamPoss;
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