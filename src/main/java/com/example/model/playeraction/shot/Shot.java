package com.example.model.playeraction.shot;

import com.example.model.playeraction.PlayerAction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shot extends PlayerAction {
    Double xG;
    Boolean onTarget = false;
    Boolean isGoal = false;
    Goal goal;
}
