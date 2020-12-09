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
        gardenList = gardener.getGardens();
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
                                             Intent intent = new Intent(PlantListView.this, GardenView.class);
                                             //intent.putExtra("GardensList",gardenList);
                                             new AlertDialog.Builder(PlantListView.this)
                                                     .setTitle("Remove Garden")
                                                     .setMessage("Do you really want to remove this garden?")
                                                     .setIcon(android.R.drawable.ic_dialog_alert)
                                                     .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                                         public void onClick(DialogInterface dialog, int whichButton) {
                                                             Toast.makeText(PlantListView.this, "Removing", Toast.LENGTH_SHORT).show();
                                                             //garden.removePlant(plant.listIndex);
                                                             //garden.updateDB()
                                                             Intent intent = new Intent(PlantListView.this, GardenView.class);
                                                             gardener.deleteGarden(garden.listindex);
                                                             intent.putExtra("GardensList", gardener.getGardens());
                                                             intent.putExtra("Gardener",gardener);
                                                             startActivity(intent);


                                                         }})
                                                     .setNegativeButton(android.R.string.no, null).show();


                                         }
                                     }

        );
    }
    public void onBackPressed(){
        //logout
        Intent intent = new Intent(this, GardenView.class);
        intent.putExtra("GardensList",gardener.getGardens());
        intent.putExtra("Gardener",gardener);
        startActivity(intent);
    }

}