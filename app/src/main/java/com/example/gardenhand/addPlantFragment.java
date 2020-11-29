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
        ArrayList<Plant>  plantList = new ArrayList<Plant>();

        plantList.add(new Plant("Garden tomato","https://bs.floristic.org/image/o/400851a79391dbe6f667c66e4bf70299e9921853",182512));
        plantList.add(new Plant("Cayenne pepper","https://bs.floristic.org/image/o/19b8d627527f56c94e2eb1cb34ed2edcb4e2b5c9",115653));
        plantList.add(new Plant("Garden lettuce","https://bs.floristic.org/image/o/7ee395463413655228bdb83cc086dd662d2b633c",147167));
        plantList.add(new Plant("Garden pea","https://bs.floristic.org/image/o/de42bcb86806a72660b5d46d06b2ae40e25610d2",167361));
        plantList.add(new Plant("Garden strawberry","https://bs.floristic.org/image/o/ade4fd72bdaebacc076be2072f515ef8596558fa",1056117));
        plantList.add(new Plant("Garden thyme","https://bs.floristic.org/image/o/8709185087a881e96ffc55ce766b53f7e861cb3b",187946));
        plantList.add(new Plant("Garden asparagus","https://bs.floristic.org/image/o/673e603f2370b1eddb2e5ec28b0818b73e5a594e",108462));
        plantList.add(new Plant("Garden cucumber","https://bs.floristic.org/image/o/d278dfc3db5de3552484da316ab5925b75f1bea5",125316));
        plantList.add(new Plant("Garden rhubarb","https://bs.floristic.org/image/o/caff92d8413a0d92c7111165d8f14118395997a0",174243));
        PlantListAdapter plAdapter = new PlantListAdapter(addPlantFragment.this.getContext(),plantList);
        listv.setAdapter(plAdapter);


    }



}