package com.example.gardenhand;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class GardenerCredentials {
    String userID;
    String password;

    public GardenerCredentials(String user, String pass){
        this.userID = user;
        this.password = pass;
    }
    public String getuserID(){
        return this.userID;
    }
    public String getpass(){
        return this.password;
    }
    public HashMap<String,String> dataform(){
        HashMap<String,String> outDict = new HashMap<String,String>();

        outDict.put("user",this.userID);
        outDict.put("pass",this.password);

        return  outDict;
    }
}
