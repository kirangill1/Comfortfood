package com.example.gursewak.comfortfood;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Adminfeedback extends AppCompatActivity {
    RecyclerView rec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminfeedback);
        get_data();
    }
    public void get_data() {
        JsonObjectRequest job = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/get_feedback.php", new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response){
                try {
                    JSONArray job = response.getJSONArray("result");
                    feedback_adapter adapter = new feedback_adapter(job,Adminfeedback.this);
                    rec = (RecyclerView) findViewById(R.id.feedback_recyclerview);
                    rec.setLayoutManager(new LinearLayoutManager(Adminfeedback.this, LinearLayoutManager.VERTICAL, false));

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
        AppController app = new AppController(Adminfeedback.this);
        app.addToRequestQueue(job);
    }

}
