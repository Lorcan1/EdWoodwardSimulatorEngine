package com.example.model.results;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;

    @Column(name = "home_team_name")
    private String homeTeamName;

    @Column(name="home_score")
    private int homeScore;

    @Column(name = "away_team_name")
    private String awayTeamName;

    @Column(name="away_score")
    private int awayScore;

    @Column(name="date")
    private Date date;

    public Results(String homeTeamName, int homeScore, String awayTeamName, int awayScore, Date date){
        this.homeTeamName = homeTeamName;
        this.homeScore = homeScore;
        this.awayTeamName = awayTeamName;
        this.awayScore = awayScore;
        this.date = date;
    }


}
