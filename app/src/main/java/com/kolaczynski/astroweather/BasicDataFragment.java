package com.kolaczynski.astroweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasicDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasicDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BasicDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasicDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasicDataFragment newInstance(String param1, String param2) {
        BasicDataFragment fragment = new BasicDataFragment();
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
        // return inflater.inflate(R.layout.fragment_settings, container, false);
        View view = inflater.inflate(R.layout.fragment_basic_data, container, false);
//        acceptWeatherButton = view.findViewById(R.id.button_weather_accept);
//        acceptWeatherButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstance) {
        super.onViewCreated(view, savedInstance);
        fillFields();
    }


    public void fillFields() {


        TextView location_view, longitude_weather_value, latitude_weather_value, LocationTime, temperature_value, pressure_value, description_view;
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


        location_view.setText(OpenWeatherAPI.locationName);
        longitude_weather_value.setText(String.valueOf(OpenWeatherAPI.coordLon));
        latitude_weather_value.setText(String.valueOf(OpenWeatherAPI.coordLat));
        LocationTime.setText(OpenWeatherAPI.time);
        temperature_value.setText(String.valueOf(OpenWeatherAPI.temperature));
        pressure_value.setText(String.valueOf(OpenWeatherAPI.pressure));
        description_view.setText(OpenWeatherAPI.description);
    }
}


