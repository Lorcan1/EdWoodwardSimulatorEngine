package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Shot {
    Double xG;
    Boolean onTarget;
    Boolean isGoal;
    Goal goal;
}
