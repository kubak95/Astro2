package com.kolaczynski.astroweather;

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


    public static String incomingDaysRequestString = "https://api.openweathermap.org/data/2.5/onecall?exclude=hourly,minutely,current&appid=b156cf78262a577c51ce2f17ba05237d";
    public static String units="metric";

    public static String requestedLocation;
    public static String currentWeatherRequestString = "https://api.openweathermap.org/data/2.5/weather?lang=pl&appid=b156cf78262a577c51ce2f17ba05237d&q=";
    public static String responseString;





    public static String day1date;
    public static String day2date;
    public static String day3date;
    public static String day4date;
    public static String day1dayTemp;
    public static String day2dayTemp;
    public static String day3dayTemp;
    public static String day4dayTemp;
    public static String day1nightTemp;
    public static String day2nightTemp;
    public static String day3nightTemp;
    public static String day4nightTemp;
    public static String day1weather;
    public static String day2weather;
    public static String day3weather;
    public static String day4weather;

}