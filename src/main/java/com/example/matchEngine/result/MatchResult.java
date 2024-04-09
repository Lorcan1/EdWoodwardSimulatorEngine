package com.example.matchEngine.result;

import com.example.model.Match;

public class MatchResult {

    private Match match;

    public void update(Match match){
        this.match = match;
        displayCurrent();
    }

    public void displayCurrent(){
        System.out.println(Integer.toString(match.getHomeScore()) + Integer.toString(match.getAwayScore()));
    }
}
