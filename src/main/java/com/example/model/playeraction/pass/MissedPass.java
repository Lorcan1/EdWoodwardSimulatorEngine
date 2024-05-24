package com.example.model.playeraction.pass;

import com.example.model.playeraction.PlayerAction;
import lombok.Getter;

import java.util.List;

@Getter
public class MissedPass extends PlayerAction {

    String type = "missedPass";
    @Override
    public List getResponseList(List response) {
        response.add(" give it away");
        return response;
    }
}
