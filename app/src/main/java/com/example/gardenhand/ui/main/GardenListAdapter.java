package com.example.gardenhand.ui.main;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.gardenhand.Garden;
import com.example.gardenhand.GardenView;
import com.example.gardenhand.Gardener;
import com.example.gardenhand.PlantListView;
import com.example.gardenhand.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GardenListAdapter implements ListAdapter{

    ArrayList<Garden> arrayList;
    Context context;
    Gardener gardener;
    public GardenListAdapter(Context context, ArrayList<Garden> arrayList, Gardener gardener) {
        this.arrayList=arrayList;
        this.context=context;
        this.gardener=gardener;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Garden garden = arrayList.get(position);
        //set onclick for row some how
        //onclick create search
        //retrieve plantby id
        //get callback
        //move to plantview page on succeess
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.gardenlist_row, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // System.out.println("touched "+garden.id);
                    // go to plantlist view for garden
                    Intent intent = new Intent(GardenListAdapter.this.context, PlantListView.class);



                    intent.putExtra("plantList", garden.plantList);
                    intent.putExtra("garden", garden);
                    intent.putExtra("gardener", gardener);
                    context.startActivity(intent);
                }});
            TextView tittle = convertView.findViewById(R.id.gardenlist_name);
           TextView imag = convertView.findViewById(R.id.gardenlist_index);
            tittle.setText(garden.name);
            imag.setText(Integer.toString(garden.listindex));
           // Picasso.with(context).load(garden.photourl).into(imag);

        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }

}
