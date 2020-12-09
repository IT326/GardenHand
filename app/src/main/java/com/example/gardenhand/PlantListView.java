package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;

import com.example.gardenhand.ui.main.GardenListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PlantListView extends AppCompatActivity {
    ArrayList<Plant> plants;
    Garden garden;
    Gardener gardener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ListView listv = findViewById(R.id.plantList);

        plants = (ArrayList<Plant>) getIntent().getSerializableExtra("plantList");
        garden = (Garden) getIntent().getSerializableExtra("garden");

        gardener = (Gardener) getIntent().getSerializableExtra("gardener");


        if (plants.size() >0) {
           PlantListAdapter plAdapter = new PlantListAdapter(this, plants,this.getIntent());
            //CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
            listv.setAdapter(plAdapter);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlantListView.this, AddPlantActivity.class);
                intent.putExtra("plantList",plants);
                intent.putExtra("garden",garden);
                intent.putExtra("gardener",gardener);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        final ListView listv = findViewById(R.id.plantList);
        plants = (ArrayList<Plant>) getIntent().getSerializableExtra("plantList");
        System.out.println("Called");


  ;
        if (plants.size() >0) {
           PlantListAdapter plAdapter = new PlantListAdapter(this,plants,this.getIntent());
            //CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
            listv.setAdapter(plAdapter);
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlantListView.this, AddPlantActivity.class);
                intent.putExtra("plantList",plants);
                intent.putExtra("garden",garden);
                startActivity(intent);
            }
        });
        FloatingActionButton removefab = findViewById(R.id.removeGarden);
        removefab.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             //gardener.removegardend
                                         }
                                     }

        );
    }
}