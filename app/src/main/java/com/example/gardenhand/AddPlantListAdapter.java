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

import com.example.gardenhand.ui.main.GardenListAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddPlantListAdapter implements ListAdapter{
    // make new addPlantListAdapter difference in onClick
    ArrayList<Plant> arrayList;
    Garden garden;
    Context context;
    Intent intent;
    public AddPlantListAdapter(Context context, ArrayList<Plant> arrayList, Intent intent) {
        this.arrayList=arrayList;
        this.context=context;
        this.intent = intent;
        this.garden = (Garden) this.intent.getSerializableExtra("garden");
        System.out.println(garden.name);
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
        final Garden garden = this.garden;
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
                    System.out.println("touched "+plant.id);
                    Search searcher = new Search(plant.id, AddPlantListAdapter.this.context);
                    searcher.retrievePlant(new JSOCallback() {
                        @Override
                        public void onSuccess(JSONObject resResult) {
                           // System.out.println(resResult.length());
                            Integer min;
                            Integer max;

                            JSONObject jsonObject = resResult;
                            try {
                                jsonObject = (JSONObject) resResult.get("growth");
                               jsonObject = (JSONObject) jsonObject.get("minimum_precipitation");
                               if(!jsonObject.isNull("mm")) {
                                   min = jsonObject.getInt("mm");
                               }else{
                                   min = 0;
                               }
                                jsonObject = (JSONObject) resResult.get("growth");
                                jsonObject = (JSONObject) jsonObject.get("maximum_precipitation");
                                if(!jsonObject.isNull("mm")) {
                                    max = jsonObject.getInt("mm");
                                }else{
                                    max = 400;
                                }
                                    plant.setPrecipitation(max, min);

                                System.out.println("plant clicked water days "+plant.daystowater);
                               // jsonObject = (JSONObject) jsonObject.get("mm");
                            }catch (JSONException e){
                                plant.setPrecipitation(400, 0);
                                e.printStackTrace();
                            }

                            //make plant with id common_name, precipicatation, url
                            //get context then move to plantview and pass it plant and context

                            //context add plant some how
                            //add plant from garden????

                        }
                    });

                    garden.addPlant(plant);

                    Intent intent = new Intent(AddPlantListAdapter.this.context, PlantListView.class);


                    System.out.println(garden.plantList.size());
                    intent.putExtra("plantList", garden.plantList);
                    intent.putExtra("garden", garden);
                   // intent.putExtra("garden", garden);
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
