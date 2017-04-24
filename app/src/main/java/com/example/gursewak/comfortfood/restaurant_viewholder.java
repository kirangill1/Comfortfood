package com.example.gursewak.comfortfood;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by gursewak on 4/18/2017.
 */

public class restaurant_viewholder extends RecyclerView.ViewHolder {

    TextView restaurant_name,address,city,type;
    LinearLayout layout;

    public restaurant_viewholder(View itemView) {
        super(itemView);

        layout =(LinearLayout)itemView.findViewById(R.id.restaurant_name_layout);
        restaurant_name = (TextView)itemView.findViewById(R.id.restaurant_name);
        address = (TextView)itemView.findViewById(R.id.addresss);
        city = (TextView)itemView.findViewById(R.id.cityy);
        type = (TextView)itemView.findViewById(R.id.typee);

    }
}


