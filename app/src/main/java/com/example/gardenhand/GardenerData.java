package com.example.gardenhand;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public class GardenerData {
    private ArrayList<Garden> gardens;
    //ArrayList<Plant> wishlist;
    public GardenerData(){
       this.gardens = new ArrayList<Garden>();
    }

    public GardenerData(String dbUserID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("gardeners").document(dbUserID);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("GardenerData Firestore", "DocumentSnapshot data: " + document.getData());

                        List<DocumentReference> gardenRefs = (List<DocumentReference>) document.get("gardens");

                        for(DocumentReference g : gardenRefs) {
                            gardens.add(new Garden(g));
                        }
                    }
                    else {
                        Log.d("GardenerData Firestore", "No such document");
                    }
                }
                else {
                    Log.d("GardenerData Firestore", "get failed with ", task.getException());
                }
            }
        });
    }

    public Garden getGarden(int index){
        return gardens.get(index);
    }

    public ArrayList<Garden> getGardens(){
        return gardens;
    }

    public int addGarden(Garden garden){
        garden.setIndex(gardens.size());
        this.gardens.add(garden);
        return garden.listindex;

    }
    public void updateGarden(int index, Garden garden){
        this.gardens.set(index,garden);
    }
}
