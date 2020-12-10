package com.example.gardenhand;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gardenhand.ui.login.GardenerLogin;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.Date;

public class PlantView extends AppCompatActivity {
    Garden garden;
    Plant plant;
    Gardener gardener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView image;
        TextView name;
        TextView lastwater;
        TextView createdate;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_view);
        createNotificationChannels();
        plant = (Plant) this.getIntent().getSerializableExtra("plant");
        garden = (Garden) this.getIntent().getSerializableExtra("garden");
        gardener =(Gardener) this.getIntent().getSerializableExtra("gardener");
        System.out.println(gardener.getUsername());
        image = findViewById(R.id.imgsrc);
        name = findViewById(R.id.nametext);
        name.setText(plant.commonname);
        lastwater = findViewById(R.id.lastwatertext);
        lastwater.setText(plant.lastWater.toString());
        createdate = findViewById(R.id.dateCreatedtext);
        createdate.setText(plant.createDate.toString());

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
                        gardener.addGardenHistory(plant.commonname);
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

    public void createNotification(View view){
        plant.lastWater = new Date();

        Intent intent=new Intent(this,NotificationBroadcast.class);
        // intent.putExtra("plant",plant);
        //intent.putExtra("garden",garden);
        // intent.putExtra("gardener",gardener);
        intent.putExtra(NotificationBroadcast.TITLE,plant.commonname);
        intent.putExtra(NotificationBroadcast.MESSAGE,"Time to water "+ plant.commonname);
        intent.putExtra(NotificationBroadcast.CHANNEL,"plant");
        intent.putExtra(NotificationBroadcast.ID,plant.id);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeNow=System.currentTimeMillis();
        long offset=1000*plant.daystowater+1;
        System.out.println(plant.daystowater + " " + offset);
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeNow+offset,pendingIntent);
    }
    public void compareButtonClick(View view) {}
    public void familytrackButtonClick(View view) {}

    public void onBackPressed(){
        //logout
        garden.plantList.set(plant.listIndex,plant);
        gardener.updateGarden(garden);
        Intent intent = new Intent(this, PlantListView.class);
        //intent.putExtra("plantList",garden.plantList);
        intent.putExtra("garden",garden);
        intent.putExtra("gardener",gardener);
        startActivity(intent);
    }
    private void createNotificationChannels() {
        // Create the NotificationChannels, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel plantChannel = new NotificationChannel("plant", getString(R.string.plant_channel), NotificationManager.IMPORTANCE_HIGH);
            plantChannel.setDescription(getString(R.string.plant_channel_description));
            plantChannel.setShowBadge(false);
            NotificationChannel socialChannel = new NotificationChannel("social", getString(R.string.social_channel), NotificationManager.IMPORTANCE_DEFAULT);
            socialChannel.setDescription(getString(R.string.plant_channel_description));

            NotificationChannel miscChannel = new NotificationChannel("misc", getString(R.string.misc_channel), NotificationManager.IMPORTANCE_DEFAULT);
            miscChannel.setDescription(getString(R.string.plant_channel_description));
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannels(Arrays.asList(plantChannel,socialChannel,miscChannel));
        }
    }

}