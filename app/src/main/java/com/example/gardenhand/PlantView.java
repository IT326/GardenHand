package com.example.gardenhand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void removeButtonClick(View view) {

        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Do you really want to whatever?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(PlantView.this, "Removing", Toast.LENGTH_SHORT).show();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }
    public void compareButtonClick(View view) {}
    public void familytrackButtonClick(View view) {}
}