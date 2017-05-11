package com.example.gursewak.comfortfood;

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

public class Profile extends AppCompatActivity {
    private EditText username_et;
    private EditText name_et;
    private EditText mobile_et;
    private EditText email_et;
    private EditText city_et;
    private EditText address_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username_et = (EditText) findViewById(R.id.username_Et);
        name_et = (EditText) findViewById(R.id.name_Et);
        mobile_et = (EditText) findViewById(R.id.mobile_Et);
        email_et = (EditText) findViewById(R.id.email_Et);
        city_et = (EditText) findViewById(R.id.City_Et);
        address_et = (EditText) findViewById(R.id.address_Et);
        get_values();
    }

    public void get_values() {
        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        final String username = sp.getString("username", "");

        try {
            job.put("username_key", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet.ip + "/comfort_food/user_editprofile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr = response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) jarr.get(0);

                    username_et.setText(job_response.getString("username"));
                    name_et.setText(job_response.getString("name"));
                    mobile_et.setText(job_response.getString("mobile"));
                    email_et.setText(job_response.getString("email"));
                    city_et.setText(job_response.getString("city"));
                    address_et.setText(job_response.getString("address"));


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

        AppController app = new AppController(Profile.this);

        app.addToRequestQueue(jobreq);
    }

    public void update_data(View v) {
        String username_s = username_et.getText().toString();
        String name = name_et.getText().toString();
        String mobile = mobile_et.getText().toString();
        String email = email_et.getText().toString();
        String city = city_et.getText().toString();
        String address = address_et.getText().toString();

        if (username_s.equals("")) {
            Toast.makeText(Profile.this, "enter your username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.equals(""))
            {
                Toast.makeText(Profile.this, "enter the name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mobile.equals(""))
            {
                Toast.makeText(Profile.this, "enter the mobile no", Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.equals(""))
            {
                Toast.makeText(Profile.this, "enter the email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (city.equals(""))
            {
                Toast.makeText(Profile.this, "enter the city", Toast.LENGTH_SHORT).show();
                return;
            }

            if (address.equals("")) {
                Toast.makeText(Profile.this, "enter the address", Toast.LENGTH_SHORT).show();
                return;
            }


            JSONObject job = new JSONObject();

            try {
                job.put("user", username_et);
                job.put("n", name);
                job.put("m", mobile);
                job.put("e", email);
                job.put("c", city);
                job.put("a", address);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet.ip + "/comfort_food/user_editprofile.php", job, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    System.out.println(response);

                    try {
                        if (response.getString("key").equals("done")) {

                            Toast.makeText(Profile.this, "Profile added succesfully", Toast.LENGTH_SHORT).show();

                            SharedPreferences.Editor sp = getSharedPreferences("admin_info", MODE_PRIVATE).edit();

                            sp.putString("admin_id", response.getString("admin_id"));
                            sp.commit();

                            finish();
                        }

                        if (response.getString("key").equals("")) {
                            Toast.makeText(Profile.this,"Error" , Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000, 2, 2));

        AppController app = new AppController(Profile.this);
        app.addToRequestQueue(jobreq);
    }

}






