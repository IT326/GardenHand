package com.example.gardenhand;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import org.json.JSONObject;

public interface DBCallback {

    void onComplete( boolean bool);
}
