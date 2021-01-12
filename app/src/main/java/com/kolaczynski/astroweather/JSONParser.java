package com.kolaczynski.astroweather;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;


public class JSONParser {

    public void parseJSON(String responseJSON) throws JSONException {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        long date = new Date().getTime();
        String dateAdjusted = formatter.format(date + OpenWeatherAPI.timezone * 1000);
        JSONObject reader = new JSONObject(responseJSON);
        JSONObject coord = reader.getJSONObject("coord");
        JSONObject main = reader.getJSONObject("main");
        JSONObject weather = reader.getJSONObject("weather");
        JSONObject wind = reader.getJSONObject("wind");


        OpenWeatherAPI.locationName = reader.getString("name");
        OpenWeatherAPI.coordLon = Double.parseDouble(coord.getString("lon"));
        OpenWeatherAPI.coordLat = Double.parseDouble(coord.getString("lat"));
        OpenWeatherAPI.time = dateAdjusted;
        OpenWeatherAPI.temperature = Float.parseFloat(main.getString("temp"));
        OpenWeatherAPI.pressure = Float.parseFloat(main.getString("pressure"));
        OpenWeatherAPI.description = weather.getString("description");
        OpenWeatherAPI.icon = weather.getString("icon");


        OpenWeatherAPI.windSpeed = Float.parseFloat(wind.getString("speed"));
        OpenWeatherAPI.windDirection = Float.parseFloat(wind.getString("deg"));
        OpenWeatherAPI.humidity = Float.parseFloat(main.getString("humidity"));
        OpenWeatherAPI.visibility = Float.parseFloat(main.getString("visibility"));


        OpenWeatherAPI.incomingDays = "nic";


    }
}
