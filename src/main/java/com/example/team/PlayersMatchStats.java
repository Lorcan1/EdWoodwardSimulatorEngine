package com.example.team;

import com.example.model.player.InGamePlayerStats;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class PlayersMatchStats {
    InGamePlayerStats inGamePlayerStats1;
    InGamePlayerStats inGamePlayerStats2;
    ArrayList<InGamePlayerStats> inGamePlayerStatsArray;

}
