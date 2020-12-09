package com.example.gardenhand.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import  	androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gardenhand.GardenManager;
import com.example.gardenhand.Gardener;
import com.example.gardenhand.MainActivity;
import com.example.gardenhand.R;
import com.example.gardenhand.ui.login.LoginViewModel;
import com.example.gardenhand.ui.login.LoginViewModelFactory;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class GardenerLogin extends AppCompatActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gardener_login);

        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        //Button loginButton = findViewById(R.id.login);

    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }


    //loginclick in MainActivity.java
    public void loginButtonClick(View view) {
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
            //System.out.println(db.co);
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
}