package com.example.gardenhand;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import  com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class NotificationCreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification("Reminder", "Your Tomato Plant needs Watering!", "plant",1,10);
                Snackbar.make(view, "You have scheduled a notification", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }
    public void createNotification(String title, String message, String channel, int ID,long secondDelay){

        Intent intent=new Intent(this,NotificationBroadcast.class);
        intent.putExtra(NotificationBroadcast.TITLE,title);
        intent.putExtra(NotificationBroadcast.MESSAGE,message);
        intent.putExtra(NotificationBroadcast.CHANNEL,channel);
        intent.putExtra(NotificationBroadcast.ID,ID);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeNow=System.currentTimeMillis();
        long offset=1000*secondDelay;
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeNow+offset,pendingIntent);
    }
}