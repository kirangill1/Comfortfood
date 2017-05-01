package com.example.gursewak.comfortfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by gursewak on 4/26/2017.
 */

public class left_over_view_holder extends RecyclerView.ViewHolder {
    ImageView i;
    TextView item_name, price,item_type;

    CheckBox left_food_check ;

    public left_over_view_holder(View itemView) {
        super(itemView);


        i = (ImageView)itemView.findViewById(R.id.menu_image);
        item_name= (TextView)itemView.findViewById(R.id.rest_name);
        price = (TextView)itemView.findViewById(R.id.price);
        item_type = (TextView)itemView.findViewById(R.id.typpe);

        left_food_check = (CheckBox) itemView.findViewById(R.id.left_food_check);

    }

}
