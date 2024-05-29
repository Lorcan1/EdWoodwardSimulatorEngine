package com.example.services.schedulerservice;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class SchedulerService {

    HashMap<String, List<List>> fixtureList;
    HashMap<String, List<String>> playersFixtures;
    HashMap<String,ArrayList<ArrayList<String>>> fixtureList1 = new HashMap<>();

    List<String> dateList = new ArrayList<>(Arrays.asList("7/8/2024", "14/8/2024", "21/8/2024", "28/8/2024", "temp", "temp1","temp2","temp3","temp4","temp5"));
    List<String> teams1 = new ArrayList<>(Arrays.asList("MCFC", "TOT", "ARS", "MUFC"));

    @PostConstruct
    public void init(){
        this.setFixtureListDate(createFixtureList1());
    }




    public ArrayList<ArrayList<ArrayList<String>>> createFixtureList1() {
        int teams = teams1.size();
        int totalRounds = (teams - 1) * 2;
        int matchesPerRound = teams / 2;
        ArrayList<ArrayList<ArrayList<String>>> fixtures = new ArrayList<>();
        for (int round = 0; round < totalRounds; round++) {
            ArrayList<ArrayList<String>> temp2 = new ArrayList<>();
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                int halfRoundMark = (totalRounds / 2);
                // Last team stays in the same place
                // while the others rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams
                // not 0 to teams - 1 upon display.
                String roundString;
                ArrayList<String> temp = new ArrayList<>();
                if (round < halfRoundMark) {
                    temp.add(teams1.get(home));
                    temp.add(teams1.get(away));
                } else {
                    temp.add(teams1.get(away));
                    temp.add(teams1.get(home));
                }
                temp2.add(temp);
            }
            fixtures.add(temp2);
        }

        return fixtures;
    }

    public void setFixtureListDate(ArrayList<ArrayList<ArrayList<String>>> createFixtureList1) {
        for(int i = 0; i<createFixtureList1.size();i++){
                fixtureList1.put(dateList.get(i), createFixtureList1.get(i));
            }
        String s = "s";
    }

    public void getFixtures(Calendar calendar){
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        String s = day + "/" + month + "/" + year;
        log.info(s);
        log.info(fixtureList1.get(s).toString());

        }
    }

