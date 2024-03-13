package com.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Goal {

    String scorerName;
    String assisterName;
    String time;
    Double xG;

    public Goal(String scorerName, String assistName, String currentTime) {
    }
}
