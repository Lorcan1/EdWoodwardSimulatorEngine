package com.example.model.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InGamePlayerStats {
    long playerStatsID;
    long matchId;
    String pos;
    String name;
    String club;
    Boolean home;
    int shots;
    int onTarget;
    int keyChancesCreated;
    int goals;
    float expGoals;
    int assists;
    float excpectedAssists;
    int keyPasses;
    int passes;
    int touches;
    float passCompletionPercentage;
    int tackles;
    int condition;
    String card;
    int blocks;
    int dribbles;
    float rating;
    int playerID;
}
