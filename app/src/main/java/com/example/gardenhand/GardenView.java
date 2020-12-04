package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GardenView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void galleryButtonClick (View view){
        Intent intent = new Intent(this, GardenGallery.class);
        startActivity(intent);
    }

    public void historyButtonClick (View view){
        Intent intent = new Intent(this, GardenHistory.class);
        startActivity(intent);
    }

    public void wishlistButtonClick (View view){
        Intent intent = new Intent(this, PlantWishList.class);
        startActivity(intent);
    }
}