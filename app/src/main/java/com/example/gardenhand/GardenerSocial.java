package com.example.gardenhand;

import com.example.gardenhand.Plant;

import java.io.Serializable;
import java.util.ArrayList;

public class GardenerSocial implements Serializable {
    //ArrayList friendList;
    String featuredPlant;


    public GardenerSocial(){

       // friendList = new ArrayList<String>();
        featuredPlant = "";
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
}
