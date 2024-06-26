package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@EntityScan(basePackages = {"com.example.model"})
@SpringBootApplication
@EnableJpaRepositories("com.example.repository")
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

//    @Bean
//    public CommandLineRunner demo(OutfieldPlayerRepository repository, GoalkeeperRepository goalkeeper) {
//        return (args) -> {
//            TeamSetupLogic teamSetupLogic = new TeamSetupLogic(repository, goalkeeper);
//            Simulate simulate = new Simulate(teamSetupLogic,"Manchester City","Tottenham Hotspur");
//            simulate.simulateMatch();





//            MatchEngine matchEngine = new MatchEngine(teamSetup, "Manchester City","Tottenham Hotspur");
//            Team awayTeam = matchEngine.getAwayTeam();
//            Team homeTeam = matchEngine.getHomeTeam();
//            for(Player player: homeTeam.getPlayers()){
//                System.out.println(player.getPositionsNatural());
//                System.out.println(player.getPositionsAcc());
//            }
////            Map<String, Player> positionsMapAway= matchEngine.getPositionsMapAway();
////            Map<String, Player> positionsMapHome= matchEngine.getPositionsMapHome();
////
////            positionsMapAway.put("ST",awayTeam.get(awayTeam.size()-1));
////            positionsMapAway.put("ML",awayTeam.get(awayTeam.size()-2));
//
//            Team team= teamSetup.createTeam("Manchester City");
////            matchEngine.runMatchEngine();
////            matchEngine.newRunMatchEngine();
//        };
//    }
}