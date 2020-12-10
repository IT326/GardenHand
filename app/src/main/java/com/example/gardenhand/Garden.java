package com.example.gardenhand;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Garden implements Serializable {
    public Integer listindex;
    public String name;
   // public String doors;
    //ArrayList<Comment> comments;
    //ArrayList<Tuple<Date,String>> changelog
    //// list of date string tuples to record changes
    public ArrayList<Plant> plantList;
    //ArrayList photos?

    public Garden(String name){
        this.name = name;
        //this.doors = outdoorindoor;

        this.plantList = new ArrayList<Plant>();
    }

    public Garden(String name, String dbOwnerID) {
        this.name = name;
        //this.doors = outdoorindoor;

        this.plantList = new ArrayList<Plant>();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ownerDocRef = db.collection("gardeners").document(dbOwnerID);

        Map<String, Object> gardenMap = new HashMap<String, Object>();
        gardenMap.put("owner", ownerDocRef);

        db.collection("gardens").document(name).set(gardenMap);
    }

    public Garden(DocumentReference gardenRef, int listIndex) {//grab garden from database
        //get garden name
        gardenRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Garden Firestore", "DocumentSnapshot data: " + document.getData());

                        name = document.getId();
                        Log.d("Garden Firestore", name);
                    }
                    else {
                        Log.d("Garden Firestore", "No such document");
                    }
                }
                else {
                    Log.d("Garden Firestore", "get failed with ", task.getException());
                }
            }
        });

        //build plant list
        this.plantList = new ArrayList<Plant>();
        gardenRef.collection("plants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        plantList.add(new Plant(plantList.size(),name, document.getId()));
                    }
                    Log.d("Garden Firestore", plantList.toString());
                } else {
                    Log.d("Garden Firestore", "Error getting documents: ", task.getException());
                }
            }
        });

        this.listindex = listIndex;
    }

    public void setIndex(int index) {
        this.listindex = index;
    }

    public String getName() {
        return name;
    }

    public void addPlant(Plant nplant){
        nplant.listIndex = (this.plantList.size());
        this.plantList.add(nplant);
        nplant.addToDatabase(name);
    }

    public void removePlant(int index){

        plantList.remove(index);
        for(int i =0; i< plantList.size();i++){
            plantList.get(i).listIndex=i;
        }
    }

    public JSONObject toJSONObject() {//create json representation of garden
        JSONObject Jgarden = new JSONObject();
        JSONArray JplantArray = new JSONArray();


        for(Plant p : plantList) {
            JplantArray.put(p.toJSONObject());
        }

        try {
            Jgarden.put("name", name);
            Jgarden.put("plants", JplantArray);
        }
        catch(JSONException e) {
            e.printStackTrace();
        }

        return Jgarden;
    }
}
