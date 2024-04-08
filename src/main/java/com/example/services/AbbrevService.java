package com.example.services;

public class AbbrevService { //prob should be a database table someday

    public String returnFullName(String abbrev){
        String fullName = null;
        switch (abbrev) {
            case "MCFC":
                fullName = "Manchester City";
            case "TOT":
                fullName = "Tottenham Hotspur";
        }
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        return fullName;

    }
}
