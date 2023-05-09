package com.example.pitch;

import java.util.ArrayList;

public class Pitch {

   ArrayList<ArrayList<Integer>> pitchArray = new ArrayList<ArrayList<Integer>>();

    public void setupArray() {
        ArrayList<Integer> rowOne = new ArrayList<Integer>();
        ArrayList<Integer> rowTwo = new ArrayList<Integer>();
        ArrayList<Integer> rowThree = new ArrayList<Integer>();
        ArrayList<Integer> rowFour = new ArrayList<Integer>();
        ArrayList<Integer> rowFive = new ArrayList<Integer>();
        ArrayList<Integer> rowSix = new ArrayList<Integer>();
        pitchArray.add(rowOne);
        pitchArray.add(rowTwo);
        pitchArray.add(rowThree);
        pitchArray.add(rowFour);
        pitchArray.add(rowFive);
        pitchArray.add(rowSix);

    }
    /*
    6x4 grid styled after the football manager tactics screen
     */
}
