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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GardenManager extends AppCompatActivity {
    Gardener user;
    TextView featuredP;

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

        featuredP = findViewById(R.id.featuredPlant);


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
        Intent intent = new Intent(this, GardenView.class);
        intent.putExtra("GardensList",user.getGardens());
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

    public void featuredPlantClick(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change Featured Plant");

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                featuredP.setText(input.getText().toString());

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}