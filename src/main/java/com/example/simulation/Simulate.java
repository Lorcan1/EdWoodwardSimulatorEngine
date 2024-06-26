package com.example.simulation;

import com.example.matchEngine.engine.MatchEngineLogic;
import com.example.matchEngine.services.matchjsonservice.MatchJSONService;
import com.example.matchEngine.services.matchrepositoryservice.MatchRepositoryService;
import com.example.services.abbrevservice.AbbrevService;
import com.example.team.ITeamSetup;
import com.example.team.TeamSetupLogic;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Simulate {
    ITeamSetup teamSetup;
    MatchEngineLogic matchEngineLogic;
    AbbrevService abbrevService;
    MatchJSONService matchJSONService;
    MatchRepositoryService matchRepositoryService;

    @Autowired
    public Simulate(TeamSetupLogic teamSetupLogic, MatchEngineLogic matchEngineLogic, AbbrevService abbrevService, MatchJSONService matchJSONService,
                    MatchRepositoryService matchRepositoryService){
        this.teamSetup = teamSetupLogic;
        this.matchEngineLogic = matchEngineLogic;
        this.abbrevService = abbrevService;
        this.matchJSONService = matchJSONService;
        this.matchRepositoryService = matchRepositoryService;

    }
    //we want to create two teams of 11 players (currently 11) and pass them to match engine

//    MatchEngine matchEngine = new MatchEngine(teamSetup, homeClubNameFull, awayClubNameFull);
    //currently this is how we call match Engine

//            this.homeTeam = teamSetup.createTeam(homeTeamName); //should the next three be in teamSetup/ new class?
//        this.awayTeam = teamSetup.createTeam(awayTeamName);

    // of course this is breaking SOLID Principles, why is match engine in charge of making that call

    public JSONObject initiateMatch(String homeTeamNameAbbrev, String awayTeamNameAbbrev){
        String homeTeamName = abbrevService.returnFullName(homeTeamNameAbbrev);
        String awayTeamName = abbrevService.returnFullName(awayTeamNameAbbrev);
        createTeams(homeTeamName, awayTeamName);
        log.info("Created Teams");
        matchEngineLogic.setHomeTeamNameAbbrev(homeTeamNameAbbrev);
        matchEngineLogic.setAwayTeamNameAbbrev(awayTeamNameAbbrev);
        matchEngineLogic.setHomeTeamName(homeTeamName);
        matchEngineLogic.setAwayTeamName(awayTeamName);
        matchEngineLogic.setupMatch();
        return matchJSONService.processInitialMatchResponse(matchEngineLogic.getUpdateInGamePlayerStats().getHomeTeamPlayersStats(),
                matchEngineLogic.getUpdateInGamePlayerStats().getAwayTeamPlayersStats(), matchEngineLogic.getUpdateInGameMatchStats());
    }


    public JSONObject simulateMatch(String homeTeamNameAbbrev, String awayTeamNameAbbrev){
        matchEngineLogic.simulateMatch();
        processResult();

        return matchJSONService.processFinalMatchResponse(matchEngineLogic.getUpdateInGamePlayerStats().getHomeTeamPlayersStats(),
                matchEngineLogic.getUpdateInGamePlayerStats().getAwayTeamPlayersStats(), matchEngineLogic.getUpdateInGameMatchStats(),
        matchEngineLogic.getFeedService().getFeedList(), matchEngineLogic.getTotalCalculation().getTotalInGamePlayerStats());
    }
    public void createTeams(String homeTeamName, String awayTeamName){
        matchEngineLogic.setHomeTeam(teamSetup.createTeam(homeTeamName));
        matchEngineLogic.setAwayTeam(teamSetup.createTeam(awayTeamName));

    }

    public void processResult() {
        matchJSONService.processScore(matchEngineLogic.getUpdateInGameMatchStats().getInGameMatchStats());
        Date currentDate = new Date(System.currentTimeMillis());
        matchEngineLogic.getUpdateInGameMatchStats().getInGameMatchStats().setMatchdate(currentDate); // date needs to be added and handled
        matchEngineLogic.getFeedService().setFeedList(matchEngineLogic.getFeedService().postProcess(matchEngineLogic.getFeedService().getFeedServiceHelperList()));
        matchRepositoryService.saveMatch(matchEngineLogic.getUpdateInGameMatchStats().getInGameMatchStats());


    }
}




