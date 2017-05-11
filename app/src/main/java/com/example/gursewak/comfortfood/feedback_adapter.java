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
 * Created by gursewak on 5/5/2017.
 */

public class feedback_adapter extends RecyclerView.Adapter<feedback_viewholder> {

    JSONArray json;
    Activity activity;

    public feedback_adapter(JSONArray job, Activity a) {
        json = job;
        activity = a;
    }

    @Override
    public feedback_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        feedback_viewholder view = new feedback_viewholder(LayoutInflater.from(activity).inflate(R.layout.feedback_cell, parent, false));
        return view;

    }

    @Override
    public void onBindViewHolder(feedback_viewholder holder, int position) {

        final JSONObject job;
        try {
            job = json.getJSONObject(position);
            holder.name.setText(job.getString("name"));
            holder.date.setText(job.getString("date"));
            holder.message.setText(job.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }



                    }
    @Override
    public int getItemCount() {
        return json.length();
    }
}





