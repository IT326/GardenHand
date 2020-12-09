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
    Gardener gardener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_garden);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gardener = (Gardener) getIntent().getSerializableExtra("Gardener");
        gardensList = gardener.getGardens();


    }

    public void yourGardenClick(View view){
        EditText nametext =  findViewById(R.id.nametext);

        String name = nametext.getText().toString();

        if(name == null || name .equals("")){
            Toast.makeText(this,"no name entered",Toast.LENGTH_SHORT).show();
        }else {

            Garden newGarden = new Garden(name, gardener.getId());
            newGarden.setIndex(gardensList.size());
            gardener.addGarden(newGarden);
            Intent intent = new Intent(this, GardenView.class);



            intent.putExtra("GardensList", gardensList);
            intent.putExtra("Gardener", gardener);
            startActivity(intent);
        }
    }
}