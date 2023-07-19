package com.example.controller;

import com.example.matchEngine.MatchEngine;
import com.example.matchEngine.MatchResult;
import com.example.model.player.Goalkeeper;
import com.example.model.player.InGamePlayerStats;
import com.example.model.player.OutfieldPlayer;
import com.example.model.player.Player;
import com.example.repository.GoalkeeperRepository;
import com.example.repository.OutfieldPlayerRepository;
import com.example.team.TeamSetup;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

//@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    // standard constructors

    private final OutfieldPlayerRepository outfieldPlayerRepository;
    private final GoalkeeperRepository goalkeeperRepository;
    private final TeamSetup teamSetup;
    Map<String, Player> positions= new HashMap<>();



    public Controller(OutfieldPlayerRepository outfieldPlayerRepository, GoalkeeperRepository goalkeeperRepository, TeamSetup teamSetup) {
        this.outfieldPlayerRepository = outfieldPlayerRepository;
        this.goalkeeperRepository = goalkeeperRepository;
        this.teamSetup = teamSetup;
    }

    @GetMapping("/players-names")
    public List<String> getUsers() {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll();
        List<String> secondNames = new ArrayList<>();
        for(OutfieldPlayer outfieldPlayer: outfieldPlayers){
            secondNames.add(outfieldPlayer.getLastName());
        }
        return secondNames;
    }

    @GetMapping("/get-heading") //http://localhost:8080/get-heading?name=Laporte
    public int getHeading(@RequestParam(value = "name", defaultValue = "Haaland") String name) {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll();
        for(OutfieldPlayer player: outfieldPlayers){
            if(player.getLastName().equals(name)){
                return player.getHeading();
            }
        }
        return 404;
    }

    @GetMapping("/get-player") //http://localhost:8080/get-heading?name=Laporte
    public String getPlayer(@RequestParam(value = "name", defaultValue = "Haaland") String name) throws JsonProcessingException {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll() ;
        for(OutfieldPlayer player: outfieldPlayers){
            if(player.getLastName().equals(name)){
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(player);
            }
        }
        return "Not found";
    }

    @GetMapping("/get-player-id") //http://localhost:8080/get-heading?name=Laporte
    public String getPlayerId(@RequestParam(value = "id", defaultValue = "29179241") int id) throws JsonProcessingException {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll() ;
        for(OutfieldPlayer player: outfieldPlayers){
            if(player.getId() == id) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(player);
            }
        }
        List<Goalkeeper> goalkeepers = (List<Goalkeeper>) goalkeeperRepository.findAll();
        for(Goalkeeper goalkeeper: goalkeepers){
            if(goalkeeper.getId() == id){
                ObjectMapper objectMapper = new ObjectMapper();
                return  objectMapper.writeValueAsString(goalkeeper);
            }
        }
        return "Not found";
    }

    @GetMapping("/get-walker") //http://localhost:8080/get-heading?name=Laporte
    public String getPlayer() throws JsonProcessingException {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll() ;
        Map<String, OutfieldPlayer> map = new HashMap<String, OutfieldPlayer>();
        ObjectMapper objectMapper = new ObjectMapper();
        for(OutfieldPlayer player: outfieldPlayers){
            if(player.getLastName().equals("Walker")){
                return objectMapper.writeValueAsString(player);
            }
        }
        return "Not found";
    }

    @GetMapping("/get-outfield-players") //http://localhost:8080/get-heading?name=Laporte
    public String getPlayers(@RequestParam(value = "club", defaultValue = "Manchester City") String club) throws JsonProcessingException {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findOutfieldPlayersClub(club);
//        Map<String, OutfieldPlayer> map = new HashMap<String, OutfieldPlayer>();
        ObjectMapper objectMapper = new ObjectMapper();
//        for(OutfieldPlayer player: outfieldPlayers){
//            map.put(player.getLastName(), player);
//            }
        return objectMapper.writeValueAsString(outfieldPlayers);
    }

    @GetMapping("/get-all-players") //http://localhost:8080/get-heading?name=Laporte
    public String getAllPlayers(@RequestParam(value = "club", defaultValue = "Manchester City") String club) throws JsonProcessingException {
        club = returnFullClubName(club);
        List<Player> goalkeepers = goalkeeperRepository.findAllPlayersClub(club);
        List<Player> outfieldPlayers =  outfieldPlayerRepository.findAllPlayersClub(club);
        goalkeepers.addAll(outfieldPlayers);

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(goalkeepers);
    }

    @GetMapping("/get-positions") //http://localhost:8080/get-heading?name=Laporte
    public String getPositions(@RequestParam(value = "club", defaultValue = "Manchester City") String club) throws JsonProcessingException {
        club = returnFullClubName(club);
        List<Player> team = teamSetup.returnStartingEleven(club);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(team);
//        return new ObjectMapper().writeValueAsString(team);
    }

    @GetMapping("/return-result") //http://localhost:8080/get-heading?name=Laporte
    public JSONObject returnResult(@RequestParam(value = "home-club", defaultValue = "MCFC") String homeClub, @RequestParam(value = "away-club",defaultValue = "Spurs") String awayClub) throws JsonProcessingException {
        String homeClubNameFull = returnFullClubName(homeClub);
        String awayClubNameFull = returnFullClubName(awayClub);
        MatchEngine matchEngine = new MatchEngine(teamSetup, homeClubNameFull, awayClubNameFull);
        JSONObject object = matchEngine.runMatchEngine();
        return object;
//        JSONObject example = new JSONObject();
//        ArrayList<String> homeScorers = new ArrayList<>();
//        ArrayList<String> awayScorers = new ArrayList<>();
//        homeScorers.add("Haaland");
//        homeScorers.add("Grealish");
//        awayScorers.add("Son");
//        awayScorers.add("Son");
//        example.put("score", "2-0");
//        example.put("homeScorers", homeScorers);
//        example.put("awayScorers", awayScorers);
//        return example;
    }

    @GetMapping("/return-result-observer-pattern") //http://localhost:8080/get-heading?name=Laporte
    public JSONObject returnResultObserver(@RequestParam(value = "home-club", defaultValue = "MCFC") String homeClub, @RequestParam(value = "away-club",defaultValue = "Spurs") String awayClub) throws JsonProcessingException {
        String homeClubNameFull = returnFullClubName(homeClub);
        String awayClubNameFull = returnFullClubName(awayClub);
        MatchEngine matchEngine = new MatchEngine(teamSetup, homeClubNameFull, awayClubNameFull); //Subject
        MatchResult matchResult = new MatchResult(matchEngine);

        return matchEngine.runMatchEngine();
    }

    @GetMapping("/processMatch") //http://localhost:8080/get-heading?name=Laporte
    public JSONObject processMatch(@RequestParam(value = "home-club", defaultValue = "MCFC") String homeClub, @RequestParam(value = "away-club",defaultValue = "Spurs") String awayClub) throws JsonProcessingException {
        String homeClubNameFull = returnFullClubName(homeClub);
        String awayClubNameFull = returnFullClubName(awayClub);
        MatchEngine matchEngine = new MatchEngine(teamSetup, homeClubNameFull, awayClubNameFull); //Subject
        ObjectMapper objectMapper = new ObjectMapper();
        List<InGamePlayerStats> homePlayerMatchStats = new ArrayList<>(matchEngine.getHomePlayersMatchStatsMap().values());
        homePlayerMatchStats = matchEngine.sortPlayers((ArrayList<InGamePlayerStats>) homePlayerMatchStats);
        List<InGamePlayerStats> awayPlayerMatchStats = new ArrayList<>(matchEngine.getAwayPlayersMatchStatsMap().values());
        awayPlayerMatchStats = matchEngine.sortPlayers((ArrayList<InGamePlayerStats>) awayPlayerMatchStats);
        homePlayerMatchStats.addAll(awayPlayerMatchStats);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("players", homePlayerMatchStats);
        jsonObject.put("match",matchEngine.updateInGameMatchStats());
        jsonObject.put("pbp","11:44 - BOS - Robert Williams elevates for a shot at the rim");
        return jsonObject;
    }

    @GetMapping("/test-pbp")
    public JSONObject testPBP(){
        JSONObject test = new JSONObject();
        test.put("key","11:44 - BOS - Robert Williams elevates for a shot at the rim");
        return test;
    }



    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    public String returnFullClubName(String abbrev){
            if(abbrev.equals("MCFC") ){
                return "Manchester City";
            }
            else if(abbrev.equals("Spurs")){
                return "Tottenham Hotspur";
            }
            return "Not found";
    }
}

