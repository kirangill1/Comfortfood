package com.example.gursewak.comfortfood;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Left_over_menu extends AppCompatActivity {

    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_over_menu);

        rec= (RecyclerView)findViewById(R.id.rest_menu_recycler);
        get_data();
    }

    public void get_data() {


        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);

        JSONObject jobr = new JSONObject();
        try {
            jobr.put("rest_id" ,sp.getString("rest_id","") );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest job = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/get_menu.php", jobr, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray job = response.getJSONArray("result");
                    left_over_food_adapter adapter = new left_over_food_adapter(job,Left_over_menu.this);
                    rec = (RecyclerView) findViewById(R.id.rest_menu_recycler);
                    rec.setLayoutManager(new LinearLayoutManager(Left_over_menu.this, LinearLayoutManager.VERTICAL, false));

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
        AppController app = new AppController(Left_over_menu.this);
        app.addToRequestQueue(job);
    }


    public static void update_menu(String status , Activity a , String menu_id)
    {
        JSONObject job = new JSONObject();

        // code to get saved username


        try {
            job.put("menu_id", menu_id);
            job.put("status" , status);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/update_menu_status.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("done"))
                    {

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

        AppController app = new AppController(a);

        app.addToRequestQueue(jobreq);

    }

}
