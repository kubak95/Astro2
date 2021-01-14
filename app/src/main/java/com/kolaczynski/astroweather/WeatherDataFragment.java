package com.kolaczynski.astroweather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeatherDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherDataFragment extends Fragment implements OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button acceptWeatherButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeatherDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherDataFragment newInstance(String param1, String param2) {
        WeatherDataFragment fragment = new WeatherDataFragment();
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
        View view = inflater.inflate(R.layout.fragment_weather_data, container, false);
        acceptWeatherButton = view.findViewById(R.id.button_weather_accept);
        acceptWeatherButton.setOnClickListener(this);
        return view;
    }

@Override
public void onClick(View v) {
    if (v == acceptWeatherButton) {
        EditText locationinput = getView().findViewById(R.id.location_input);
        Log.d("Location", locationinput.getText().toString());
        String myUrl = OpenWeatherAPI.currentWeatherRequestString + locationinput.getText().toString().replace(" ", "%20");
        Log.d("Location", myUrl);
        HttpGetRequest getRequest = new HttpGetRequest();
        try {
            String result = getRequest.execute(myUrl).get();
            JSONParser.parseJSON(result);

        } catch (Exception e) {
            e.printStackTrace();
            JSONParser.parseFailed();
            Toast.makeText(getContext(), "Nie udało się pobrać danych", Toast.LENGTH_SHORT).show();
        }

        TextView longitude_weather_value, latitude_weather_value, LocationTime, temperature_value, pressure_value, description_view, wind_speed_value, wind_direction_value, humidity_value, visibility_value;
        ImageView imageView;
        int imagePath = getResources().getIdentifier("i" + OpenWeatherAPI.icon, "drawable", getContext().getPackageName());
        imageView = getView().findViewById(R.id.imageView);
        imageView.setImageResource(imagePath);


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
    }
}
}