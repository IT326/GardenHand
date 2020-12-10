package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FriendsListActivity extends AppCompatActivity {


    private ArrayList<String> friends;
    private Gardener gardener;
    private ArrayAdapter<String> friendadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gardener = (Gardener) getIntent().getSerializableExtra("gardener");
        ListView listfriend = findViewById(R.id.friendlist);
        friendadapter = new ArrayAdapter<String>(this, R.layout.activity_friends_list, gardener.social().getList());
        listfriend.setAdapter(friendadapter);
    }

    public void addButtonClick(View view) {
        Intent intent = new Intent(this, AddFriendActivity.class);
        intent.putExtra("friends", friends);
        startActivity(intent);
    }


}