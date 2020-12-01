package com.example.gardenhand;

import android.content.Context;
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

    ArrayList<Plant> arrayList;
    Context context;
    public PlantListAdapter(Context context, ArrayList<Plant> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
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
                    System.out.println("touched "+plant.id);
                    Search searcher = new Search(plant.id, PlantListAdapter.this.context);
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
