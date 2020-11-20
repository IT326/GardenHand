package com.example.gardenhand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONException;
import org.json.JSONObject;

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
        return inflater.inflate(R.layout.fragment_first2, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView text = view.findViewById(R.id.textview_addplant);
        view.findViewById(R.id.button_addplant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantApiCaller apicall = new PlantApiCaller();
                Search searcher = new Search("Carrot",addPlantFragment.this.getContext());
                searcher.findPlant(new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {
                            String plantName = result.getString("common_name");
                            text.setText(plantName);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }
        });
    }



}