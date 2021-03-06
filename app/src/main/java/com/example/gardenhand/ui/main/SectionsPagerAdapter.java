package com.example.gardenhand.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gardenhand.FirstFragment;
import com.example.gardenhand.SearchView;
import com.example.gardenhand.SecondFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {


    private static final String[] TAB_TITLES = new String[]{"Add Plant","Find Plant"};
    private final Context mContext;

    int totalTabs;

    public SectionsPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        mContext = context;
        this.totalTabs = totalTabs;
    }

    public SectionsPagerAdapter(SearchView searchView, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        mContext = searchView.getBaseContext();

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FirstFragment firstFragment = new FirstFragment();
                return firstFragment;
            case 1:
                SecondFragment secondFragment = new SecondFragment();
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