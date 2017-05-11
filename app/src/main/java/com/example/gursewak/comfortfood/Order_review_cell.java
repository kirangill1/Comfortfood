package com.example.gursewak.comfortfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by gursewak on 5/7/2017.
 */

public class Order_review_cell extends RecyclerView.ViewHolder {

    TextView item_name , item_qty , item_price ;

    public Order_review_cell(View itemView) {
        super(itemView);

        item_name = (TextView) itemView.findViewById(R.id.item_name_id);
        item_qty = (TextView) itemView.findViewById(R.id.item_qty_id);
        item_price = (TextView) itemView.findViewById(R.id.item_price_id);
    }
}
