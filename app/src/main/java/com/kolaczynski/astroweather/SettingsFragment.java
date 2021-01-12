package com.kolaczynski.astroweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button acceptButton;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static String formatDate(AstroDateTime date_time) {
        String date = "?";
        try {
            date = String.format("%02d/%02d/%04d", date_time.getDay(), date_time.getMonth(), date_time.getYear());
        } catch (Exception e) {
        }
        return date;
    }

    public static String formatTime(AstroDateTime date_time) {
        String time = "?";
        try {
            time = String.format("%02d:%02d", date_time.getHour(), date_time.getMinute());
        } catch (Exception e) {
        }
        return time;
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        acceptButton = view.findViewById(R.id.button_accept);
        acceptButton.setOnClickListener(this);


        return view;
    }

    @Override


    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        update();
    }

    public void update() {
        Toast.makeText(getContext(), "Data updated!", Toast.LENGTH_SHORT).show();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        AstroDateTime date_time = new AstroDateTime(year, month, day, hour, minute, second, (int) TimeUnit.MILLISECONDS.toHours(TimeZone.getDefault().getOffset(System.currentTimeMillis())), true);

        AstroCalculator.Location location = new AstroCalculator.Location(Data.latitude, Data.longitude);

        AstroCalculator calculator = new AstroCalculator(date_time, location);

        EditText latitude = getView().findViewById(R.id.latitude);
        EditText longitude = getView().findViewById(R.id.longitude);
        EditText UInterval = getView().findViewById(R.id.interval);

        ((TextView) getView().findViewById(R.id.location)).setText(Data.longitude + "  |  " + Data.latitude);
        longitude.setText(String.valueOf(Data.longitude));
        latitude.setText(String.valueOf(Data.latitude));
        UInterval.setText(String.valueOf(Data.interval));
    }

    @Override
    public void onClick(View v) {
        if (v == acceptButton) {
            double tmp;
            EditText latitude = getView().findViewById(R.id.latitude);
            EditText longitude = getView().findViewById(R.id.longitude);
            EditText UInterval = getView().findViewById(R.id.interval);

            tmp = Double.parseDouble(longitude.getText().toString());
            if (tmp < 180 && tmp > -180) {
                Data.longitude = tmp;
            } else {
                Data.longitude = 0.0;
                longitude.setText(String.valueOf(Data.longitude));
                Toast.makeText(getContext(), "Wrong longitude, should be between -180 and 180", Toast.LENGTH_SHORT).show();
            }
            tmp = Double.parseDouble(latitude.getText().toString());
            if (tmp < 90 && tmp > -90) {
                Data.latitude = tmp;
            } else {
                Data.latitude = 0.0;
                latitude.setText(String.valueOf(Data.latitude));
                Toast.makeText(getContext(), "Wrong latitude, should be between -90 and 90", Toast.LENGTH_SHORT).show();

            }
            tmp = Double.parseDouble(UInterval.getText().toString());
            if (tmp > 0 && tmp < 999) {
                Data.interval = tmp;
            } else {
                Data.interval = 1;
                UInterval.setText(String.valueOf(Data.interval));
                Toast.makeText(getContext(), "Wrong interval", Toast.LENGTH_SHORT).show();
            }

            update();

        }

    }

}