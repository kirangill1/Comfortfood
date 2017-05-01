package com.example.gursewak.comfortfood;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gursewak on 4/26/2017.
 */

public class left_over_food_adapter extends RecyclerView.Adapter<left_over_view_holder> {


    JSONArray json ;
    Activity activity;

    public left_over_food_adapter(JSONArray job, Activity a) {
        json = job;
        activity = a;
    }

    @Override
    public left_over_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
       left_over_view_holder view = new left_over_view_holder(LayoutInflater.from(activity).inflate(R.layout.left_over_food_cell,parent,false));
        return view;

    }

    @Override
    public void onBindViewHolder(left_over_view_holder holder, int position) {
        try {
            final JSONObject job = json.getJSONObject(position);


            holder.item_name.setText(job.getString("Item_name"));
            holder.price.setText(job.getString("Price"));
            holder.item_type.setText(job.getString("item_type"));

            Bitmap bmp = StringToBitMap(job.getString("image"));
            holder.i.setImageBitmap(bmp);

            holder.left_food_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {

                        try {
                            Left_over_menu.update_menu("1" , activity , job.getString("Mid") );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    else {
                        try {
                            Left_over_menu.update_menu("0" , activity , job.getString("Mid") );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
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

