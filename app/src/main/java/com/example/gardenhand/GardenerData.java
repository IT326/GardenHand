package com.example.gardenhand;

import java.io.Serializable;
import java.util.ArrayList;

public class GardenerData implements Serializable {
    private ArrayList<Garden> gardens;
    //ArrayList<Plant> wishlist;
    public GardenerData(){
       this.gardens = new ArrayList<Garden>();
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
