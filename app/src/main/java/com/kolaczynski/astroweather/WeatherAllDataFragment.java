package com.kolaczynski.astroweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherAllDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherAllDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button acceptWeatherButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherAllDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherAllDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherAllDataFragment newInstance(String param1, String param2) {
        WeatherAllDataFragment fragment = new WeatherAllDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_all_data, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        fillFields();
    }

    public void fillFields() {
        TextView location_view, longitude_weather_value, latitude_weather_value, LocationTime, temperature_value, pressure_value, description_view, wind_speed_value, wind_direction_value, humidity_value, visibility_value;
        ImageView imageView;
        int imagePath = getResources().getIdentifier("i" + OpenWeatherAPI.icon, "drawable", getContext().getPackageName());
        imageView = getView().findViewById(R.id.imageView);
        imageView.setImageResource(imagePath);


        location_view = getView().findViewById(R.id.location_view);
        longitude_weather_value = getView().findViewById(R.id.longitude_weather_value);
        latitude_weather_value = getView().findViewById(R.id.latitude_weather_value);
        LocationTime = getView().findViewById(R.id.LocationTime);
        temperature_value = getView().findViewById(R.id.temperature_value);
        pressure_value = getView().findViewById(R.id.pressure_value);
        description_view = getView().findViewById(R.id.description_view);

        wind_speed_value = getView().findViewById(R.id.wind_speed_value);
        wind_direction_value = getView().findViewById(R.id.wind_direction_value);
        humidity_value = getView().findViewById(R.id.humidity_value);
        visibility_value = getView().findViewById(R.id.visibility_value);

        location_view.setText(OpenWeatherAPI.locationName);

        longitude_weather_value.setText(String.valueOf(OpenWeatherAPI.coordLon));
        latitude_weather_value.setText(String.valueOf(OpenWeatherAPI.coordLat));
        LocationTime.setText(OpenWeatherAPI.time);
        temperature_value.setText(String.valueOf(OpenWeatherAPI.temperature));
        pressure_value.setText(String.valueOf(OpenWeatherAPI.pressure));
        description_view.setText(OpenWeatherAPI.description);

        wind_speed_value.setText(String.valueOf(OpenWeatherAPI.windSpeed));
        wind_direction_value.setText(String.valueOf(OpenWeatherAPI.windDirection));
        humidity_value.setText(String.valueOf(OpenWeatherAPI.humidity));
        visibility_value.setText(String.valueOf(OpenWeatherAPI.visibility));
        TextView day1, day1dayTemp, day1nightTemp, day1weather,
                day2, day2dayTemp, day2nightTemp, day2weather,
                day3, day3dayTemp, day3nightTemp, day3weather,
                day4, day4dayTemp, day4nightTemp, day4weather;


        day1 = getView().findViewById(R.id.day1);
        day2 = getView().findViewById(R.id.day2);
        day3 = getView().findViewById(R.id.day3);
        day4 = getView().findViewById(R.id.day4);

        day1dayTemp = getView().findViewById(R.id.day1_day_temp_val);
        day2dayTemp = getView().findViewById(R.id.day2_day_temp_val);
        day3dayTemp = getView().findViewById(R.id.day3_day_temp_val);
        day4dayTemp = getView().findViewById(R.id.day4_day_temp_val);

        day1nightTemp = getView().findViewById(R.id.day1_night_temp_val);
        day2nightTemp = getView().findViewById(R.id.day2_night_temp_val);
        day3nightTemp = getView().findViewById(R.id.day3_night_temp_val);
        day4nightTemp = getView().findViewById(R.id.day4_night_temp_val);


        day1weather = getView().findViewById(R.id.day1_weather);
        day2weather = getView().findViewById(R.id.day2_weather);
        day3weather = getView().findViewById(R.id.day3_weather);
        day4weather = getView().findViewById(R.id.day4_weather);


        day1.setText(OpenWeatherAPI.day1date);
        day2.setText(OpenWeatherAPI.day2date);
        day3.setText(OpenWeatherAPI.day3date);
        day4.setText(OpenWeatherAPI.day4date);


        day1dayTemp.setText(OpenWeatherAPI.day1dayTemp);
        day2dayTemp.setText(OpenWeatherAPI.day2dayTemp);
        day3dayTemp.setText(OpenWeatherAPI.day3dayTemp);
        day4dayTemp.setText(OpenWeatherAPI.day4dayTemp);


        day1nightTemp.setText(OpenWeatherAPI.day1nightTemp);
        day2nightTemp.setText(OpenWeatherAPI.day2nightTemp);
        day3nightTemp.setText(OpenWeatherAPI.day3nightTemp);
        day4nightTemp.setText(OpenWeatherAPI.day4nightTemp);

        day1weather.setText(OpenWeatherAPI.day1weather);
        day2weather.setText(OpenWeatherAPI.day2weather);
        day3weather.setText(OpenWeatherAPI.day3weather);
        day4weather.setText(OpenWeatherAPI.day4weather);
    }


}

