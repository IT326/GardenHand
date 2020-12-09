package com.example.gardenhand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlantView extends AppCompatActivity {
    Garden garden;
    Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView image;
        TextView name;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
      plant = (Plant) this.getIntent().getSerializableExtra("plant");
        garden = (Garden) this.getIntent().getSerializableExtra("garden");

       image = findViewById(R.id.imgsrc);
       name = findViewById(R.id.nametext);

       name.setText(plant.commonname);

        Picasso.with(this).load(plant.photourl).into(image);
    }

    public void removeButtonClick(View view) {}
    public void compareButtonClick(View view) {}
    public void familytrackButtonClick(View view) {}
}