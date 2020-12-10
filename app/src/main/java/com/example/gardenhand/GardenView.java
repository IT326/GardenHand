package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;

import com.example.gardenhand.ui.login.GardenerLogin;
import com.example.gardenhand.ui.main.GardenListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GardenView extends AppCompatActivity {

    ArrayList<Garden> gardenList;
    Gardener gardener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
       final ListView listv = findViewById(R.id.gardenList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView textView = (TextView) toolbar.findViewById(R.id.usernametext);
        textView.setText("Gardens");
        gardener = (Gardener) getIntent().getSerializableExtra("gardener");
        gardenList= gardener.getGardens();



        if (gardenList != null && gardenList.size() >0) {
            GardenListAdapter plAdapter = new GardenListAdapter(this, gardenList, gardener);
            //CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
            listv.setAdapter(plAdapter);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GardenView.this, addGarden.class);
                intent.putExtra("GardensList",gardener.getGardens());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ListView listv = findViewById(R.id.gardenList);
        gardener = (Gardener) getIntent().getSerializableExtra("gardener");
        gardenList= gardener.getGardens();


        if (gardenList != null && gardenList.size() >0) {
            GardenListAdapter plAdapter = new GardenListAdapter(this, gardenList, gardener);
            //CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
            listv.setAdapter(plAdapter);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GardenView.this, addGarden.class);
                intent.putExtra("GardensList",gardenList);
                intent.putExtra("gardener",gardener);
                startActivity(intent);

            }
        });

    }
    public void onBackPressed(){
        //logout
        Intent intent = new Intent(this, GardenManager.class);
        intent.putExtra("GardensList",gardenList);
        intent.putExtra("gardener",gardener);
        startActivity(intent);
    }
}