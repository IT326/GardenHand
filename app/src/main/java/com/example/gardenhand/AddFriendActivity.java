package com.example.gardenhand;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity {

    private ArrayList<String> friends;
    private String nameToAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        friends = (ArrayList<String>) getIntent().getSerializableExtra("friends");

    }

    public void addFriendButtonClicked(View view) {
        nameToAdd = findViewById(R.id.textUsername).toString();

        if (friends.contains(nameToAdd)) {

        }
        else if (!nameToAdd.isEmpty()) {
            friends.add(nameToAdd);
        }
    }
}