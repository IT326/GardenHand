package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity {

    private Gardener gardener;
    private EditText nameBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gardener = (Gardener) getIntent().getSerializableExtra("gardener");
    }

    public void addFriendButtonClicked(View view) {
        nameBox = findViewById(R.id.textUsername);
        String nameToAdd = nameBox.getText().toString();
        gardener.social().insert(nameToAdd);

        Intent intent = new Intent(this, FriendsListActivity.class);
        intent.putExtra("gardener", gardener);
        startActivity(intent);
    }
}