package com.kolaczynski.astroweather;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdditionalDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdditionalDataFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdditionalDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdditionalDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdditionalDataFragment newInstance(String param1, String param2) {
        AdditionalDataFragment fragment = new AdditionalDataFragment();
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
        View view = inflater.inflate(R.layout.fragment_additional_data, container, false);


        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       fillFields();
    }
    public void fillFields(){

        TextView location_view, wind_speed_value, wind_direction_value, humidity_value, visibility_value;
        location_view = getView().findViewById(R.id.location_view);
        wind_speed_value = getView().findViewById(R.id.wind_speed_value);
        wind_direction_value = getView().findViewById(R.id.wind_direction_value);
        humidity_value = getView().findViewById(R.id.humidity_value);
        visibility_value = getView().findViewById(R.id.visibility_value);

        location_view.setText(OpenWeatherAPI.locationName);
        wind_speed_value.setText(String.valueOf(OpenWeatherAPI.windSpeed));
        wind_direction_value.setText(String.valueOf(OpenWeatherAPI.windDirection));
        humidity_value.setText(String.valueOf(OpenWeatherAPI.humidity));
        visibility_value.setText(String.valueOf(OpenWeatherAPI.visibility));
    }
}