package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Match {
    private static int nextId = 1;

//    public Match(String homeTeam, String awayTeam, int homeScore, int awayScore, List<String> homeScorers, List<String> homeAssisters,List<String> awayScorers,
//                 List<String> awayAssisters ){
//        this.id = nextId++;
//        this.matchdate = null;
//        this.homeTeam = homeTeam;
//        this.awayTeam = awayTeam;
//        this.homeScore = homeScore;
//        this.awayScore = awayScore;
//        this.homeScorers = homeScorers;
//        this.homeAssisters = homeAssisters;
//        this.awayScorers = awayScorers;
//        this.awayAssisters = awayAssisters;
//        this.playerOfTheMatch = null;
//
//    }


    private long matchId;
    private Date matchdate;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private int homePoss;
    private int awayPoss;
    private int homeShots;
    private int awayShots;
    private int homeOnTarget;
    private int awayOnTarget;
    private float homeXG;
    private float awayXG;
    private List<String> homeScorers;
    private List<String> homeAssisters;
    private List<String> awayScorers;
    private List<String> awayAssisters;
    private String playerOfTheMatch;

}
