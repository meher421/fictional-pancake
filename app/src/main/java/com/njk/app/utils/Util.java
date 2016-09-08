package com.njk.app.utils;

import java.text.DateFormat;
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
//        todaysDate = "23-08-2016";
        Logger.i("123456", " Todays date : " + todaysDate);

        return todaysDate;
    }
}