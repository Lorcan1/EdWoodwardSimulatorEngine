package com.example.model;

import com.example.model.playeraction.shot.Goal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InGameMatchStats {
    int homePoss;
    int awayPoss;
    int homeShots;
    int awayShots;
    int homeShotsOnT;
    int awayShotsOnT;
    Double homexG = 0.0;
    Double awayxG = 0.0;
    List<Goal> homeGoals = new ArrayList<>();
    List<Goal> awayGoals = new ArrayList<>();
    int homeScore;
    int awayScore;

    private long matchId;
    private Date matchdate;
    private String homeTeam;
    private String awayTeam;
    private List<String> homeScorers;
    private List<String> homeAssisters;
    private List<String> awayScorers;
    private List<String> awayAssisters;
    private String playerOfTheMatch;

}
