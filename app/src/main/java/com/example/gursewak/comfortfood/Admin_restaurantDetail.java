package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    public EditText discount_et;
    public Button add , update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_restaurantdetail);

        restaurantname_et = (EditText) findViewById(R.id.restaurantname_);
        address_et = (EditText) findViewById(R.id.address_);
        city_et = (EditText) findViewById(R.id.city_);
        foodtype_et = (EditText) findViewById(R.id.foodtype_);
        discount_et = (EditText)findViewById(R.id.discount);

        add = (Button) findViewById(R.id.add_btn);
        update = (Button) findViewById(R.id.update_btn);

        get_details();

    }


    public void add_details (View v)
    {

        String rest_name = restaurantname_et.getText().toString();
        String address = address_et.getText().toString();
        String city = city_et.getText().toString();
        String foodtype = foodtype_et.getText().toString();
        String discount = discount_et.getText().toString();


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
            job.put("discount",discount);


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

    public void get_details ()
    {

        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);
        final String admin_id = sp.getString("admin_id" , "");

        try {
            job.put("admin_id", admin_id);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/get_restaurant_details.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("yes"))
                    {

                        JSONObject job = response.getJSONObject("details");

                        restaurantname_et.setText(job.getString("name"));

                        restaurantname_et.setEnabled(true);

                        address_et.setText(job.getString("Address"));

                        address_et.setEnabled(true);

                        city_et.setText(job.getString("City"));

                        city_et.setEnabled(true);

                        foodtype_et.setText(job.getString("food_type"));

                        foodtype_et.setEnabled(true);

                        discount_et.setText(job.getString("discount"));

                        discount_et.setEnabled(true);

                        add.setVisibility(View.GONE);
                        update.setVisibility(View.VISIBLE);


                    }

                    if(response.getString("key").equals("no"))
                    {
                        Toast.makeText(Admin_restaurantDetail.this , "add restaurant details" , Toast.LENGTH_SHORT).show();
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

    public void update_details (View v)
    {


        String rest_name = restaurantname_et.getText().toString();
        String address = address_et.getText().toString();
        String city = city_et.getText().toString();
        String foodtype = foodtype_et.getText().toString();
        String discount = discount_et.getText().toString();


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
            job.put("discount",discount);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/update_restaurant_detail.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("done"))
                    {

                        Toast.makeText(Admin_restaurantDetail.this , "restaurant detail updated succesfully" , Toast.LENGTH_SHORT).show();


                        finish();
                    }

                    if(response.getString("key").equals("restaurant exist"))
                    {
                        Toast.makeText(Admin_restaurantDetail.this , "error" , Toast.LENGTH_SHORT).show();
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




