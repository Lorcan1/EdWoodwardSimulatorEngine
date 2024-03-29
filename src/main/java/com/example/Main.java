package com.example;

import com.example.repository.GoalkeeperRepository;
import com.example.team.Team;
import com.example.team.TeamSetup;
import com.example.matchEngine.engine.MatchEngine;
import com.example.model.player.Player;
import com.example.repository.OutfieldPlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
            Team awayTeam = matchEngine.getAwayTeam();
            Team homeTeam = matchEngine.getHomeTeam();
            for(Player player: homeTeam.getPlayers()){
                System.out.println(player.getPositionsNatural());
                System.out.println(player.getPositionsAcc());
            }
//            Map<String, Player> positionsMapAway= matchEngine.getPositionsMapAway();
//            Map<String, Player> positionsMapHome= matchEngine.getPositionsMapHome();
//
//            positionsMapAway.put("ST",awayTeam.get(awayTeam.size()-1));
//            positionsMapAway.put("ML",awayTeam.get(awayTeam.size()-2));

            Team team= teamSetup.createTeam("Manchester City");
//            matchEngine.runMatchEngine();
//            matchEngine.newRunMatchEngine();


            int x = 5;






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