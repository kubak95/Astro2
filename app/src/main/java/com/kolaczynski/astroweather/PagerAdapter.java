package com.kolaczynski.astroweather;

import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position){

       if (this.getCount()==3) {
//           if (numOfTabs==3) {

           switch (position) {
               case 0:
                   return new SunFragment();
               case 1:
                   return new MoonFragment();
               case 2:
                   return new SettingsFragment();
               default:
                   return null;
           }
       }
       else if (this.getCount()==2){
//       else if (numOfTabs==2){
           switch (position) {
               case 0:
                   return new SunMoonFragment();
               case 1:
                   return new SettingsFragment();
               default:
                   return null;
           }

       }
       else
           return new TabletFragment();
    }

    @Override
    public int getCount(){
        return numOfTabs;
    }
}
