package com.example.gursewak.comfortfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gursewak on 5/6/2017.
 */

public class vieworder_viewholder extends RecyclerView.ViewHolder   {
    TextView username_tv, date_tv, time_tv;

    Button view_items ;

    public vieworder_viewholder(View itemView)
    {
        super(itemView);

        username_tv = (TextView)itemView.findViewById(R.id.username_tv);
        date_tv = (TextView)itemView.findViewById(R.id.date_tv);
        time_tv = (TextView)itemView.findViewById(R.id.time_tv);

        view_items = (Button) itemView.findViewById(R.id.view_items);
    }

}
