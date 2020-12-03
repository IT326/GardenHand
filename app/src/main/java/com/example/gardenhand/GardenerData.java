package com.example.gardenhand;

import java.util.List;

public class GardenerData {
    List<Plant> wishList;
    Garden garden;

    List<Plant> getWishList() {
        return wishList;
    }

    Garden getGarden() {
        return garden;
    }

    void deleteGarden() {
        garden = null;
    }
}
