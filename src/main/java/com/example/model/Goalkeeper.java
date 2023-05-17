package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="goalkeepers_current")
public class Goalkeeper extends Player {

    @Column(name="aerial-reach")
    private int aerialReach;

    @Column(name="command-of-area")
    private int commandOfArea;

    @Column(name="communication")
    private int communication;

    @Column(name="eccentricity")
    private int eccentricity;

    @Column(name="handling")
    private int handling;

    @Column(name="kicking")
    private int kicking;

    @Column(name="one-on-ones")
    private int oneOnOnes;

    @Column(name="punching-tendency")
    private int punchingTendency;

    @Column(name="reflexes")
    private int reflexes;

    @Column(name="rushing-out-tendency")
    private int rushingOut;

    @Column(name="throwing")
    private int throwing;

    @Transient
    private String playerOrGoalkeeper = "goalkeeper";
}
