package com.example.gardenhand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.gardenhand.ui.login.GardenerLogin;
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
       // String user = (String) getIntent().getSerializableExtra("user");
       // String pass = (String) getIntent().getSerializableExtra("pass");
        user = (Gardener) getIntent().getSerializableExtra("gardener");

        if(user == null) {
            user = new Gardener((String) getIntent().getSerializableExtra("user"), (String) getIntent().getSerializableExtra("pass"));

            System.out.println(user);
            System.out.println(user.getGardens().size());
        }

        TextView textView = (TextView) toolbar.findViewById(R.id.usernametext);
        textView.setText("Welcome! "+user.getUsername()+": Garden Manager");



        featuredP = findViewById(R.id.featuredPlant);
        if(user.getFeaturedPlant().equals(""))
        {
            featuredP = findViewById(R.id.featuredPlant);
        }
        else
            featuredP.setText(user.getFeaturedPlant());

    }
    @Override
    public void onBackPressed(){
        //logout
        Intent intent = new Intent(this, GardenerLogin.class);
        intent.putExtra("user","");
        intent.putExtra("pass","");
        startActivity(intent);
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
        intent.putExtra("gardener", user);
        startActivity(intent);
    }
    public void yourGardenClick(View view){

        //create list of gardens from garden references fromdb
        Intent intent = new Intent(this, GardenView.class);
       // intent.putExtra("GardensList",user.getGardens());
        System.out.println(user.getGardens().size());
        intent.putExtra("gardener",user);
        startActivity(intent);
        //need to pass gardener then get gardens
        //use update/remove gardensdown to plant list view level and probably further
    }

    public void historyButtonClick (View view){
        Intent intent = new Intent(this, GardenHistory.class);
        intent.putExtra("Gardener", user);
        startActivity(intent);
    }

    public void wishlistButtonClick (View view){
        Intent intent = new Intent(this, PlantWishList.class);
        intent.putExtra("gardener", user);
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
                user.setFeaturedPlant(input.getText().toString());

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