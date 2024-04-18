package com.example.simulation;

import com.example.matchEngine.engine.MatchEngineLogic;
import com.example.matchEngine.result.MatchResult;
import com.example.model.Match;
import com.example.services.AbbrevService;
import com.example.team.ITeamSetup;
import com.example.team.TeamSetupLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Simulate {
    ITeamSetup teamSetup;
    MatchEngineLogic matchEngineLogic;
    AbbrevService abbrevService;
    MatchResult matchResult;

    @Autowired
    public Simulate(TeamSetupLogic teamSetupLogic, MatchEngineLogic matchEngineLogic, AbbrevService abbrevService, MatchResult matchResult){
        this.teamSetup = teamSetupLogic;
        this.matchEngineLogic = matchEngineLogic;
        this.abbrevService = abbrevService;
        this.matchResult = matchResult;

    }
    //we want to create two teams of 11 players (currently 11) and pass them to match engine

//    MatchEngine matchEngine = new MatchEngine(teamSetup, homeClubNameFull, awayClubNameFull);
    //currently this is how we call match Engine

//            this.homeTeam = teamSetup.createTeam(homeTeamName); //should the next three be in teamSetup/ new class?
//        this.awayTeam = teamSetup.createTeam(awayTeamName);

    // of course this is breaking SOLID Principles, why is match engine in charge of making that call


    public String simulateMatch(String homeTeamNameAbbrev, String awayTeamNameAbbrev){
        String homeTeamName = abbrevService.returnFullName(homeTeamNameAbbrev);
        String awayTeamName = abbrevService.returnFullName(awayTeamNameAbbrev);
        createTeams(homeTeamName, awayTeamName);
        log.info("Created Teams");
        matchEngineLogic.setHomeTeamNameAbbrev(homeTeamNameAbbrev);
        matchEngineLogic.setAwayTeamNameAbbrev(awayTeamNameAbbrev);
        matchEngineLogic.simulateMatch(homeTeamName, awayTeamName);
        processResult();
        return String.valueOf((matchEngineLogic.getUpdateInGameMatchStats().getInGameMatchStats().getHomeScore() + ":" +
                matchEngineLogic.getUpdateInGameMatchStats().getInGameMatchStats().getAwayScore()));
    }
    public void createTeams(String homeTeamName, String awayTeamName){
        matchEngineLogic.setHomeTeam(teamSetup.createTeam(homeTeamName));
        matchEngineLogic.setAwayTeam(teamSetup.createTeam(awayTeamName));

    }

    public void processResult() {
        Match match = new Match(matchEngineLogic.getUpdateInGameMatchStats().getInGameMatchStats());
        matchResult.processScore(match);

    }
}




