package com.example.gardenhand;

import java.util.Date;
import java.util.Dictionary;

public class Plant {
    String commonname;
    String photourl;
    //String id;
    int id;
    int daystowater;
    //Plant seedParent = null;
    private Dictionary<Date,Double> plantHistory;
    private Date lastWater;

    public Plant(String name, String photourl, int id){
        this.commonname = name;
        this.photourl = photourl;
        this.id = id;

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
