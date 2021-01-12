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

import static java.lang.Math.abs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoonFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MoonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MoonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MoonFragment newInstance(String param1, String param2) {
        MoonFragment fragment = new MoonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
//                update();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_moon, container, false);
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


        AstroCalculator.MoonInfo moonInfo = calculator.getMoonInfo();
        String moonrise = formatTime(moonInfo.getMoonrise());
        String moonset = formatTime(moonInfo.getMoonset());
        String newmoon = formatDate(moonInfo.getNextNewMoon());
        String fullmoon = formatDate(moonInfo.getNextFullMoon());
        String phase = (int) (moonInfo.getIllumination() * 100) + "%";
        String lunarday = Integer.toString(abs((int) moonInfo.getAge()));

        ((TextView) getView().findViewById(R.id.moonrise)).setText(moonrise);
        ((TextView) getView().findViewById(R.id.moonset)).setText(moonset);
        ((TextView) getView().findViewById(R.id.new_moon)).setText(newmoon);
        ((TextView) getView().findViewById(R.id.full_moon)).setText(fullmoon);
        ((TextView) getView().findViewById(R.id.moon_phase)).setText(phase);
        ((TextView) getView().findViewById(R.id.lunar_day)).setText(lunarday);

        ((TextView) getView().findViewById(R.id.location)).setText(Data.longitude + "  |  "+ Data.latitude);

    }

    public static String formatDate(AstroDateTime date_time) {
        String date = "?";
        try {
            date = String.format("%02d/%02d/%04d", date_time.getDay(), date_time.getMonth(), date_time.getYear());
        } catch (Exception e) {}
        return date;
    }

    public static String formatTime(AstroDateTime date_time) {
        String time = "?";
        try {
            time = String.format("%02d:%02d", date_time.getHour(), date_time.getMinute());
        } catch (Exception e) {}
        return time;
    }

}

