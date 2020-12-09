package com.example.gardenhand;

import java.io.Serializable;
import java.util.ArrayList;

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

    public void setIndex(int index) {
        this.listindex = index;
    }

    public void addPlant(Plant nplant){
        nplant.listIndex = (this.plantList.size());
        this.plantList.add(nplant);
    }

    public void removePlant(int index){
        plantList.remove(index);
        for(int i =0; i< plantList.size();i++){
            plantList.get(i).listIndex=i;
        }
    }
}
