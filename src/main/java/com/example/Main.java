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

import java.util.ArrayList;
import java.util.List;

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
    public CommandLineRunner demo(OutfieldPlayerRepository repository, GoalkeeperRepository goalkeepr) {
        return (args) -> {
            TeamSetup TeamSetup = new TeamSetup(repository, goalkeepr);
            MatchEngine matchEngine = new MatchEngine(TeamSetup, "Manchester City","Tottenham Hotspur");
            List<Player> awayTeam = matchEngine.awayTeam;
            List<Player> homeTeam = matchEngine.homeTeam;
            for(Player player:homeTeam){
                System.out.println(player.getPositionsNaturalArray().get(0));
                System.out.println(player.getPositionsAccArray());
            }

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