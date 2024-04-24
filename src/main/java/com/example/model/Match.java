package com.example.model;

import com.example.model.playeraction.shot.Goal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Match extends InGameMatchStats {
    private static int nextId = 1;
}
