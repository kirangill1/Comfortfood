package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Admin_Leftoverfood extends AppCompatActivity {

    public RadioButton left_food,no_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_leftoverfood);

        left_food = (RadioButton)findViewById(R.id.radio_button);
        no_food = (RadioButton)findViewById(R.id.radio_button1);

    }

    public void have_left(View v)
    {
        update_status("1");
        Intent i = new Intent(Admin_Leftoverfood.this,Left_over_menu.class);

        startActivity(i);

        finish();
    }

    public void no_food(View v)
    {
        update_status("0");
        Intent i = new Intent(Admin_Leftoverfood.this,No_leftfood.class);
        startActivity(i);
        finish();
    }

    public void update_status(String status)
    {
        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);
        final String rest_id = sp.getString("rest_id" , "");

        try {
            job.put("rest_id", rest_id);
            job.put("status" , status);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/update_restaurant_status.php", job, new Response.Listener<JSONObject>() {
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

        AppController app = new AppController(Admin_Leftoverfood.this);

        app.addToRequestQueue(jobreq);
    }

}
