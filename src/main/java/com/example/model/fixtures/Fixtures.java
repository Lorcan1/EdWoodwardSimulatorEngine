package com.example.model.fixtures;

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
@Table(name="fixtures")
public class Fixtures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;

    @Column(name = "home_team_name")
    private String homeTeamName;

    @Column(name = "away_team_name")
    private String awayTeamName;

    @Column(name="match_date")
    private Date date;
}
