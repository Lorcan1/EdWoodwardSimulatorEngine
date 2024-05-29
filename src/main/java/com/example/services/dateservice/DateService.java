package com.example.services.dateservice;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@Getter
public class DateService {

    Calendar calendar;

    // if button is pressed then it should go to the next day
    // if there is a match it should trigger the screen where there is a match

    @PostConstruct
    public void init() {
        this.setInitialDate();
    }


        public Calendar setInitialDate(){
        calendar = Calendar.getInstance();

        // Set the year to 2024
        calendar.set(Calendar.YEAR, 2024);

        // Set the month to August (7 because months are 0-based)
        calendar.set(Calendar.MONTH, 8);

        // Set the day of the month to the last day of August
        // Assuming August 2024 has 31 days
        calendar.set(Calendar.DAY_OF_MONTH, 6);

        // Optionally, print the date to verify
        System.out.println("Last day of August 2024: " + calendar.getTime());

        return calendar;
    }

    public boolean processForward(){  //probably should be a loop

        calendar.add(Calendar.DATE, 1);

        // Get the new date after adding one day
        Date currentDate = calendar.getTime();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isSaturday = dayOfWeek == Calendar.SATURDAY;


        // Print both dates for comparison
        System.out.println("Current Date: " + currentDate);
        System.out.println("New Date After Adding One Day: " + currentDate);
        System.out.println("Is it a Saturday? " + isSaturday);

        return isSaturday;
    }
}
