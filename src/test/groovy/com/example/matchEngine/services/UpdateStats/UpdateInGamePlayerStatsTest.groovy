package com.example.matchEngine.services.UpdateStats

import com.example.matchEngine.services.UpdateStats.UpdateInGamePlayerStats
import com.example.model.player.OutfieldPlayer
import com.example.model.player.Player
import com.example.model.playeraction.PlayerAction
import com.example.model.playeraction.pass.Pass
import com.example.model.playeraction.touch.Touch
import spock.lang.Shared
import spock.lang.Specification

class UpdateInGamePlayerStatsTest extends Specification{

    @Shared
    OutfieldPlayer testHomePlayer = new OutfieldPlayer();
    @Shared
    OutfieldPlayer testAwayPlayer = new OutfieldPlayer();
    @Shared
    List<Player> homePlayers = new ArrayList<>()
    @Shared
    List<Player> awayPlayers = new ArrayList<>()
    @Shared
    UpdateInGamePlayerStats updateInGamePlayerStats = new UpdateInGamePlayerStats()


    def setupSpec() {
        // Initialize the shared resource here
        testHomePlayer.setLastName("testHomePlayer")
        homePlayers.add(testHomePlayer)
        testAwayPlayer.setLastName("testAwayPlayer")
        awayPlayers.add(testAwayPlayer)
        updateInGamePlayerStats.setHomeTeamPlayersStats(updateInGamePlayerStats.initializeInGamePlayerStats(homePlayers, true))
        updateInGamePlayerStats.setAwayTeamPlayersStats(updateInGamePlayerStats.initializeInGamePlayerStats(awayPlayers, false))
    }


    def "test pass"(){

        HashMap<String, PlayerAction> hashMapTest = new HashMap<>()
        Pass pass = new Pass();
        hashMapTest.put("testHomePlayer", pass )

        updateInGamePlayerStats.updateInGamePlayerStats(hashMapTest)

        expect:
        updateInGamePlayerStats.getHomeTeamPlayersStats().get("testHomePlayer").getPasses() == 1
        updateInGamePlayerStats.getAwayTeamPlayersStats().get("testAwayPlayer").getPasses() == 0
    }

    def "test touch"(){
        HashMap<String,PlayerAction> hashMapTest = new HashMap<>()
        Touch touch = new Touch();
        hashMapTest.put("testHomePlayer", touch)

        updateInGamePlayerStats.updateInGamePlayerStats(hashMapTest)

        expect:
        updateInGamePlayerStats.getHomeTeamPlayersStats().get("testHomePlayer").getTouches() == 1
        updateInGamePlayerStats.getAwayTeamPlayersStats().get("testAwayPlayer").getTouches() == 0
    }
}
