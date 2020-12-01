package com.example.gardenhand;

import android.content.Context;

import org.json.*;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.entity.mime.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.*;

import java.util.concurrent.ExecutionException;


public class PlantApiCaller {
    private static final String PLANTBASEAPI = "https://trefle.io/api/v1/plants";
    private static final String PLANTAPITOKEN = "A5x32nhndnuaaNfm5-avmytk5xEn_1X0o72WhP11Cq4";
    public JSONObject response = new JSONObject();
    public Boolean wait = true;


    public void getPlant(String input, Context context, final JSOCallback callback) throws JSONException {
        String url = PLANTBASEAPI+"/"+input+"?token="+PLANTAPITOKEN;
        //input should be id
        //check if number
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringrequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
           @Override
           public void onResponse(String response){
               try {
                   JSONArray resResult = new JSONArray();
                   JSONObject jsonObject = new JSONObject(response);
                   setJSONobj(jsonObject);
                   jsonObject = (JSONObject) jsonObject.get("data");
                   jsonObject = (JSONObject) jsonObject.get("main_species");
                  // jsonObject = jsonObject.get("growth");
                   callback.onSuccess(jsonObject);

               }catch(JSONException err){
                   err.printStackTrace();

               }

           }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("unsuccessful");
            }
        });

        queue.add(stringrequest);
    }

    public void searchPlant(String input, Context context, final VolleyCallback callback) throws JSONException {

        String relurl = "/search";
        RequestQueue queue = Volley.newRequestQueue(context);
        String ghettourl = PLANTBASEAPI+relurl+"?token="+PLANTAPITOKEN+"&q="+input;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ghettourl, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    setJSONobj(jsonObject);
                    JSONArray resResult= (JSONArray) jsonObject.get("data");
                    callback.onSuccess(resResult);
                }catch(JSONException err){
                    err.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("unsuccessful");
            }
        });


        queue.add(stringRequest);



        //System.out.println(params.toString());
        //String teststr = "?token=A5x32nhndnuaaNfm5-avmytk5xEn_1X0o72WhP11Cq4&q=tomato";
       /* PlantApi.get(relurl, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    results[0] = response;
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

        JSONArray resultarray = (JSONArray) results[0].get("data");
        JSONObject resResult = (JSONObject) resultarray.get(0);
        String plantName = resResult.getString("common_name");
        System.out.println(plantName);*/
    }

    public void setJSONobj(JSONObject response){
        this.response = response;
    }


}
