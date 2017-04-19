package com.example.gursewak.comfortfood;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gursewak on 4/18/2017.
 */

public class restaurantadapter extends RecyclerView.Adapter<restaurant_viewholder> {

    JSONArray json ;
    Activity activity;

    public restaurantadapter(JSONArray job, Activity a) {
        json = job;
        activity = a;
    }

    @Override
    public restaurant_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
    restaurant_viewholder view = new restaurant_viewholder(LayoutInflater.from(activity).inflate(R.layout.restaurantname_cell,parent,false));
        return view;

    }

    @Override
    public void onBindViewHolder(restaurant_viewholder holder, int position) {

        try {
            JSONObject job = json.getJSONObject(position);

            holder.restaurant_name.setText(job.getString("name"));
            holder.city.setText(job.getString("City"));
            holder.address.setText(job.getString("Address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return json.length();
    }
}
