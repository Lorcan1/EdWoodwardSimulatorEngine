package com.example.model.playeraction.touch;

import com.example.model.playeraction.PlayerAction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Touch extends PlayerAction {
    final String type = "touch";

    @Override
    public List getResponseList(List responses) {
        responses.add(" controls it");
        return responses;
    }
}