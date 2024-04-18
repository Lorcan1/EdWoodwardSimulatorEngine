package com.example.model;

import com.example.model.playeraction.shot.Goal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Match extends InGameMatchStats {
    private static int nextId = 1;

    private long matchId;
    private Date matchdate;
    private String homeTeam;
    private String awayTeam;
    private List<String> homeScorers;
    private List<String> homeAssisters;
    private List<String> awayScorers;
    private List<String> awayAssisters;
    private String playerOfTheMatch;

    public Match(InGameMatchStats stats) {
        super(stats);
    }
}
