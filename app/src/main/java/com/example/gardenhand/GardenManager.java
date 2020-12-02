package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class GardenManager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_manager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void settingsButtonClick(View view){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void searchButtonClick(View view){
        Intent intent = new Intent(this, SearchView.class);
        startActivity(intent);
    }

    public void addPlantButtonClick(View view){
        Intent intent = new Intent(this, AddPlantActivity.class);
        startActivity(intent);
    }

    public void friendsListClick(View view){
        Intent intent = new Intent(this, FriendsListActivity.class);
        startActivity(intent);
    }
    public void yourGardenClick(View view){
        Intent intent = new Intent(this, GardenView.class);
        startActivity(intent);
    }
}