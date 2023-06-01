package com.example.team;

import com.example.model.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Team {
    private final List<String> positions= new ArrayList<>();
    private final String teamName;
    private final TeamSetup teamSetup;

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


    public Team(String teamName, TeamSetup teamSetup) {
        this.teamName = teamName;
        this.teamSetup = teamSetup;
        List<Player> team = teamSetup.returnStartingEleven(teamName);
            for (Player player : team) {
                if (player.getStartingPosition().equals("GK")) {
                    this.gk = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("DL")){
                   this.dl = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("DCL")){
                    this.dcl = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("DCR")){
                    this.dcr = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("DR")){
                    this.dr = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("DM")){
                    this.dm = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("MR")){
                    this.mr = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("MCR")){
                    this.mcr = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("MCL")){
                    this.mcl = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("ML")){
                    this.ml = player;
                    players.add(player);
                }else if(player.getStartingPosition().equals("ST")){
                    this.st = player;
                    players.add(player);
                }
            }

        }
    }
