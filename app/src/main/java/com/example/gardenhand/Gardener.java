package com.example.gardenhand;

public class Gardener {
    GardenerCredentials credentials;
    GardenerSocial socials;
    GardenerData gardenerData;

    public Gardener() {
        credentials = new GardenerCredentials();
        socials = new GardenerSocial();
        gardenerData = new GardenerData();
    }

    void deleteGarden() {

    }

    GardenerCredentials getCredentials() {
        return credentials;
    }

    GardenerSocial getSocials() {
        return socials;
    }

    GardenerData getGardenerData() {
        return gardenerData;
    }
}
