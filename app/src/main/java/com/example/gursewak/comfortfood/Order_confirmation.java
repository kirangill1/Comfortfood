package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Order_confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        add_order();
    }

    public void add_order()
    {
        SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);



        JSONObject job = new JSONObject();

        try {
            job.put("rest_id" , getIntent().getStringExtra("rest_id"));
            job.put("order_items" , User_viewmenu.cart_array.toString());
            job.put("username" , sp.getString("username", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest json = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/add_order.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("resultkey").equals("done"))
                    {

                        Toast.makeText(Order_confirmation.this , "done" , Toast.LENGTH_SHORT).show();

                        User_viewmenu.inst.finish();

                        
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println(error);

            }
        });

        json.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
        AppController app = new AppController(Order_confirmation.this);

        app.addToRequestQueue(json);
    }
}
