package com.example.services;

import org.springframework.stereotype.Component;

@Component
public class AbbrevService { //prob should be a database table someday

    public String returnFullName(String abbrev){
        String fullName = null;
        switch (abbrev) {
            case "MCFC":
                fullName = "Manchester City";
                break;
            case "TOT":
                fullName = "Tottenham Hotspur";
                break;
        }
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        return fullName;

    }
}
