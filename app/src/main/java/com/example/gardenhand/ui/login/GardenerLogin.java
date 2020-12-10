package com.example.gardenhand.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import  	androidx.appcompat.app.AppCompatActivity;

import android.telecom.Call;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gardenhand.DBCallback;
import com.example.gardenhand.GardenManager;
import com.example.gardenhand.Gardener;
import com.example.gardenhand.MainActivity;
import com.example.gardenhand.R;
import com.example.gardenhand.ui.login.LoginViewModel;
import com.example.gardenhand.ui.login.LoginViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GardenerLogin extends AppCompatActivity {



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
    public void signupButtonClick(View view) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        EditText usernameEditText= findViewById(R.id.username);
        EditText passwordEditText= findViewById(R.id.password);
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
            Toast.makeText(getApplicationContext(), "User or Pass wrong",Toast.LENGTH_SHORT).show();
        } else {
            usermap.put("user",user);
            usermap.put("pass",pass);

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
            Intent intent = new Intent(GardenerLogin.this, GardenManager.class);
            //intent.putExtra("Gardener",gardener);
            intent.putExtra("user", user);
            intent.putExtra("pass", pass);
            //gardener created when enter gardenManager
            startActivity(intent);

        }
    }

    public void loginButtonClick(View view){

        uName = findViewById(R.id.username);
        pword = findViewById(R.id.password);

        final String un = uName.getText().toString();
        final String pw = pword.getText().toString();

        //move to garden manager activity
        final boolean valid;
        validate(un,pw,new DBCallback(){
            @Override
            public void onComplete(boolean result) {
                if(result){
                    Intent intent = new Intent(GardenerLogin.this, GardenManager.class);
                    intent.putExtra("user", un);
                    intent.putExtra("pass", pw);
                    startActivity(intent);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GardenerLogin.this);
                    builder.setTitle("Invalid Username or Password");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });

    }


    private void validate(String user, String pass, final DBCallback callback) {
        final boolean[] val = new boolean[1];
        final String u = user.trim();
        final String p = pass.trim();

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
                        System.out.println(un+" "+ u);
                        System.out.println(pw+" "+ p);

                        if (un.trim().equals(u) && pw.trim().equals(p)) {
                            val[0] = true;

                        } else {
                            val[0] = false;
                        }
                        callback.onComplete(val[0]);
                    }

                }
            }
        });
    }
}
