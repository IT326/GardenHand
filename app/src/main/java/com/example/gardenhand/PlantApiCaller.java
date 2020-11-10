package com.example.gardenhand;

import org.json.*;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.entity.mime.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.*;


public class PlantApiCaller {
    public void searchPlant() throws JSONException{
        //input
        String input = "tomato";
        String relurl = "/search";
        RequestParams params = new RequestParams();
        params.put("q",input);
        //System.out.println(params.toString());
        //String teststr = "?token=A5x32nhndnuaaNfm5-avmytk5xEn_1X0o72WhP11Cq4&q=tomato";
        PlantApi.get(relurl, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    JSONArray resarray = (JSONArray) response.get("data");
                    JSONObject resResult = (JSONObject) resarray.get(0);
                    String plantName = resResult.getString("common_name");
                    System.out.println(plantName);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                System.out.println("got json object");


            }



        });


    }
}
