package com.njk.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by meher on 23/8/16.
 */

public class Util {


    public static String getTodayDate() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        String todaysDate = dateFormat.format(date);
        todaysDate = "23-08-2016";
        Logger.i("123456", " Todays date : " + todaysDate);

        return todaysDate;
    }


    public static String getTodayDateInMills() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(getTodayDate());
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        long timeMills = 0;
//        String todaysDate = dateFormat.format(date);
        if (date != null)
            timeMills = date.getTime();
        Logger.i("123456", " Todays date : " + timeMills);

        return ""+timeMills;
    }
}
