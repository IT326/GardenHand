package com.example.gardenhand;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.gardenhand.ui.login.GardenerLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardener_login);
        createNotificationChannels();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void loginButtonClick(View view) {
        EditText usernameEditText= findViewById(R.id.username);
        EditText passwordEditText= findViewById(R.id.password);
        Button loginButton;
        //move to garden manager activity
        String user = "";
        String pass = "";
        user = usernameEditText.getText().toString().trim();
        pass = passwordEditText.getText().toString().trim();
        System.out.println("method passed through");
        System.out.println(user);
        System.out.println(pass);
        if (user.equals("") || pass.equals("") || user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "User or Pass wrong",Toast.LENGTH_SHORT).show();
        } else {

            //create gardener for now default
            Gardener gardener = new Gardener(user, pass);
            // or "sign in gardener" get gardener when make a sign in or create button

            //pass Gardener in and open it in GardenManger class
            Intent intent = new Intent(MainActivity.this, GardenManager.class);
            //intent.putExtra("Gardener",gardener);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            //gardener created when enter gardenManager
            startActivity(intent);

        }


    }

}
