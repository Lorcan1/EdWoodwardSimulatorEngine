package com.example.matchEngine.result;

import com.example.matchEngine.observerPattern.Observer;
import com.example.matchEngine.observerPattern.Subject;
import com.example.model.Match;

public class MatchResult implements Observer {

    private Match match;
    public MatchResult(Subject matchEngine){
        matchEngine.registerObserver(this);
    }

    @Override
    public void update(Match match){
        this.match = match;
        displayCurrent();
    }

    public void displayCurrent(){
        System.out.println(Integer.toString(match.getHomeScore()) + Integer.toString(match.getAwayScore()));
    }
}
