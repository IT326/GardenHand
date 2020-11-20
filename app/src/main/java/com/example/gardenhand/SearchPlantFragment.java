package com.example.gardenhand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class SearchPlantFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searchplant, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_searchplant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantApiCaller apicall = new PlantApiCaller();
                Search searcher = new Search("Carrot",SearchPlantFragment.this.getContext());
               // searcher.findPlant();
            }
        });
    }
}