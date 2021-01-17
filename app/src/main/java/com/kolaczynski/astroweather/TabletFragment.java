package com.kolaczynski.astroweather;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabletFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_longi = "1.0";
    private static final String ARG_lati = "1.0";
    private static final String ARG_interval = "15";
    Button acceptButton;
    Switch unitSwitch;
    // TODO: Rename and change types of parameters
    private double mParam1;
    private double mParam2;
    private double mParam3;

    public TabletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabletFragment newInstance(String param1, String param2) {
        TabletFragment fragment = new TabletFragment();
        Bundle args = new Bundle();
        args.putString(ARG_longi, param1);
        args.putString(ARG_lati, param2);
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
            mParam1 = getArguments().getDouble(ARG_longi);
            mParam2 = getArguments().getDouble(ARG_lati);
            mParam3 = getArguments().getDouble(ARG_interval);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_tablet, container, false);
        acceptButton = view.findViewById(R.id.button_accept);
        acceptButton.setOnClickListener(this);

        unitSwitch = view.findViewById(R.id.UnitsSwitch);
        unitSwitch.setOnClickListener(this);

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

        EditText latitude = getView().findViewById(R.id.latitude);
        EditText longitude = getView().findViewById(R.id.longitude);
        EditText UInterval = getView().findViewById(R.id.interval);
        longitude.setText(String.valueOf(Data.longitude));
        latitude.setText(String.valueOf(Data.latitude));
        UInterval.setText(String.valueOf(Data.interval));
        ((TextView) getView().findViewById(R.id.location)).setText(Data.longitude + "  |  " + Data.latitude);

    }

    private void saveJSONtoFile(Context context, String result, String locationString) {
        String filename = locationString + ".json";
        filename = filename.toLowerCase();
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            if (result != null) {
                fos.write(result.getBytes());
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String readJSONfromFile(Context context, String locationString) {
        String filename = locationString + ".json";
        filename = filename.toLowerCase();
        try {
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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


            EditText locationinput = getView().findViewById(R.id.location_weather_value);
            String locationString = locationinput.getText().toString().replace(" ", "%20");
            String myUrl = OpenWeatherAPI.currentWeatherRequestString + locationString;
            HttpGetRequest getRequest = new HttpGetRequest();
            boolean valueRead = false;
            try {
                String result = getRequest.execute(myUrl).get();
                JSONParser.parseJSON(result);
                saveJSONtoFile(getContext(), result, locationString);
                String coordsString = "lat" + OpenWeatherAPI.coordLat + "lon" + OpenWeatherAPI.coordLon;
                String newUrl = OpenWeatherAPI.incomingDaysRequestString + "&lat=" + OpenWeatherAPI.coordLat + "&lon=" + OpenWeatherAPI.coordLon; // + "&units=" + "metric" //OpenWeatherAPI.units;
                HttpGetRequest getNewRequest = new HttpGetRequest();
                String newResult = getNewRequest.execute(newUrl).get();
                JSONParser.parseIJSON(newResult);
                saveJSONtoFile(getContext(), newResult, coordsString);
                valueRead = true;
            } catch (Exception e) {
                e.printStackTrace();
                JSONParser.parseFailed();
                Toast.makeText(getContext(), "Nie udało się pobrać aktualnych danych", Toast.LENGTH_SHORT).show();
            }
            if (valueRead == false) {

                try {
                    String result = readJSONfromFile(getContext(), locationString);
                    JSONParser.parseJSON(result);

                    String newResult = readJSONfromFile(getContext(), "lat" + OpenWeatherAPI.coordLat + "lon" + OpenWeatherAPI.coordLon);
                    JSONParser.parseIJSON(newResult);
                    valueRead = true;

                } catch (Exception e) {
                    e.printStackTrace();
                    JSONParser.parseFailed();
                    JSONParser.parseIFailed();
                    Toast.makeText(getContext(), "Nie udało się pobrać danych z pliku", Toast.LENGTH_SHORT).show();
                }

            }


        }
        if (v == unitSwitch) {

            if (unitSwitch.isChecked()) {
                OpenWeatherAPI.units = "imperial";
            } else {
                OpenWeatherAPI.units = "metric";
            }

        }
    }


}