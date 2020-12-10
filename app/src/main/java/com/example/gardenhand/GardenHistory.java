package com.example.gardenhand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GardenHistory extends AppCompatActivity {

    Gardener user;
    ListView lv;
    ArrayList<String> removedPlants;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_history2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (Gardener) getIntent().getSerializableExtra("Gardener");

        lv = (ListView) findViewById(R.id.list_view);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        removedPlants = new ArrayList<String>();
        removedPlants = user.getGardenHistory();

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                removedPlants );

        lv.setAdapter(arrayAdapter);

    }

    public void onBackPressed(){
        //logout
        Intent intent = new Intent(this, GardenManager.class);
        intent.putExtra("Gardener", user);
        startActivity(intent);
    }
}