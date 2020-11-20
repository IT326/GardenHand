package com.example.gardenhand.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.gardenhand.addPlantFragment;
import com.example.gardenhand.SearchView;
import com.example.gardenhand.SearchPlantFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class PlantTabsPagerAdapter extends FragmentPagerAdapter {


    private static final String[] TAB_TITLES = new String[]{"Add Plant","Find Plant"};
    private final Context mContext;

    int totalTabs;

    public PlantTabsPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        mContext = context;
        this.totalTabs = totalTabs;
    }

    public PlantTabsPagerAdapter(SearchView searchView, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        mContext = searchView.getBaseContext();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                addPlantFragment firstFragment = new addPlantFragment();
                return firstFragment;
            case 1:
                SearchPlantFragment secondFragment = new SearchPlantFragment();
                return secondFragment;
            default: return  null;
        }

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return totalTabs;
    }
}