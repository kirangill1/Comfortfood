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
            final JSONObject job = json.getJSONObject(position);

            holder.restaurant_name.setText(job.getString("name"));
            holder.city.setText(job.getString("City"));
            holder.address.setText(job.getString("Address"));
            holder.type.setText(job.getString("food_type"));
            holder.discount.setText("Discount : "+job.getString("discount")+" %");
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(activity,User_viewmenu.class);
                    try {
                        i.putExtra("rest_id",job.getString("Rid"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    activity.startActivity(i);




                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return json.length();
    }
}
