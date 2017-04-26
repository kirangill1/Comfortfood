package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Menu extends AppCompatActivity {
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
                    menu_adapter adapter = new menu_adapter(job,Menu.this);
                    rec = (RecyclerView) findViewById(R.id.rest_menu_recycler);
                    rec.setLayoutManager(new LinearLayoutManager(Menu.this, LinearLayoutManager.VERTICAL, false));

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
        AppController app = new AppController(Menu.this);
        app.addToRequestQueue(job);
    }

    public void check_out(View v)
    {
        Intent i = new Intent(Menu.this, Payment_mode.class);
        startActivity(i);
        return;
    }

    public void add_menu(View v)
    {
        Intent i = new Intent(Menu.this,Add_newitem.class);
        startActivity(i);
        return;
    }
}
