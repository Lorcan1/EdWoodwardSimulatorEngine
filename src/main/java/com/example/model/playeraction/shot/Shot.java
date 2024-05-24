package com.example.model.playeraction.shot;

import com.example.model.playeraction.PlayerAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shot extends PlayerAction {
    double xG;
    Boolean onTarget = false;
    Boolean isGoal = false;
    Goal goal;
    final String type = "shot";

    @Override
    public List getResponseList(List responses) {
        responses.add(" shoots!");
        return responses;
    }
}