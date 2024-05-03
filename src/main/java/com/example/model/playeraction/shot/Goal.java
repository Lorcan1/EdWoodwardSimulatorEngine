package com.example.model.playeraction.shot;

import com.example.model.playeraction.PlayerAction;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
public class Goal extends PlayerAction {

    String scorerName;
    String assisterName;
    String time;
    final String type = "goal";


    @Override
    public List getResponseList(List responses) {
        responses.add(" scores");
        responses.add(" finishes a fine move");
        responses.add(" puts it past player2");
        responses.add(" smashes it past player2");
        return responses;
    }

}
