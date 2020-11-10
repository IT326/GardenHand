package com.example.gardenhand;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.gardenhand.ui.main.SectionsPagerAdapter;

public class AddPlantActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        viewPager = findViewById(R.id.view_pager);
         tabs = (TabLayout) findViewById(R.id.tabs);

        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(),tabs.getTabCount());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab thistab) {
                int position = thistab.getPosition();
                switch(position){
                    case 0:

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab thistab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab thistab) {

            }
        });

    }
}