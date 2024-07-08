package com.example.services.schedulerservice;

import com.example.model.fixtures.Fixtures;
import com.example.repository.fixturesrepository.FixturesRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class SchedulerService {

    @Autowired
    FixturesRepository fixturesRepository;

    HashMap<Date, ArrayList<String>> playersFixtures = new HashMap<>();
    HashMap<Date,ArrayList<ArrayList<String>>> fixtureListDate = new HashMap<>();

    ArrayList<Date> dateList = new ArrayList<>();
    List<String> teams1 = new ArrayList<>(Arrays.asList("MCFC", "TOT", "ARS", "MUFC"));
    String playerTeam = "MCFC";

    @PostConstruct
    public void init(){
        this.createDateList();
        this.setFixtureListDate(createFixtureList1());
        this.saveData(createFixtureList1());
    }
    public void createDateList(){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            dateList.add(sdf.parse("2024-09-07"));
            dateList.add(sdf.parse("2024-09-14"));
            dateList.add(sdf.parse("2024-09-21"));
            dateList.add(sdf.parse("2024-09-28"));
            dateList.add(sdf.parse("2024-10-05"));
            dateList.add(sdf.parse("2024-10-12"));

        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public void setFixtureListDate(ArrayList<ArrayList<ArrayList<String>>> fixtureList) {
        for(int i = 0; i<fixtureList.size();i++){
            fixtureListDate.put(dateList.get(i), fixtureList.get(i));
            }
    }

    public void setPlayersFixtures(HashMap<Date,ArrayList<ArrayList<String>>> fixtureListDate) {
//        for (Map.Entry<String,ArrayList<ArrayList<String>>> entry : myMap.entrySet()) {
//        }

        for (Map.Entry<Date, ArrayList<ArrayList<String>>> entry : fixtureListDate.entrySet()) {
            Date key = entry.getKey();
            ArrayList<ArrayList<String>> listOfLists = entry.getValue();

            // Inner loop to iterate through the ArrayList<ArrayList<String>>
            for (ArrayList<String> innerList : listOfLists) {
                // Further inner loop if needed to iterate through individual ArrayList<String>
                for (String item : innerList) {
                    System.out.println(key + ": " + item);
                    if(item.equals(playerTeam)){
                        playersFixtures.put(key, innerList);
                    }
                }
            }
        }
    }

    public void saveData(ArrayList<ArrayList<ArrayList<String>>> createFixtureList1) {
        for (int i = 0; i < createFixtureList1.size(); i++) {

            ArrayList<ArrayList<String>> fixtureListDate = createFixtureList1.get(i);
            //[[],[],[],[],[],[],[]
            for(ArrayList<String> array : fixtureListDate) {
                Fixtures fixture = new Fixtures();

                fixture.setHomeTeamName(array.get(0));
                fixture.setAwayTeamName(array.get(1));
                fixture.setDate(dateList.get(i));
                fixturesRepository.save(fixture);
            }
        }
    }
    public void getFixtures(Calendar calendar){
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Add dates to the list by parsing the strings

        String dateString = year + "-" + (month + 1) + "-" + day;
        Date date = null;
        try {
             date = sdf.parse(dateString);
        } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception, perhaps log it or notify the user
            }
        if (fixtureListDate.get(date) != null) {
            log.info(date.toString());
            log.info(fixtureListDate.get(date).toString());
            log.info("it worked");
        }
        else{
            log.info("No fixtures on this date: " + date.toString());
        }

        }
    }