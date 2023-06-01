package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Match {

    private String id;
    private Date matchdate;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private List<String> homeScorers;
    private List<String> homeAssisters;
    private List<String> awayScorers;
    private List<String> awayAssisters;
    private String playerOfTheMatch;

}
