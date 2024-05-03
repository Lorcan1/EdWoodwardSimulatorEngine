package com.example.model.playeraction;

import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class PlayerAction {
    Boolean homeTeamPoss;
    String player1;
    String type;

    public abstract List getResponseList(List response);

//    public abstract void updateStats(InGamePlayerStats playerStats);
//
//    public abstract void getClassName

}