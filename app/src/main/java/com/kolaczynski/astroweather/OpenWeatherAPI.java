package com.kolaczynski.astroweather;

import java.math.BigDecimal;

public class OpenWeatherAPI {
    public static String locationName;
    public static double coordLon;
    public static double coordLat;
    public static String time;
    public static String temperature;
    public static float pressure;
    public static String description;
    public static String icon;
    public static int timezone;



    public static float windSpeed;
    public static float windDirection;
    public static float humidity;
    public static float visibility;


    public static String incomingDays;


    public static String requestedLocation;
    public static String currentWeatherRequestString = "https://api.openweathermap.org/data/2.5/weather?lang=pl&appid=b156cf78262a577c51ce2f17ba05237d&q=";
    public static String responseString;

}