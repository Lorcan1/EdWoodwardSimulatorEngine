package com.example.model;

import com.example.model.Player;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="outfield_players")
public class OutfieldPlayer extends Player {

    @Column(name="corners")
    private int corners;

    @Column(name="crossing")
    private int crossing;

    @Column(name="dribbling")
    private int dribbling;

    @Column(name="finishing")
    private int finishing;

    @Column(name="marking")
    private int marking;

    @Column(name="tackling")
    private int tackling;

    @Column(name="heading")
    private int heading;

    @Column(name="long-shots")
    private int longShots;

    @Column(name="long-throws")
    private int longThrows;
    public void kick(){
        System.out.println("kick");
    }

}

//package model;
//
//public class OutfieldPlayer extends Player {
//    private double corners;
//    private double crossing;
//    private double dribbling;
//    private double finishing;
//    private double firstTouch;
//    private double freeKickTaking;
//    private double heading;
//    private double longShots;
//    private double longThrows;
//    private double markers;
//    private double passing;
//    private double penaltyTaking;
//
//    public OutfieldPlayer(int id,String firstName, String lastName, String club, String nation, String position, int age, int ovr) {
//        super(id,firstName, lastName, club, nation, position, age, ovr);
//    }
//
//
//    public double getCorners() {
//        return corners;
//    }
//
//    public void setCorners(double corners) {
//        this.corners = corners;
//    }
//
//    public double getCrossing() {
//        return crossing;
//    }
//
//    public void setCrossing(double crossing) {
//        this.crossing = crossing;
//    }
//
//    public double getDribbling() {
//        return dribbling;
//    }
//
//    public void setDribbling(double dribbling) {
//        this.dribbling = dribbling;
//    }
//
//    public double getFinishing() {
//        return finishing;
//    }
//
//    public void setFinishing(double finishing) {
//        this.finishing = finishing;
//    }
//
//    public double getFirstTouch() {
//        return firstTouch;
//    }
//
//    public void setFirstTouch(double firstTouch) {
//        this.firstTouch = firstTouch;
//    }
//
//    public double getFreeKickTaking() {
//        return freeKickTaking;
//    }
//
//    public void setFreeKickTaking(double freeKickTaking) {
//        this.freeKickTaking = freeKickTaking;
//    }
//
//    public double getHeading() {
//        return heading;
//    }
//
//    public void setHeading(double heading) {
//        this.heading = heading;
//    }
//
//    public double getLongShots() {
//        return longShots;
//    }
//
//    public void setLongShots(double longShots) {
//        this.longShots = longShots;
//    }
//
//    public double getLongThrows() {
//        return longThrows;
//    }
//
//    public void setLongThrows(double longThrows) {
//        this.longThrows = longThrows;
//    }
//
//    public double getMarkers() {
//        return markers;
//    }
//
//    public void setMarkers(double markers) {
//        this.markers = markers;
//    }
//
//    public double getPassing() {
//        return passing;
//    }
//
//    public void setPassing(double passing) {
//        this.passing = passing;
//    }
//
//    public double getPenaltyTaking() {
//        return penaltyTaking;
//    }
//
//    public void setPenaltyTaking(double penaltyTaking) {
//        this.penaltyTaking = penaltyTaking;
//    }
//}
