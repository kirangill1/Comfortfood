package com.example.gursewak.comfortfood;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gursewak on 4/20/2017.
 */

public class menu_adapter extends RecyclerView.Adapter<menu_viewholder> {


    JSONArray json ;
    Activity activity;

    public menu_adapter(JSONArray job, Activity a) {
        json = job;
        activity = a;
    }

    @Override
    public menu_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        menu_viewholder view = new menu_viewholder(LayoutInflater.from(activity).inflate(R.layout.menu_cell,parent,false));
        return view;

    }

    @Override
    public void onBindViewHolder(menu_viewholder holder, int position) {
        try {
            JSONObject job = json.getJSONObject(position);


            holder.item_name.setText(job.getString("Item_name"));
            holder.price.setText(job.getString("Price"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

                }

    @Override
    public int getItemCount() {
        return json.length();
    }
}
