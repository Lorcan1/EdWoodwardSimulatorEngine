package com.example.team;

import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class Team {
    private  List<String> positions= new ArrayList<>();
    private  String teamName;
    private TeamSetupLogic teamSetupLogic;
    private Player gk;
    private  Player dl;
    private  Player dcl;
    private  Player dcr;
    private  Player dr;
    private  Player dm;
    private  Player mr;
    private  Player mcr;
    private  Player mcl;
    private  Player ml;
    private  Player st;

    private List<Player> players = new ArrayList<>();

    private HashMap<String, Player> defenders = new HashMap<>();
    private HashMap<String, Player> midfielders = new HashMap<>();
    private HashMap<String, Player> attackers = new HashMap<>();

    }
