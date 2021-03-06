package com.example.gardenhand;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.gardenhand.ui.login.GardenerLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText uName;
    EditText pword;

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
        // Create the NotificationChannels, if on API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel plantChannel = new NotificationChannel("plant", getString(R.string.plant_channel), NotificationManager.IMPORTANCE_HIGH);
            plantChannel.setDescription(getString(R.string.plant_channel_description));
            plantChannel.setShowBadge(false);
            NotificationChannel socialChannel = new NotificationChannel("social", getString(R.string.social_channel), NotificationManager.IMPORTANCE_DEFAULT);
            socialChannel.setDescription(getString(R.string.plant_channel_description));

            NotificationChannel miscChannel = new NotificationChannel("misc", getString(R.string.misc_channel), NotificationManager.IMPORTANCE_DEFAULT);
            miscChannel.setDescription(getString(R.string.plant_channel_description));
            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannels(Arrays.asList(plantChannel, socialChannel, miscChannel));
        }
    }

    public void signupButtonClick(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        Map<String, Object> usermap = new HashMap<>();
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
            Toast.makeText(getApplicationContext(), "User or Pass wrong", Toast.LENGTH_SHORT).show();
        } else {
            usermap.put("user", user);
            usermap.put("pass", pass);

            db.collection("gardeners").document(user).set(usermap)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("user added");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("user failed");
                            e.printStackTrace();
                        }
                    });

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

    public void loginButtonClick(View view) {

        uName = findViewById(R.id.username);
        pword = findViewById(R.id.password);

        String un = uName.getText().toString();
        String pw = pword.getText().toString();

        //move to garden manager activity
        if (validate(un, pw)) {
            Intent intent = new Intent(this, GardenManager.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid username or password");

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }


    private boolean validate(String user, String pass) {
        final boolean[] val = {};
        final String u = user;
        final String p = pass;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("gardeners").document(user);
        Task<DocumentSnapshot> documentSnapshotTask = docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String un = (String) document.get("user");
                        String pw = (String) document.get("pass");
                        if (un.equals(u) && pw.equals(p)) {
                            val[0] = true;
                        } else {
                            val[0] = false;
                        }
                    }

                }
            }
        });
        return val[0];
    }
}
