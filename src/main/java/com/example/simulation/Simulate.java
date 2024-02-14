package com.example.simulation;

import com.example.matchEngine.engine.MatchEngineLogic;
import com.example.team.ITeamSetup;
import com.example.team.TeamSetupLogic;

public class Simulate {
    ITeamSetup teamSetup;
    MatchEngineLogic matchEngineLogic;
    String homeTeamName;
    String awayTeamName;

    public Simulate(TeamSetupLogic teamSetupLogic, String homeTeamName, String awayTeamName){
        this.teamSetup = teamSetupLogic;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.matchEngineLogic = new MatchEngineLogic(homeTeamName, awayTeamName);
    }
    //we want to create two teams of 11 players (currently 11) and pass them to match engine

//    MatchEngine matchEngine = new MatchEngine(teamSetup, homeClubNameFull, awayClubNameFull);
    //currently this is how we call match Engine

//            this.homeTeam = teamSetup.createTeam(homeTeamName); //should the next three be in teamSetup/ new class?
//        this.awayTeam = teamSetup.createTeam(awayTeamName);

    // of course this is breaking SOLID Principles, why is match engine in charge of making that call


    public void simulateMatch(){
        createTeams();
        matchEngineLogic.simulateMatch();
    }
    public void createTeams(){
        matchEngineLogic.setHomeTeam(teamSetup.createTeam(homeTeamName));
        matchEngineLogic.setAwayTeam(teamSetup.createTeam(awayTeamName));
    }
}




