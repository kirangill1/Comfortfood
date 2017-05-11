package com.example.gursewak.comfortfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.gursewak.comfortfood.R.id.restaurant_name;


/**
 * Created by gursewak on 4/20/2017.
 */

public class menu_viewholder extends RecyclerView.ViewHolder {
    ImageView i;
    TextView item_name, price, item_qty ,item_type;

    public menu_viewholder(View itemView) {
        super(itemView);


        i = (ImageView)itemView.findViewById(R.id.menu_image);
        item_name= (TextView)itemView.findViewById(R.id.rest_name);
        price = (TextView)itemView.findViewById(R.id.price);
        item_qty = (TextView)itemView.findViewById(R.id.qty);
        item_type = (TextView)itemView.findViewById(R.id.typpe);

    }

    }

