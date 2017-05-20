package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Order_review extends AppCompatActivity {

    RecyclerView order_review_recycle ;

    Button next_btn ;

    TextView price ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review);

        price = (TextView) findViewById(R.id.tot_amount);

        order_review_recycle = (RecyclerView) findViewById(R.id.order_review);

        next_btn = (Button) findViewById(R.id.next_btn);

        if(getIntent().getStringExtra("from").equals("admin"))
        {
            next_btn.setVisibility(View.GONE);
        }

        try {
            JSONArray jarr = new JSONArray(getIntent().getStringExtra("order_items"));

            Order_review_adapter adapter = new Order_review_adapter( jarr, Order_review.this);

            order_review_recycle.setLayoutManager(new LinearLayoutManager(Order_review.this , LinearLayoutManager.VERTICAL , false));

            order_review_recycle.setAdapter(adapter);

            int tot_price = 0;
            for (int i = 0 ; i <jarr.length() ; i ++)
            {
                JSONObject job = jarr.getJSONObject(i);
                int p = Integer.parseInt(job.getString("price"));
                tot_price += p ;
            }

            price.setText(String.valueOf(tot_price));
        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
    public void check_outt(View v)
    {
        Intent i = new Intent(Order_review.this,Payment_mode.class);
        i.putExtra("rest_id",getIntent().getStringExtra("rest_id"));
        startActivity(i);

        finish();
    }
}
