package com.example.gardenhand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.gardenhand.ui.main.GardenListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PlantListView extends AppCompatActivity {
    ArrayList<Plant> plants;
    ArrayList<Garden> gardenList;
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



        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete this garden?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        gardener.deleteGarden(garden.getName());
                        System.out.println(garden.getName());
                        Intent intent = new Intent(PlantListView.this, GardenView.class);
                        intent.putExtra("GardensList", gardener.getGardens());
                        intent.putExtra("Gardener",gardener);
                        startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        FloatingActionButton deleteGardenfab = findViewById(R.id.removeGarden);
        deleteGardenfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
    public void onBackPressed(){
        //logout
        Intent intent = new Intent(this, GardenView.class);
        intent.putExtra("GardensList",gardener.getGardens());
        intent.putExtra("Gardener",gardener);
        startActivity(intent);
    }

}