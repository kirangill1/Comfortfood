package com.example.gursewak.comfortfood;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by gursewak on 5/5/2017.
 */

public class feedback_viewholder extends RecyclerView.ViewHolder {

    TextView name,date,message;

    public feedback_viewholder(View itemView ){

        super(itemView);

        name = (TextView)itemView.findViewById(R.id.name_tv);
        date = (TextView)itemView.findViewById(R.id.date_tv);
        message = (TextView)itemView.findViewById(R.id.message_tv);


}
}
