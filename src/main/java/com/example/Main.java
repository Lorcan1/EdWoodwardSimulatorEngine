package com.example;

import com.example.repository.GoalkeeperRepository;
import com.example.team.TeamSetup;
import com.example.matchEngine.MatchEngine;
import com.example.model.OutfieldPlayer;
import com.example.model.Player;
import com.example.repository.OutfieldPlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import com.example.repository.PlayerRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EntityScan(basePackages = {"com.example.model"})
@SpringBootApplication
@EnableJpaRepositories("com.example.repository")
@EnableAutoConfiguration
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }


    @Bean
    public CommandLineRunner demo(OutfieldPlayerRepository repository, GoalkeeperRepository goalkeeper) {
        return (args) -> {
            TeamSetup teamSetup = new TeamSetup(repository, goalkeeper);
            MatchEngine matchEngine = new MatchEngine(teamSetup, "Manchester City","Tottenham Hotspur");
            List<Player> awayTeam = matchEngine.awayTeam;
            List<Player> homeTeam = matchEngine.homeTeam;
            for(Player player:homeTeam){
                System.out.println(player.getPositionsNaturalArray());
                System.out.println(player.getPositionsAccArray());
            }

            ArrayList<String> positions = new ArrayList<>(Arrays.asList("GK","DL","DC","DC","DR","DM","MC","MC","MR","ML","ST"));
            Map<String, Player> positionsMapHome= new HashMap<>();
            positionsMapHome.put("GK",null);
            positionsMapHome.put("DL",null);
            positionsMapHome.put("DCR",null);
            positionsMapHome.put("DCL",null);
            positionsMapHome.put("DR",null);
            positionsMapHome.put("DM",null);
            positionsMapHome.put("MCR",null);
            positionsMapHome.put("MCL",null);
            positionsMapHome.put("MR",null);
            positionsMapHome.put("ML",null);
            positionsMapHome.put("ST",null);
            Map<String, Player> positionsMapAway= new HashMap<>();
            positionsMapAway.put("GK",null);
            positionsMapAway.put("DL",null);
            positionsMapAway.put("DCR",null);
            positionsMapAway.put("DCL",null);
            positionsMapAway.put("DR",null);
            positionsMapAway.put("DM",null);
            positionsMapAway.put("MCR",null);
            positionsMapAway.put("MCL",null);
            positionsMapAway.put("MR",null);
            positionsMapAway.put("ML",null);
            positionsMapAway.put("ST",null);
            positionsMapHome = teamSetup.assignPlayerToPosition(positionsMapHome,homeTeam);
            positionsMapAway = teamSetup.assignPlayerToPosition(positionsMapAway,awayTeam);

            int x= 3;



            // save a few customers

//        }
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            List<Player> players = new ArrayList<Player>();
//            players = (List<Player>) repository.findAll();
//            for (Player player : repository.findAll()) {
//                log.info(player.toString());
//            }
//            log.info("");
//
//            players = repository.findAllPlayersClub("Man City");
//            int d = 10;

//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            List<OutfieldPlayer> players = new ArrayList<OutfieldPlayer>();
//            players = (List<OutfieldPlayer>) repository.findAll();
//            for (Player player : repository.findAll()) {
//                log.info(player.toString());
//            }
//            log.info("");
////
//            players = repository.findAllPlayersClub("Manchester City");
//            int d = 10;
//
//            for(OutfieldPlayer outfieldPlayer :  players){
//                outfieldPlayer.kick();
//            }


//            // fetch an individual customer by ID
//            Customer customer = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLastName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  log.info(bauer.toString());
//            // }
//            log.info("");
        };
    }
}