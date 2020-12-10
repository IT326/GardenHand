package com.example.gardenhand;

import java.io.Serializable;
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

public class GardenerData implements Serializable {
    private ArrayList<Garden> gardens;
    ArrayList<String> wishlist;
    ArrayList<String> gardenHistory;
    public GardenerData(){

        this.gardens = new ArrayList<Garden>();
        this.wishlist = new ArrayList<String>();
        this.gardenHistory = new ArrayList<String>();
    }

    public GardenerData(String dbUserID) {
        gardens = new ArrayList<Garden>();
        wishlist = new ArrayList<String>();
        gardenHistory = new ArrayList<String>();
        getGardensData(dbUserID, new GardenerDataCallback() {
            @Override
            public void onComplete(ArrayList<Garden> g) {



                if(g.size() == 0 || g == null){
                    System.out.println("Empty gardens?: "+g.size());
                    //gardens = g;
                }else {
                    System.out.println("gardens added: "+g.size());
                   gardens = g;
                }
                Log.d("Garden_create", gardens.toString());
            }
        });

        //Log.d("Garden_create2", gardens.toString());
        //System.out.println(gardens.size());
    }

    private void getGardensData(String dbUserID, final GardenerDataCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("gardeners").document(dbUserID);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Garden> gardenArr = new ArrayList<Garden>();
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("GardenerData Firestore", "DocumentSnapshot data: " + document.getData());

                        List<DocumentReference> gardenRefs = (List<DocumentReference>) document.get("gardens");

                        if(gardenRefs != null) {
                            //for(DocumentReference g : gardenRefs) {
                            for(int i=0; i<gardenRefs.size(); i++) {
                                Garden newGarden = new Garden(gardenRefs.get(i), i);

                               gardenArr.add(newGarden);
                                addGarden(newGarden);
                            }
                            Log.d("GardenerData", gardenArr.toString());
                        }

                    }
                    else {
                        Log.d("GardenerData Firestore", "No such document");
                    }
                    callback.onComplete(gardenArr);
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
        int index = gardens.size();
        garden.setIndex(index);
        gardens.add(garden);
        System.out.println(gardens.size());
        return garden.listindex;

    }
    public void removeGarden(int listindex){
        this.gardens.remove(listindex);

        for(int i =0; i< gardens.size();i++){
            gardens.get(i).listindex=i;
        }
    }
    public void updateGarden(int index, Garden garden){
        this.gardens.set(index,garden);
    }

    public void deleteGarden(String name) {
        for(Garden g : gardens) {
            if(g.getName().equals(name)) {
                gardens.remove(g);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("gardens").document(name).delete();
            }
        }
    }

    public void setWishlist(ArrayList<String> wl){wishlist = wl;}
    public ArrayList<String> getWishlist(){return wishlist;};
    public void setHistory(ArrayList<String> gh){gardenHistory = gh;}
    public ArrayList<String> getHistory(){return gardenHistory;};
}
