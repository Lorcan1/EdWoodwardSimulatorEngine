package com.example.model.player;

import com.example.model.JsonArray;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name="outfield_players")
//public class Player implements Serializable {
//
//    @Id
//    @Column(name="UniqueID")
//    private int id;
//
//    @Column(name="first_name")
//    private String firstName;
//
//    @Column(name="second_name")
//    private String lastName;
//
//    @Column(name="Club")
//    private String club;
//
//    @Column(name="Country")
//    private String nation;
//
//    @Column(name="Age")
//    private int age;
//
//    @Column(name="Position")
//    private String position;
//
//    @Column(name="Foot")
//    private String foot;
//
//    @Column(name="Height")
//    private int height;
//
//    @Column(name="Weight")
//    private int weight;
//
//    @Column(name="int_caps")
//    private Integer intCaps;
//
//    @Column(name="int_goals")
//    private Integer intGoals;
//
//    @Column(name="sell_value")
//    private String sellValue;
//
//    @Column(name="Wages")
//    private String wages;
//
//    @Column(name="contract_end")
//    private String contractEnd;
//
//    @Column(name="Overall")
//    private int overall;
//
//    @Column(name="Potential")
//    private int potential;
//
//    @Column(name="first-touch")
//    private int firstTouch;
//
//    @Column(name="free-kick-taking")
//    private int freeKickTaking;
//
//    @Column(name="penalty-taking")
//    private int penaltyTaking;
//
//    @Column(name="technique")
//    private int technique;
//
//    @Column(name="passing")
//    private int passing;
//
//    @Column(name="aggression")
//    private int aggression;
//
//    @Column(name="anticipation")
//    private int anticipation;
//
//    @Column(name="bravery")
//    private int bravery;
//
//    @Column(name="composure")
//    private int composure;
//
//    @Column(name="concentration")
//    private int concentration;
//
//    @Column(name="decisions")
//    private int decisions;
//
//    @Column(name="determination")
//    private int determination;
//
//    @Column(name="flair")
//    private int flair;
//
//    @Column(name="leadership")
//    private int leadership;
//
//    @Column(name="off-the-ball")
//    private int offTheBall;
//
//    @Column(name="positioning")
//    private int positioning;
//
//    @Column(name="teamwork")
//    private int teamwork;
//
//    @Column(name="vision")
//    private int vision;
//
//    @Column(name="work-rate")
//    private int workRate;
//
//    @Column(name="acceleration")
//    private int acceleration;
//
//    @Column(name="agility")
//    private int agility;
//
//    @Column(name="balance")
//    private int balance;
//
//    @Column(name="jumping-reach")
//    private int jumpingReach;
//
//    @Column(name="natural-fitness")
//    private int naturalFitness;
//
//    @Column(name="pace")
//    private int pace;
//
//    @Column(name="stamina")
//    private int stamina;
//
//    @Column(name="strength")
//    private int strength;
//}

@Getter
@Setter
@MappedSuperclass
public abstract class Player implements Comparable<Player> {

    @Id
    @Column(name="UniqueID")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="second_name")
    private String lastName;

    @Column(name="Club")
    private String club;

    @Transient
    private String clubAbbrev;

    @Column(name="Country")
    private String nation;

    @Column(name="Age")
    private int age;

    @Column(name="position_natural")
    private String position;

    @Transient
    private JSONArray positionsNatural;

    @Transient
    private JSONArray positionsAcc;


    //    @ElementCollection(targetClass = )
//    @Convert(converter = JSONArrayConverter.class)
    @Column(name="position_accomplished")
//    @ElementCollection
    public String otherPositions;

    public String startingPosition;

    @Column(name="Foot")
    private String foot;

    @Column(name="Height")
    private int height;

    @Column(name="Weight")
    private int weight;

    @Column(name="int_caps")
    private Integer intCaps;

    @Column(name="int_goals")
    private Integer intGoals;

    @Column(name="sell_value")
    private String sellValue;

    @Column(name="Wages")
    private String wages;

    @Column(name="contract_end")
    private String contractEnd;

    @Column(name="Overall")
    private int overall;

    @Column(name="Potential")
    private int potential;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="club_url")
    private String clubUrl;

    @Column(name="country_url")
    private String countryUrl;

    @Column(name="first-touch")
    private int firstTouch;

    @Column(name="free-kick-taking")
    private int freeKickTaking;

    @Column(name="penalty-taking")
    private int penaltyTaking;

    @Column(name="technique")
    private int technique;

    @Column(name="passing")
    private int passing;

    @Column(name="aggression")
    private int aggression;

    @Column(name="anticipation")
    private int anticipation;

    @Column(name="bravery")
    private int bravery;

    @Column(name="composure")
    private int composure;

    @Column(name="concentration")
    private int concentration;

    @Column(name="decisions")
    private int decisions;

    @Column(name="determination")
    private int determination;

    @Column(name="flair")
    private int flair;

    @Column(name="leadership")
    private int leadership;

    @Column(name="off-the-ball")
    private int offTheBall;

    @Column(name="positioning")
    private int positioning;

    @Column(name="teamwork")
    private int teamwork;

    @Column(name="vision")
    private int vision;

    @Column(name="work-rate")
    private int workRate;

    @Column(name="acceleration")
    private int acceleration;

    @Column(name="agility")
    private int agility;

    @Column(name="balance")
    private int balance;

    @Column(name="jumping-reach")
    private int jumpingReach;

    @Column(name="natural-fitness")
    private int naturalFitness;

    @Column(name="pace")
    private int pace;

    @Column(name="stamina")
    private int stamina;

    @Column(name="strength")
    private int strength;

    public int compareTo(Player other) {
        return Integer.compare(this.overall, other.overall);
    }

}