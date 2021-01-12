package com.kolaczynski.astroweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Use the {@link SunFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SunFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SunFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SunFragment newInstance(String param1, String param2) {
        SunFragment fragment = new SunFragment();
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
//                update();

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//                update();

        return inflater.inflate(R.layout.fragment_sun, container, false);
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

        AstroCalculator.SunInfo sunInfo = calculator.getSunInfo();
        String sunrise_time = formatTime(sunInfo.getSunrise());
        String sunset_time = formatTime(sunInfo.getSunset());
        String civil_dusk = formatTime(sunInfo.getTwilightMorning());
        String civil_dawn = formatTime(sunInfo.getTwilightEvening());
        String sunrise_azimuth = Integer.toString((int) sunInfo.getAzimuthRise());
        String sunset_azimuth = Integer.toString((int) sunInfo.getAzimuthSet());

        ((TextView) getView().findViewById(R.id.sunrise)).setText(sunrise_time);
        ((TextView) getView().findViewById(R.id.azimuth1)).setText(sunrise_azimuth);
        ((TextView) getView().findViewById(R.id.sunset)).setText(sunset_time);
        ((TextView) getView().findViewById(R.id.azimuth2)).setText(sunset_azimuth);
        ((TextView) getView().findViewById(R.id.dusk)).setText(civil_dusk);
        ((TextView) getView().findViewById(R.id.dawn)).setText(civil_dawn);

        ((TextView) getView().findViewById(R.id.location)).setText(Data.longitude + "  |  " + Data.latitude);

    }


}