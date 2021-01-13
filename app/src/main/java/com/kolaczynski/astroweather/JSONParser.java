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


        OpenWeatherAPI.temperature = new BigDecimal(Float.parseFloat(main.getString("temp")) - Float.parseFloat("273.15"), MathContext.DECIMAL32).round(new MathContext(3)).stripTrailingZeros().toPlainString();
        OpenWeatherAPI.pressure = Float.parseFloat(main.getString("pressure"));
        OpenWeatherAPI.description = weather.getString("description");
        OpenWeatherAPI.icon = weather.getString("icon");


        OpenWeatherAPI.windSpeed = Float.parseFloat(wind.getString("speed"));
        OpenWeatherAPI.windDirection = Float.parseFloat(wind.getString("deg"));
        OpenWeatherAPI.humidity = Float.parseFloat(main.getString("humidity"));
        OpenWeatherAPI.visibility = Float.parseFloat(reader.getString("visibility"));


        OpenWeatherAPI.incomingDays = "nic";


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


        OpenWeatherAPI.incomingDays = "";
    }
}