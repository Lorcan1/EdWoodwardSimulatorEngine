package com.example.model.player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerMatchStats {
    String pos;
    String name;
    String club;
    int shots;
    int onTarget;
    int keyChanesCreated;
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
