package com.example.controller;

import com.example.model.OutfieldPlayer;
import com.example.model.Player;
import com.example.repository.OutfieldPlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController

//@CrossOrigin(origins = "http://localhost:4200")
public class Controller {

    // standard constructors

    private final OutfieldPlayerRepository outfieldPlayerRepository;

    public Controller(OutfieldPlayerRepository outfieldPlayerRepository) {
        this.outfieldPlayerRepository = outfieldPlayerRepository;
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
    public String getHeading(@RequestParam(value = "name", defaultValue = "Haaland") String name) {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll();
        for(OutfieldPlayer player: outfieldPlayers){
            if(player.getLastName().equals(name)){
                return player.getHeading();
            }
        }
        return "Not found";
    }

    @GetMapping("/get-player") //http://localhost:8080/get-heading?name=Laporte
    public String getPlayer(@RequestParam(value = "name", defaultValue = "Haaland") String name) {
        List<OutfieldPlayer> outfieldPlayers =  (List<OutfieldPlayer>) outfieldPlayerRepository.findAll();
        for(OutfieldPlayer player: outfieldPlayers){
            if(player.getLastName().equals(name)){

                return player.getHeading();
            }
        }
        return "Not found";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}

