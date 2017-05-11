package com.example.gursewak.comfortfood;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gursewak on 5/6/2017.
 */

public class vieworder_adapter extends RecyclerView.Adapter<vieworder_viewholder> {
    JSONArray json;
    Activity activity;

    public vieworder_adapter(JSONArray job, Activity a) {
        json = job;
        activity = a;
    }

    @Override
    public vieworder_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        vieworder_viewholder view = new vieworder_viewholder(LayoutInflater.from(activity).inflate(R.layout.vieworder_cell, parent, false));
        return view;

    }

    @Override
    public void onBindViewHolder(vieworder_viewholder holder, int position) {
        try {
            final JSONObject job = json.getJSONObject(position);


            holder.username_tv.setText(job.getString("username"));
            holder.date_tv.setText(job.getString("Date"));
            holder.time_tv.setText(job.getString("Time"));

            holder.view_items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(activity , Order_review.class);
                    try {
                        i.putExtra("order_items" ,job.getString("order_item") );
                        i.putExtra("from" , "admin");
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


