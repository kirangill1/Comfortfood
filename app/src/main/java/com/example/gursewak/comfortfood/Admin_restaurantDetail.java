package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Admin_restaurantDetail extends AppCompatActivity {
    public EditText restaurantname_et;
    public EditText address_et;
    public EditText city_et;
    public EditText foodtype_et;
    public EditText pickup_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_restaurantdetail);

        restaurantname_et = (EditText) findViewById(R.id.restaurantname_);
        address_et = (EditText) findViewById(R.id.address_);
        city_et = (EditText) findViewById(R.id.city_);
        foodtype_et = (EditText) findViewById(R.id.foodtype_);
        pickup_et = (EditText) findViewById(R.id.pickup_);


    }


    public void add_details (View v)
    {

        String rest_name = restaurantname_et.getText().toString();
        String address = address_et.getText().toString();
        String city = city_et.getText().toString();
        String foodtype = foodtype_et.getText().toString();
        String pickup = pickup_et.getText().toString();

        if(restaurantname_et.equals(""))
        {
            Toast.makeText(Admin_restaurantDetail.this, "enter the restaurant name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(address_et.equals(""))
        {
            Toast.makeText(Admin_restaurantDetail.this, "enter the address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(city_et.equals(""))
        {
            Toast.makeText(Admin_restaurantDetail.this, "enter the city", Toast.LENGTH_SHORT).show();
            return;
        }

        if(foodtype_et.equals(""))
        {
            Toast.makeText(Admin_restaurantDetail.this, "enter the food type", Toast.LENGTH_SHORT).show();
            return;
        }

        if(pickup_et.equals(""))
        {
            Toast.makeText(Admin_restaurantDetail.this, "enter the pickup time", Toast.LENGTH_SHORT).show();
            return;
        }



        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);
        final String admin_id = sp.getString("admin_id" , "");

        try {
            job.put("admin_id", admin_id);
            job.put("rest_name" , rest_name);
            job.put("address",address);
            job.put("city",city);
            job.put("food",foodtype);
            job.put("pick",pickup);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/restaurant_detail.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("done"))
                    {

                        Toast.makeText(Admin_restaurantDetail.this , "restaurant detail added succesfully" , Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor sp = getSharedPreferences("admin_info" , MODE_PRIVATE).edit();

                        sp.putString("rest_id",response.getString("rest_id"));
                        sp.commit();



                        finish();
                    }

                    if(response.getString("key").equals("restaurant exist"))
                    {
                        Toast.makeText(Admin_restaurantDetail.this , "restaurant already exist" , Toast.LENGTH_SHORT).show();
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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = new AppController(Admin_restaurantDetail.this);

        app.addToRequestQueue(jobreq);
    }


}




