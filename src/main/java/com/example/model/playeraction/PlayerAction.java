package com.example.model.playeraction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerAction {
    Boolean homeTeamPoss;
    String player1;

    public String getClassName() {
        return this.getClass().getSimpleName();
    }
}
