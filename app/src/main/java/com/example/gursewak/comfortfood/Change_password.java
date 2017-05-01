package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class Change_password extends AppCompatActivity {
    public EditText previous_et, newpass_et, confirmpass_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        previous_et = (EditText) findViewById(R.id.passwrd_et);
        newpass_et = (EditText) findViewById(R.id.new_et);
        confirmpass_et = (EditText) findViewById(R.id.confirm_et);

    }

    public void add_detail(View v) {

        String previous_pass = previous_et.getText().toString();
        String new_pass = newpass_et.getText().toString();
        String confirm_pass = confirmpass_et.getText().toString();


        if (previous_pass.equals("")) {
            Toast.makeText(Change_password.this, "enter the previous password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (new_pass.equals("")) {
            Toast.makeText(Change_password.this, "enter the new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirm_pass.equals("")) {
            Toast.makeText(Change_password.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!new_pass.equals(confirm_pass)) {
            Toast.makeText(Change_password.this, "password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        final String admin_id = sp.getString("admin_id", "");

        try {
            job.put("previouspass", previous_pass);
            job.put("newpass", new_pass);
            job.put("admin_id", admin_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet.ip + "/comfort_food/change_password.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if (response.getString("key").equals("done")) {

                        Toast.makeText(Change_password.this, "restaurant detail added succesfully", Toast.LENGTH_SHORT).show();


                        finish();
                    }

                    if (response.getString("key").equals("restaurant exist")) {
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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(Change_password.this);

        app.addToRequestQueue(jobreq);
    }


        public void update(View v)
        {
            Intent i = new Intent(Change_password.this ,HomeActivity.class);
            startActivity(i);
            return;
        }

    }

