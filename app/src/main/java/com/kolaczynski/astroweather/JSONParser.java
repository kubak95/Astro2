package com.kolaczynski.astroweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JSONParser {

    public static void parseJSON(String responseJSON) throws JSONException {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        long date = new Date().getTime();
        JSONObject reader = new JSONObject(responseJSON);
        JSONObject coord = reader.getJSONObject("coord");
        JSONObject main = reader.getJSONObject("main");
        JSONArray weatherArray = reader.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        JSONObject wind = reader.getJSONObject("wind");

        OpenWeatherAPI.timezone = Integer.parseInt(reader.getString("timezone"));

        OpenWeatherAPI.locationName = reader.getString("name");
        OpenWeatherAPI.coordLon = Double.parseDouble(coord.getString("lon"));
        OpenWeatherAPI.coordLat = Double.parseDouble(coord.getString("lat"));
        OpenWeatherAPI.time = formatter.format(date - 3600 * 1000 + OpenWeatherAPI.timezone * 1000);


        OpenWeatherAPI.temperature = kelvinToTemp(main.getString("temp"));
        OpenWeatherAPI.pressure = Float.parseFloat(main.getString("pressure"));
        OpenWeatherAPI.description = weather.getString("description");
        OpenWeatherAPI.icon = weather.getString("icon");


        OpenWeatherAPI.windSpeed = Float.parseFloat(wind.getString("speed"));
        OpenWeatherAPI.windDirection = Float.parseFloat(wind.getString("deg"));
        OpenWeatherAPI.humidity = Float.parseFloat(main.getString("humidity"));
        OpenWeatherAPI.visibility = Float.parseFloat(reader.getString("visibility"));




    }


    public static void parseFailed() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        long date = new Date().getTime();
        String dateAdjusted = formatter.format(date);

        OpenWeatherAPI.locationName = "";
        OpenWeatherAPI.coordLon = 0;
        OpenWeatherAPI.coordLat = 0;
        OpenWeatherAPI.time = dateAdjusted;
        OpenWeatherAPI.temperature = "0";
        OpenWeatherAPI.pressure = 0;
        OpenWeatherAPI.description = "";
        OpenWeatherAPI.icon = "unknown";


        OpenWeatherAPI.windSpeed = 0;
        OpenWeatherAPI.windDirection = 0;
        OpenWeatherAPI.humidity = 0;
        OpenWeatherAPI.visibility = 0;


    }

    public static String epochToDate(String epochTime){

        Date date = new Date(Long.parseLong(epochTime)*1000);
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }


    public static String kelvinToTemp(String kelvin){
        String temp = null;
        if (OpenWeatherAPI.units.equals("metric")){
            temp = new BigDecimal(Float.parseFloat(kelvin) - Float.parseFloat("273.15"), MathContext.DECIMAL32).round(new MathContext(3)).stripTrailingZeros().toPlainString() +"C";
        }
        else {
            temp = new BigDecimal(9.0/5*(Float.parseFloat(kelvin)-273)+32).round(new MathContext(3)).stripTrailingZeros().toPlainString() + "F";
//            String temp = new BigDecimal(Float.parseFloat(kelvin) - Float.parseFloat("273.15"), MathContext.DECIMAL32).round(new MathContext(3)).stripTrailingZeros().toPlainString();
        }
        return temp;
    }

    public static void parseIJSON(String responseJSON) throws JSONException {

        JSONObject reader = new JSONObject(responseJSON);
        JSONArray daysArray = reader.getJSONArray("daily");

        JSONObject day1 = daysArray.getJSONObject(1);
        JSONObject day2 = daysArray.getJSONObject(2);
        JSONObject day3 = daysArray.getJSONObject(3);
        JSONObject day4 = daysArray.getJSONObject(4);

        JSONObject day1temp = day1.getJSONObject("temp");
        JSONObject day2temp = day2.getJSONObject("temp");
        JSONObject day3temp = day3.getJSONObject("temp");
        JSONObject day4temp = day4.getJSONObject("temp");

        JSONArray d1weatherArray = day1.getJSONArray("weather");
        JSONArray d2weatherArray = day2.getJSONArray("weather");
        JSONArray d3weatherArray = day3.getJSONArray("weather");
        JSONArray d4weatherArray = day4.getJSONArray("weather");

        JSONObject d1weather = d1weatherArray.getJSONObject(0);
        JSONObject d2weather = d2weatherArray.getJSONObject(0);
        JSONObject d3weather = d3weatherArray.getJSONObject(0);
        JSONObject d4weather = d4weatherArray.getJSONObject(0);


        OpenWeatherAPI.day1date = epochToDate(day1.getString("dt"));
        OpenWeatherAPI.day2date = epochToDate(day2.getString("dt"));
        OpenWeatherAPI.day3date = epochToDate(day3.getString("dt"));
        OpenWeatherAPI.day4date = epochToDate(day4.getString("dt"));

        OpenWeatherAPI.day1dayTemp = kelvinToTemp(day1temp.getString("day"));
        OpenWeatherAPI.day2dayTemp = kelvinToTemp(day2temp.getString("day"));
        OpenWeatherAPI.day3dayTemp = kelvinToTemp(day3temp.getString("day"));
        OpenWeatherAPI.day4dayTemp = kelvinToTemp(day4temp.getString("day"));

        OpenWeatherAPI.day1nightTemp = kelvinToTemp(day1temp.getString("night"));
        OpenWeatherAPI.day2nightTemp = kelvinToTemp(day2temp.getString("night"));
        OpenWeatherAPI.day3nightTemp = kelvinToTemp(day3temp.getString("night"));
        OpenWeatherAPI.day4nightTemp = kelvinToTemp(day4temp.getString("night"));

        OpenWeatherAPI.day1weather = d1weather.getString("main");
        OpenWeatherAPI.day2weather = d2weather.getString("main");
        OpenWeatherAPI.day3weather = d3weather.getString("main");
        OpenWeatherAPI.day4weather = d4weather.getString("main");


    }

    public static void parseIFailed() {

    }


}