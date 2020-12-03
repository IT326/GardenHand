package com.example.gardenhand;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gardenhand.ui.main.PlantTabsPagerAdapter;

public class AddPlantActivity extends AppCompatActivity {
    TabLayout tabs;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        viewPager = findViewById(R.id.view_pager);
         tabs = (TabLayout) findViewById(R.id.tabs);

        final PlantTabsPagerAdapter plantTabsPagerAdapter = new PlantTabsPagerAdapter(this, getSupportFragmentManager(),tabs.getTabCount());
        viewPager.setAdapter(plantTabsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));

        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab thistab) {
                int position = thistab.getPosition();
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