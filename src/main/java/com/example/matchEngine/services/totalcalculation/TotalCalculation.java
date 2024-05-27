package com.example.matchEngine.services.totalcalculation;

import com.example.model.player.InGamePlayerStats;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class TotalCalculation {

    List<InGamePlayerStats> totalInGamePlayerStats;
    DecimalFormat df = new DecimalFormat("#.##");


    public List<InGamePlayerStats> calculateTotal(List<InGamePlayerStats> homePlayerStats, List<InGamePlayerStats> awayPlayerStats ){
        totalInGamePlayerStats= new ArrayList<InGamePlayerStats>();
        totalInGamePlayerStats.add(returnTotal(homePlayerStats,true));
        totalInGamePlayerStats.add(returnTotal(awayPlayerStats,false));
        return totalInGamePlayerStats;
    }

    public InGamePlayerStats returnTotal(List<InGamePlayerStats> playerStats, Boolean home){
        InGamePlayerStats inGamePlayerStatsTotal = new InGamePlayerStats();
        inGamePlayerStatsTotal.setHome(home);
        int total = 0;
        int condition = 0;
        inGamePlayerStatsTotal.setCondition(0);

        for(InGamePlayerStats inGamePlayerStats: playerStats){
            inGamePlayerStatsTotal.setShots(inGamePlayerStatsTotal.getShots() + inGamePlayerStats.getShots());
            inGamePlayerStatsTotal.setOnTarget(inGamePlayerStatsTotal.getOnTarget() + inGamePlayerStats.getOnTarget());
            inGamePlayerStatsTotal.setKeyChancesCreated(inGamePlayerStatsTotal.getKeyChancesCreated() + inGamePlayerStats.getKeyChancesCreated());
            inGamePlayerStatsTotal.setGoals(inGamePlayerStatsTotal.getGoals() + inGamePlayerStats.getGoals());
            inGamePlayerStatsTotal.setXG(inGamePlayerStatsTotal.getXG() + inGamePlayerStats.getXG());
            inGamePlayerStatsTotal.setAssists(inGamePlayerStatsTotal.getAssists() + inGamePlayerStats.getAssists());
            inGamePlayerStatsTotal.setExcpectedAssists(inGamePlayerStatsTotal.getExcpectedAssists() + inGamePlayerStats.getExcpectedAssists());
            inGamePlayerStatsTotal.setKeyPasses(inGamePlayerStatsTotal.getKeyPasses() + inGamePlayerStats.getKeyPasses());
            inGamePlayerStatsTotal.setPasses(inGamePlayerStatsTotal.getPasses() + inGamePlayerStats.getPasses());
            inGamePlayerStatsTotal.setTouches(inGamePlayerStatsTotal.getTouches() + inGamePlayerStats.getTouches());
            inGamePlayerStatsTotal.setPassCompletionPercentage(inGamePlayerStatsTotal.getPassCompletionPercentage() + inGamePlayerStats.getPassCompletionPercentage());
            inGamePlayerStatsTotal.setTackles(inGamePlayerStatsTotal.getTackles() + inGamePlayerStats.getTackles());
            inGamePlayerStatsTotal.setCondition(inGamePlayerStatsTotal.getCondition() + inGamePlayerStats.getCondition());
            inGamePlayerStatsTotal.setBlocks(inGamePlayerStatsTotal.getBlocks() + inGamePlayerStats.getBlocks());
            inGamePlayerStatsTotal.setDribbles(inGamePlayerStatsTotal.getDribbles() + inGamePlayerStats.getDribbles());
            inGamePlayerStatsTotal.setRating(inGamePlayerStatsTotal.getRating() + inGamePlayerStats.getRating());
            total +=1;
        }
        inGamePlayerStatsTotal.setCondition(inGamePlayerStatsTotal.getCondition()/total);
        inGamePlayerStatsTotal.setPassCompletionPercentage(inGamePlayerStatsTotal.getPassCompletionPercentage()/total);
//        inGamePlayerStatsTotal.setPassCompletionPercentage(Math.round(inGamePlayerStatsTotal.getPassCompletionPercentage() * 100) / 100);

        String formattedValue1 = df.format(inGamePlayerStatsTotal.getPassCompletionPercentage());
        double newXG1 = Double.parseDouble(formattedValue1);
        inGamePlayerStatsTotal.setPassCompletionPercentage(newXG1);

        String formattedValue = df.format(inGamePlayerStatsTotal.getXG());
        double newXG = Double.parseDouble(formattedValue);
        inGamePlayerStatsTotal.setXG(newXG);
        return inGamePlayerStatsTotal;
    }
}
