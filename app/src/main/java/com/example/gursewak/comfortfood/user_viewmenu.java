package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User_viewmenu extends AppCompatActivity {
    RecyclerView rec;

    public static JSONArray cart_array ;

    public static TextView cart_counter;

    public static  User_viewmenu inst ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewmenu);

        inst = this ;

        cart_counter = (TextView) findViewById(R.id.counter_text);

        cart_array = new JSONArray();

        rec= (RecyclerView)findViewById(R.id.rest_menu_recycler);
        get_data();
    }
    public void check_outt(View v)
    {

        if(User_viewmenu.cart_array.length()==0)
        {

        }
        else {
            Intent i = new Intent(User_viewmenu.this, Order_review.class);
            i.putExtra("rest_id",getIntent().getStringExtra("rest_id"));
            i.putExtra("order_items" , User_viewmenu.cart_array.toString());
            i.putExtra("from" , "user");


            System.out.println(cart_array);
            startActivity(i);


        }
    }

    public void get_data() {




        JSONObject jobr = new JSONObject();
        try {
            jobr.put("rest_id" ,getIntent().getStringExtra("rest_id") );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest job = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/get_user_menu.php", jobr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray job = response.getJSONArray("result");
                   user_menu_adapter adapter = new user_menu_adapter(job,User_viewmenu.this);
                    rec = (RecyclerView) findViewById(R.id.rest_menu_recycler);
                    rec.setLayoutManager(new LinearLayoutManager(User_viewmenu.this, LinearLayoutManager.VERTICAL, false));

                    rec.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        job.setRetryPolicy(new DefaultRetryPolicy(2000, 2, 2));
        AppController app = new AppController(User_viewmenu.this);
        app.addToRequestQueue(job);
    }

}
