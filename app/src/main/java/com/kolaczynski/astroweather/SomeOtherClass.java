package com.kolaczynski.astroweather;


import java.util.concurrent.ExecutionException;

public class SomeOtherClass {
    //Some url endpoint that you may have
    String myUrl = OpenWeatherAPI.currentWeatherRequestString + OpenWeatherAPI.requestedLocation;
    //String to place our result in
//    String result;
    //Instantiate new instance of our class
    HttpGetRequest getRequest = new HttpGetRequest();
    //Perform the doInBackground method, passing in our url
    String result = getRequest.execute(myUrl).get();

    public SomeOtherClass() throws ExecutionException, InterruptedException {
    }
}