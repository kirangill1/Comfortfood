package com.example.gursewak.comfortfood;

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

public class Admin_vieworder extends AppCompatActivity {
    RecyclerView rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vieworder);
        get_data();
    }

    public void get_data()
    {
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);



        JSONObject jobj = new JSONObject();
        try {
            jobj.put("rest_id", sp.getString("rest_id","") );
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest job = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/admin_vieworder.php", jobj, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response)
            {

                try {
                    JSONArray job = response.getJSONArray("result");
                    vieworder_adapter adapter = new vieworder_adapter(job,Admin_vieworder.this);

                    rec = (RecyclerView) findViewById(R.id.vieworder_recyclerview);
                    rec.setLayoutManager(new LinearLayoutManager(Admin_vieworder.this, LinearLayoutManager.VERTICAL, false));

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

        AppController app = new AppController(Admin_vieworder.this);

        app.addToRequestQueue(job);
    }


}


























