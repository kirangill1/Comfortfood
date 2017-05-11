package com.example.gursewak.comfortfood;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gursewak on 5/7/2017.
 */

public class Order_review_adapter extends RecyclerView.Adapter<Order_review_cell> {

    JSONArray jarr ;

    Activity a ;

    public Order_review_adapter(JSONArray jarr , Activity a)
    {
        this.jarr = jarr ;
        this.a = a ;

    }
    @Override
    public Order_review_cell onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Order_review_cell(LayoutInflater.from(a).inflate(R.layout.review_order_cell,parent , false));

    }

    @Override
    public void onBindViewHolder(Order_review_cell holder, int position) {

        try {
            JSONObject job = jarr.getJSONObject(position);

            holder.item_name.setText(job.getString("name"));
            holder.item_price.setText(job.getString("price"));
            holder.item_qty.setText(job.getString("qty"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return jarr.length();
    }
}
