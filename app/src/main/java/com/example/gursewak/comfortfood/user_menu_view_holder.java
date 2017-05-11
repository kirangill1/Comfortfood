package com.example.gursewak.comfortfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gursewak on 4/27/2017.
 */

public class user_menu_view_holder extends RecyclerView.ViewHolder {
    ImageView i;
    TextView item_name, price,item_type,item_qty;

    Button add_item;



    public user_menu_view_holder(View itemView) {
        super(itemView);


        i = (ImageView)itemView.findViewById(R.id.menu_image);
        item_name= (TextView)itemView.findViewById(R.id.rest_name);
        price = (TextView)itemView.findViewById(R.id.price);
        item_type = (TextView)itemView.findViewById(R.id.typpe);
        item_qty = (TextView)itemView.findViewById(R.id.qty);

        add_item  = (Button)  itemView.findViewById(R.id.add_btn);



    }

}