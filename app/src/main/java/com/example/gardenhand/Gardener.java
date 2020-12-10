package com.example.gardenhand;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;

public class Gardener implements Serializable  {
    private GardenerCredentials credentials;
    private GardenerSocial social;
    private GardenerData data;
    public String id;
    private GardenerSocial gs;

   public Gardener(String username, String password){
       credentials = new GardenerCredentials(username,password);
       id = username;
       data = new GardenerData(username);
       social = new GardenerSocial();
       social.insert("");
   }

   public String getUsername(){
       return credentials.getuserID();
   }
   public ArrayList<Garden> getGardens(){
       return  data.getGardens();
   }
   public Garden getGarden(int index){
       return data.getGarden(index);
   }
   public void updateGarden(Garden newG){
       data.updateGarden(newG.listindex,newG);
   }
   public String getId() {
       return credentials.getuserID();
    }
    public GardenerSocial social() {
       return social;
    }

    public void deleteGarden(int listindex){
       data.removeGarden(listindex);
    }
   }
   //public String search(String search){}
   public void deleteGarden(String name) {
       data.deleteGarden(name);

       FirebaseFirestore db = FirebaseFirestore.getInstance();

       DocumentReference docRef = db.collection("gardeners").document(id);
       docRef.update("gardens", FieldValue.arrayRemove(db.collection("gardens").document(name)));
   }

    public void addGarden(Garden newG){
       data.addGarden(newG);
    }

    public void setFeaturedPlant(String p){gs.setFeaturedPlant(p);}
    public String getFeaturedPlant(){return gs.getFeaturedPlant();}
}
