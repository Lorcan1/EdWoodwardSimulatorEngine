package com.example.matchEngine.services.matchrepositoryservice;

import com.example.model.InGameMatchStats;
import com.example.model.results.Results;
import com.example.repository.fixturesrepository.FixturesRepository;
import com.example.repository.resultsrepository.ResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MatchRepositoryService {

    @Autowired
    ResultsRepository resultsRepository;

    public void saveMatch(InGameMatchStats inGameMatchStats){
        Results results = new Results(inGameMatchStats.getHomeTeam(),inGameMatchStats.getHomeScore(),inGameMatchStats.getAwayTeam(), inGameMatchStats.getAwayScore(),
                inGameMatchStats.getMatchdate());
        resultsRepository.save(results);
    }

    public List<Results> fetchMatches(){
        return resultsRepository.findMatchesByDate(new Date(System.currentTimeMillis()));
    }
}
