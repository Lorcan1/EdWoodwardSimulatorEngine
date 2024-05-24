package com.example.matchEngine.services.defensiveservice;

import com.example.model.player.OutfieldPlayer;
import com.example.model.player.Player;
import com.example.team.Team;
import org.springframework.stereotype.Component;

@Component
public class MidfielderService {

    public int homeMidfieldersTacklingAv;
    public int awayMidfieldersTacklingAv;


    //take in a certain number of midfielders and get the average defensive ability in order to apply a debuff to the oposition

    //lets just take the three midfielders, not sure how it will work with different formations
    // sh

    public void setMidfielders(Team team, String homeOrAway){
            int totalTacking = 0;
            for(Player player: team.getMidfielders().values()){
                OutfieldPlayer outfieldPlayer = (OutfieldPlayer) player;
                 totalTacking += outfieldPlayer.getTackling();
            }
        if(homeOrAway.equals("home")){
            homeMidfieldersTacklingAv = totalTacking/ team.getMidfielders().size();

        } else if(homeOrAway.equals("away")){
            awayMidfieldersTacklingAv = totalTacking/ team.getMidfielders().size();
        } else{
            throw new IllegalArgumentException();
        }

    }

}
