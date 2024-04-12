package com.example.matchEngine;

import com.example.model.player.Goalkeeper;
import com.example.model.player.Player;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SaveCalculation {
    Random random = new Random();
    public boolean isShotSaved(int shotChance, Goalkeeper goalkeeper){
        //lets do reflexes and composure
        int saveChance = random.nextInt(100) + 1;
        saveChance = saveChance + ((goalkeeper.getReflexes() + goalkeeper.getComposure())/2);

        if(saveChance > shotChance){
            //what a save
        } else{
            //goal
        }
        return true;

    }
    public boolean isLongShotSaved(int shotChance, Goalkeeper goalkeeper){
        //lets do reflexes and composure
        int saveChance = random.nextInt(100) + 1;
        saveChance = saveChance + (goalkeeper.getReflexes() + goalkeeper.getComposure());
        if(saveChance > shotChance){
            return false;
        } else{
            return true;
        }

    }
}
