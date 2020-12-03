package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addGarden extends AppCompatActivity {

    ArrayList<Garden> gardensList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_garden);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gardensList = (ArrayList<Garden>) getIntent().getSerializableExtra("GardensList");

    }

    public void yourGardenClick(View view){
        EditText nametext =  findViewById(R.id.nametext);

        String name = nametext.getText().toString();

        if(name == null || name .equals("")){
            Toast.makeText(this,"no name entered",Toast.LENGTH_SHORT).show();
        }else {

            Garden newGarden = new Garden(name);
            newGarden.setIndex(gardensList.size());
            Intent intent = new Intent(this, GardenView.class);


            gardensList.add(newGarden);
            intent.putExtra("GardensList", gardensList);
            startActivity(intent);
        }
    }
}