package com.example.gardenhand;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Plant implements Serializable {
    public Integer listIndex;
    String commonname;
    String photourl;
    //String id;
    int id;
    int daystowater;
    Plant seedParent = null;
    private Dictionary<Date,Double> plantHistory;
    private Date lastWater;
    private String dbGardenID;

    public Plant(String name, String photourl, int id){
        this.commonname = name;
        this.photourl = photourl;
        this.id = id;

    }

    public Plant(String name, String photourl, int id, String dbGardenID){
        this.commonname = name;
        this.photourl = photourl;
        this.id = id;

        this.dbGardenID = dbGardenID;

        updateDB();
    }

    public Plant(String dbGardenID, String dbPlantID) {//pull plant from database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("gardens").document(dbGardenID).collection("plants").document(dbPlantID);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Plant Firestore", "DocumentSnapshot data: " + document.getData());

                        id = Integer.parseInt(document.getId());
                        commonname = (String) document.get("commonname");
                        photourl = (String) document.get("photourl");
                        plantHistory = (Dictionary) document.get("plantHistory");
//                        lastWater = (Date) document.get("lastWater");
                    }
                    else {
                        Log.d("Plant Firestore", "No such document");
                    }
                }
                else {
                    Log.d("Plant Firestore", "get failed with ", task.getException());
                }
            }
        });

        this.dbGardenID = dbGardenID;
    }

    public void addToDatabase(String dbGardenID) {
        this.dbGardenID = dbGardenID;

        updateDB();
    }

    private void updateDB() {//update database with new data
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> plantMap = new HashMap<String, Object>();

        plantMap.put("photourl", photourl);
        plantMap.put("commonname", commonname);
        plantMap.put("daystowater", daystowater);
        plantMap.put("plantHistory", plantHistory);
        plantMap.put("lastWater", lastWater);

        db.collection("gardens").document(dbGardenID).collection("plants").document(Integer.toString(id)).set(plantMap);


    }


    //methods
    //showGrowth()
    //compareGrowth()
    //makePlant(JSON object){
    //construct plant from retrieved plant object
    // }
    //updateWater(){
    // use current date to update last water
    // }

    public void setPlantParent(Plant plant){
        seedParent = plant;
    }

    public String toString(){
        String outstr = "";

        outstr+="name: " + this.commonname;
        outstr+="\nphotourl: "+photourl;
        outstr+="\nid" + this.id;

        return outstr;
    }
    public void setPrecipitation(int maxprecip, int minprecip){
        daystowater=(maxprecip-minprecip)/200;
    }
}
