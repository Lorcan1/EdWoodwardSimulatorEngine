package com.example.matchEngine.services.matchrepositoryservice;

import com.example.model.InGameMatchStats;
import com.example.model.matchmodel.Match;
import com.example.repository.matchrepository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MatchRepositoryService {

    @Autowired
    MatchRepository matchRepository;

    public void saveMatch(InGameMatchStats inGameMatchStats){
        Match match = new Match(inGameMatchStats.getHomeTeam(),inGameMatchStats.getHomeScore(),inGameMatchStats.getAwayTeam(), inGameMatchStats.getAwayScore(),
                inGameMatchStats.getMatchdate());
        matchRepository.save(match);
    }

    public List<Match> fetchMatches(){
        return matchRepository.findMatchesByDate(new Date(System.currentTimeMillis()));
    }
}
