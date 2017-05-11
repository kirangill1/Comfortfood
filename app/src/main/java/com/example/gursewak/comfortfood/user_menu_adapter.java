package com.example.gursewak.comfortfood;

import android.app.Activity;
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
 * Created by gursewak on 4/27/2017.
 */

public class user_menu_adapter extends RecyclerView.Adapter<user_menu_view_holder> {


    JSONArray json ;
    Activity activity;

    public user_menu_adapter(JSONArray job, Activity a) {
        json = job;
        activity = a;
    }

    @Override
    public user_menu_view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
       user_menu_view_holder view = new user_menu_view_holder(LayoutInflater.from(activity).inflate(R.layout.user_menu_cell,parent,false));
        return view;

    }

    @Override
    public void onBindViewHolder(user_menu_view_holder holder, int position) {
        try {
            final JSONObject job = json.getJSONObject(position);


            holder.item_name.setText(job.getString("Item_name"));
            holder.price.setText(job.getString("Price"));
            holder.item_type.setText(job.getString("item_type"));
            holder.item_qty.setText(job.getString("item_qty"));

            Bitmap bmp = StringToBitMap(job.getString("image"));
            holder.i.setImageBitmap(bmp);

            holder.add_item.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick(View view)
                {
                    try {
                        new qty_dialog(activity , job.getString("Mid") , job.getString("Item_name") , job.getString("Price")    ).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
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
