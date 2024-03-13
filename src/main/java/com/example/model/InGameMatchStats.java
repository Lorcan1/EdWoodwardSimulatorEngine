package com.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InGameMatchStats {
    int homePoss;
    int awayPoss;
    int homeShots;
    int awayShots;
    int homeShotsOnT;
    int awayShotsOnT;
    Double homexG;
    Double awayxG;
    List<Goal> homeGoals;
    List<Goal> awayGoals;
}
