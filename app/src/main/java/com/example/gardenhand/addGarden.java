package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

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

        gardensList = (ArrayList<Garden>) getIntent().getSerializableExtra("GardensList");
        gardener = (Gardener) getIntent().getSerializableExtra("Gardener");

    }

    public void yourGardenClick(View view){
        EditText nametext =  findViewById(R.id.nametext);

        String name = nametext.getText().toString();

        if(name == null || name .equals("")){
            Toast.makeText(this,"no name entered",Toast.LENGTH_SHORT).show();
        }else {

            Garden newGarden = new Garden(name, gardener.getId());
            newGarden.setIndex(gardensList.size());
            Intent intent = new Intent(this, GardenView.class);


            gardensList.add(newGarden);
            intent.putExtra("GardensList", gardensList);
            intent.putExtra("Gardener", gardener);
            startActivity(intent);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference gardenDoc = db.collection("gardens").document(name);
            db.collection("gardeners").document(gardener.getId()).update("gardens", FieldValue.arrayUnion(gardenDoc));
        }
    }
}