package com.example.matchEngine.result;


import com.example.model.InGameMatchStats;
import com.example.model.Match;
import com.example.model.playeraction.shot.Goal;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@Slf4j
public class MatchResult {

    Match match;
    JSONObject jsonObject = new JSONObject();

    public void processScore(Match match){
        String jsonString = null;

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonString = objectMapper.writeValueAsString(match);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
       log.info(jsonString);




    }
}
