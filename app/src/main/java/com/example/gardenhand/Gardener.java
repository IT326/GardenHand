package com.example.gardenhand;

import java.io.Serializable;
import java.util.ArrayList;

public class Gardener implements Serializable  {
    private GardenerCredentials credentials;
    //private com.example.gardenhand.GardenerSocial social;
    private GardenerData data;
    public int id;

   public Gardener(String username, String password){
       credentials = new GardenerCredentials(username,password);
       data = new GardenerData();
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
   //public String search(String search){}
    //private deleteGarden(){}



}
