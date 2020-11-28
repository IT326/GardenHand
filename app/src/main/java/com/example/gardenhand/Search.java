package com.example.gardenhand;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search {
    private static final String PLANTBASEAPI = "https://trefle.io/api/v1/plants";
    private static final String PLANTAPITOKEN = "A5x32nhndnuaaNfm5-avmytk5xEn_1X0o72WhP11Cq4";
    String searchtext;
    Context mContext;
    public JSONObject response = new JSONObject();
    public Search(String input, Context context){
        this.searchtext = input;
        this.mContext =  context;
    }

    public void findPlant( final VolleyCallback callback){

        final PlantApiCaller apicall = new PlantApiCaller();
        try {
            apicall.searchPlant(this.searchtext, this.mContext, new VolleyCallback() {
                @Override
                public void onSuccess(JSONArray result) {

                    callback.onSuccess(result);
                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setJSONobj(JSONObject response){
        this.response = response;
    }
}
