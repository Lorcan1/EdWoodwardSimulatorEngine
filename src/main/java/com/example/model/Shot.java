package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shot {
    Double xG;
    Boolean onTarget;
    Boolean isGoal;
    Goal goal;
}
