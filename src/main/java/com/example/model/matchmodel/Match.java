package com.example.model.matchmodel;

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
@Table(name="match")
public class Match {

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

    @Column(name="match_date")
    private Date matchDate;

    public Match(String homeTeamName, int homeScore, String awayTeamName, int awayScore, Date matchDate){
        this.homeTeamName = homeTeamName;
        this.homeScore = homeScore;
        this.awayTeamName = awayTeamName;
        this.awayScore = awayScore;
        this.matchDate = matchDate;
    }


}
