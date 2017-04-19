package com.example.gursewak.comfortfood;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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


        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);
        final String username = sp.getString("username" , "");

        try {
            job.put("username_key", username);
            job.put("rest_name" , rest_name);
            job.put("address",address);
            job.put("city",city);
            job.put("food",foodtype);
            job.put("pick",pickup);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/add_restaurant.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr =  response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) jarr.get(0);

                    restaurantname_et.setText(job_response.getString("restaurant name"));
                    address_et.setText( job_response.getString("address") );
                    city_et.setText( job_response.getString("city"));
                    foodtype_et.setText( job_response.getString("food type"));
                    pickup_et.setText(job_response.getString("pick up"));



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




