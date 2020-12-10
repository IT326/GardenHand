package com.example.gardenhand;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlantListAdapter implements ListAdapter{
    // make new addPlantListAdapter difference in onClick
    ArrayList<Plant> arrayList;
    Garden garden;
    Context context;
    Intent intent;
    public PlantListAdapter(Context context, ArrayList<Plant> arrayList, Intent intent) {
        this.arrayList=arrayList;
        this.context=context;
        this.intent = intent;
        this.garden = (Garden) this.intent.getSerializableExtra("garden");
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
        final Plant plant = arrayList.get(position);
        //set onclick for row some how
        //onclick create search
        //retrieve plantby id
        //get callback
        //move to plantview page on succeess
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.plantlist_row, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to plant view for plant
                    Intent intent = new Intent(PlantListAdapter.this.context, PlantView.class);
                    intent.putExtra("garden", garden);
                    intent.putExtra("plant", plant);
                    context.startActivity(intent);

                }
            });
            TextView tittle = convertView.findViewById(R.id.plantlist_name);
            ImageView imag = convertView.findViewById(R.id.plantlist_image);
            tittle.setText(plant.commonname);

            Picasso.with(context).load(plant.photourl).into(imag);

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
