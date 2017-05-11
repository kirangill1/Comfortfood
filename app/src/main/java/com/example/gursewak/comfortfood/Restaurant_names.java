package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Restaurant_names extends AppCompatActivity {
    DrawerLayout drawer ;
    RecyclerView rec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_names);

        drawer = (DrawerLayout) findViewById(R.id.activity_restaurant_names_drawer);
        get_data();

    }
    public void get_data() {
        JsonObjectRequest job = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/get_restaurant.php", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray job = response.getJSONArray("result");
                    restaurantadapter adapter = new restaurantadapter(job,Restaurant_names.this);
                    rec = (RecyclerView) findViewById(R.id.rest_names_recycler);
                    rec.setLayoutManager(new LinearLayoutManager(Restaurant_names.this, LinearLayoutManager.VERTICAL, false));

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
        AppController app = new AppController(Restaurant_names.this);
        app.addToRequestQueue(job);
    }


    public  void lines(View v)
    {
        drawer.openDrawer(Gravity.LEFT);
    }

    public void share(View v)
        {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Download the app' COMFORT FOOD' via play store.";
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject here");
            sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(sharingIntent,"Share via"));
        }


    public void profile(View v)
    {
        Intent i = new Intent(Restaurant_names.this,Profile.class);
        startActivity(i);
    }

    public void order(View v)
    {
        Intent i = new Intent(Restaurant_names.this,User_view_order.class);
        startActivity(i);
    }

    public void feedback(View v)
    {
        Intent i = new Intent(Restaurant_names.this,Feedback.class);
        startActivity(i);
    }

    public void change_password(View v)
    {
        Intent i = new Intent(Restaurant_names.this,userchange_pass.class);
        startActivity(i);
        return;
    }
    public  void rating(View v)
    {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market:/detals?id =" + this.getPackageName())));
        }
        catch (android.content.ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id = " + this.getPackageName())));
        }
    }

    public void  logoutt(View v)
    {
        finish();
    }



}
