package com.example.model;

import com.example.model.playeraction.shot.Goal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    // Copy constructor
    public InGameMatchStats(InGameMatchStats stats) {
        this.homePoss = stats.homePoss;
        this.awayPoss = stats.awayPoss;
        this.homeShots = stats.homeShots;
        this.awayShots = stats.awayShots;
        this.homeShotsOnT = stats.homeShotsOnT;
        this.awayShotsOnT = stats.awayShotsOnT;
        this.homexG = stats.homexG;
        this.awayxG = stats.awayxG;
        // Deep copy for mutable objects
        this.homeGoals = new ArrayList<>(stats.homeGoals);
        this.awayGoals = new ArrayList<>(stats.awayGoals);
        this.homeScore = stats.homeScore;
        this.awayScore = stats.awayScore;
    }
}
