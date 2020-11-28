package com.example.gardenhand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class addPlantFragment extends Fragment {

    public void onResume(){
        super.onResume();

    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_plant, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView text = view.findViewById(R.id.textview_addplant);
        final ListView listv = view.findViewById(R.id.plantList);
        final Button buttv =  view.findViewById(R.id.button_addplant);

        view.findViewById(R.id.button_addplant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // PlantApiCaller apicall = new PlantApiCaller();
                Search searcher = new Search("Carrot",addPlantFragment.this.getContext());
                searcher.findPlant(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONArray result) {
                        try {
                            //result is json Array

                            JSONObject tempjso = null;
                            ArrayList<Plant>  plantList = new ArrayList<Plant>();

                            //for each in result add to list view
                            for (int i =0; i< result.length() && i< 10; i++){
                                tempjso = result.getJSONObject(i);
                                plantList.add(new Plant(tempjso.getString("common_name"),tempjso.getString("image_url"),tempjso.getInt("id")));
                                System.out.print(plantList.get(i).toString());

                            }

                           // String plantName = result.getString("common_name");
                            text.setText("list complete"+ plantList.size());
                            PlantListAdapter plAdapter = new PlantListAdapter(addPlantFragment.this.getContext(),plantList);
                            //CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
                            listv.setAdapter(plAdapter);
                            text.setVisibility(View.INVISIBLE);
                            buttv.setVisibility(View.INVISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }



}