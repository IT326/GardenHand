package com.example.gardenhand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PlantView extends AppCompatActivity {
    Garden garden;
    Plant plant;
    Gardener gardener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView image;
        TextView name;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
      plant = (Plant) this.getIntent().getSerializableExtra("plant");
        garden = (Garden) this.getIntent().getSerializableExtra("garden");
        gardener =(Gardener) this.getIntent().getSerializableExtra("gardener");
        System.out.println(gardener.getUsername());
       image = findViewById(R.id.imgsrc);
       name = findViewById(R.id.nametext);
       name.setText(plant.commonname);

        Picasso.with(this).load(plant.photourl).into(image);
    }

    public void removeButtonClick(View view) {

        new AlertDialog.Builder(this)
                .setTitle("Remove Plant")
                .setMessage("Do you really want to remove this plant?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(PlantView.this, "Removing", Toast.LENGTH_SHORT).show();
                        garden.removePlant(plant.listIndex);
                        gardener.updateGarden(garden);
                        Intent intent = new Intent(PlantView.this,PlantListView.class);
                        intent.putExtra("plantList",garden.plantList);
                        intent.putExtra("garden",garden);
                        intent.putExtra("gardener",gardener);
                        startActivity(intent);


                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }
    public void compareButtonClick(View view) {}
    public void familytrackButtonClick(View view) {}
}