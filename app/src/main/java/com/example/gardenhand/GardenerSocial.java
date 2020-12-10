package com.example.gardenhand;

import com.example.gardenhand.Plant;

import java.io.Serializable;
import java.util.ArrayList;

public class GardenerSocial implements Serializable {
    //ArrayList friendList;
    String featuredPlant;
    private ArrayList<String> friendList;
   // Plant featuredPlant;


    public GardenerSocial(){

       // friendList = new ArrayList<String>();
        featuredPlant = "";
       friendList = new ArrayList<String>();
    }

    public boolean updateGarden(Plant newplant){
        //add plant to gardenList
        //gardens.addPlant();
        //make update garden list to save current garden to DB
        //return succ or fail
        return false;
    }

    public void setFeaturedPlant(String p){featuredPlant = p;}
    public String getFeaturedPlant(){return featuredPlant;}

    public void insert(String name) {
        friendList.add(name);
    }

    public void remove(String name) {
        for (int i = 0; i < friendList.size(); i++) {
            if (friendList.get(i).equals(name)) {
                friendList.remove(i);
            }
        }
    }

    public String get(int index) {
        return friendList.get(index);
    }

    public ArrayList<String> getList() {
        return friendList;
    }
}
