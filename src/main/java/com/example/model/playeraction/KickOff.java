package com.example.model.playeraction;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@SuperBuilder
@Getter
public class KickOff extends PlayerAction{

    final String type = "kickOff";
    @Override
    public List getResponseList(List responses) {
        responses.add(" kicks off");
        responses.add(" gets us underway");
        return responses;
    }
}
