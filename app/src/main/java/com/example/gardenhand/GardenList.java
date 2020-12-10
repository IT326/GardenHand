package com.example.gardenhand;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gardenhand.ui.login.GardenerLogin;
import com.example.gardenhand.ui.main.GardenListAdapter;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class GardenList extends Fragment {
    ArrayList<Garden> gardens;
    Gardener gardener;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_garden_list, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView listv = view.findViewById(R.id.gardenList);
        gardener = (Gardener) getActivity().getIntent().getSerializableExtra("Gardener");
        gardens = (ArrayList<Garden>) getActivity().getIntent().getSerializableExtra("GardensList");

        GardenListAdapter plAdapter = new GardenListAdapter(GardenList.this.getContext(), gardens,gardener);
        //CustomAdapter customAdapter = new CustomAdapter(this, arrayList);
        listv.setAdapter(plAdapter);


    }
    public void onBackPressed(){
        //logout
        Intent intent = new Intent(getContext(), GardenManager.class);
        //intent.putExtra("user","");
      //  intent.putExtra("pass","");
        startActivity(intent);
    }
}