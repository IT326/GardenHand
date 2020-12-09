package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class GardenManager extends AppCompatActivity {
    Gardener user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_manager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String user = (String) getIntent().getSerializableExtra("user");
        String pass = (String) getIntent().getSerializableExtra("pass");

        TextView textView = (TextView) toolbar.findViewById(R.id.usernametext);
        textView.setText("Welcome! "+user+": Garden Manager");




        Gardener gardener = new Gardener(user,pass);
        this.user = gardener;
        System.out.println(user);


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

        //create list of gardens from garden references fromdb
        Intent intent = new Intent(this, GardenView.class);
        intent.putExtra("GardensList",user.getGardens());
        intent.putExtra("Gardener",user);
        startActivity(intent);
    }
}