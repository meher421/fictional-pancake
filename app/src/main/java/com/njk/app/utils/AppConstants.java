package com.njk.app.utils;

/**
 * Created by meher on 15/9/16.
 */

public class AppConstants {

    public static String FCM_URL = "https://njk-web-app.appspot.com";
    public static String FCM_API_URL = FCM_URL + "/_ah/api/";
    public static String FCM_MSG_URL = FCM_URL + "/send";

    public static class MarketConstants {
        public static int MARKET_UP = 0;
        public static int MARKET_DOWN = 1;
        public static int MARKET_STEADY = 2;
        public static int MARKET_CLOSED = 3;

    }
}
