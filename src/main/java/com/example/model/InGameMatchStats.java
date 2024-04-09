package com.example.model;

import com.example.model.playeraction.shot.Goal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
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
}
