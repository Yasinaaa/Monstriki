package ru.android.monstrici.monstrici.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yasina on 07.12.17.
 */

public class DateFunctions {

    public static ArrayList<String> createWeekDates(Calendar today) {
        ArrayList<String> dates = new ArrayList<String>();

        for (int i=2; i<7; i++){
            today.set(Calendar.DAY_OF_WEEK, i);
            Date date = today.getTime();
            dates.add(Resources.DATE_FORMAT.format(date));
        }

        for (int i=1; i>-1; i--){
            today.set(Calendar.DAY_OF_WEEK, i);
            Date date = today.getTime();
            dates.add(Resources.DATE_FORMAT.format(date));
        }
        return dates;
    }

    public static ArrayList<Date> createWeekDates2(Calendar today) {
        ArrayList<Date> dates = new ArrayList<Date>();

        for (int i=2; i<7; i++){
            today.set(Calendar.DAY_OF_WEEK, i);
            Date date = today.getTime();
            dates.add(date);
        }

        for (int i=1; i>-1; i--){
            today.set(Calendar.DAY_OF_WEEK, i);
            Date date = today.getTime();
            dates.add(date);
        }
        return dates;
    }

    public static ArrayList<Date> createWeekDates(Date currentDate){
        Calendar today = Calendar.getInstance();
        today.setTime(currentDate);
        return createWeekDates2(today);
    }
}
