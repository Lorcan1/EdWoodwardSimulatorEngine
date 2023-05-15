package com.example.controller;

import com.example.model.Goalkeeper;
import com.example.model.OutfieldPlayer;
import com.example.model.Player;
import com.example.repository.GoalkeeperRepository;
import com.example.repository.OutfieldPlayerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

//@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    // standard constructors

    private final OutfieldPlayerRepository outfieldPlayerRepository;
    private final GoalkeeperRepository goalkeeperRepository;

    public Controller(OutfieldPlayerRepository outfieldPlayerRepository, GoalkeeperRepository goalkeeperRepository) {
        this.outfieldPlayerRepository = outfieldPlayerRepository;
        this.goalkeeperRepository = goalkeeperRepository;
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
        List<Player> goalkeepers = goalkeeperRepository.findAllPlayersClub(club);
        List<Player> outfieldPlayers =  outfieldPlayerRepository.findAllPlayersClub(club);
        goalkeepers.addAll(outfieldPlayers);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(goalkeepers);
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}

