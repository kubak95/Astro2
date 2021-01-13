package com.kolaczynski.astroweather;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private final int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        if (this.getCount() == 6) {
//           if (numOfTabs==3) {

            switch (position) {
                case 0:
                    return new SunFragment();
                case 1:
                    return new MoonFragment();
                case 2:
                    return new SettingsFragment();
                case 3:
                    return new BasicDataFragment();
                case 4:
                    return new AdditionalDataFragment();
                case 5:
                    return new IncomingDaysDataFragment();
                default:
                    return null;
            }
        } else if (this.getCount() == 4) {
//       else if (numOfTabs==2){
            switch (position) {
                case 0:
                    return new SunMoonFragment();
                case 1:
                    return new SettingsFragment();
                case 2:
                    return new WeatherDataFragment();
                case 3:
                    return new IncomingDaysDataFragment();
                default:
                    return null;
            }

        } else
            switch (position) {
                case 0:
                    return new TabletFragment();
                case 1:
                    return new WeatherAllDataFragment();
                default:
                    return null;
            }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
