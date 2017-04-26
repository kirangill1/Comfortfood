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
            holder.item_type.setText(job.getString("item_type"));

            Bitmap bmp = StringToBitMap(job.getString("image"));
            holder.i.setImageBitmap(bmp);

        } catch (JSONException e) {
            e.printStackTrace();
        }

                }

    @Override
    public int getItemCount() {
        return json.length();
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
