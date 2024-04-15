package com.example.model.playeraction.shot;

import com.example.model.playeraction.PlayerAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
