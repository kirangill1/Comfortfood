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

public class Admin_profile extends AppCompatActivity {
    public EditText username_et;
    public EditText name_et;
    public EditText mobile_et;
    public EditText email_et;
    public EditText city_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

      username_et = (EditText)findViewById(R.id.username_Et);
        name_et = (EditText)findViewById(R.id.name_Et);
        mobile_et = (EditText)findViewById(R.id.mobile_Et);
        email_et = (EditText)findViewById(R.id.email_Et);
        city_et = (EditText)findViewById(R.id.City_Et);
        get_values();
    }
    public void get_values()
    {
        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);
        final String username = sp.getString("admin_id" , "");

        try {
            job.put("username_key", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/admin_editprofile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr = response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) jarr.get(0);

                    username_et.setText(job_response.getString("name"));
                    name_et.setText( job_response.getString("full_name") );
                    mobile_et.setText( job_response.getString("mobile"));
                    email_et.setText( job_response.getString("email"));
                    city_et.setText(job_response.getString("city"));



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

        AppController app = new AppController(Admin_profile.this);
        app.addToRequestQueue(jobreq);
    }

    public void update_data(View v)
    {
        String username = username_et.getText().toString();
        String name = name_et.getText().toString();
        String mobile = mobile_et.getText().toString();
        String email = email_et.getText().toString();
        String city = city_et.getText().toString();


        if(username.equals(""))
        {
            Toast.makeText(Admin_profile.this, "enter your username", Toast.LENGTH_SHORT).show();
            return;
        }


        if(name.equals(""))
        {
            Toast.makeText(Admin_profile.this, "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mobile.equals(""))
        {
            Toast.makeText(Admin_profile.this, "enter the mobile no", Toast.LENGTH_SHORT).show();
            return;
        }

        if(email.equals(""))
        {
            Toast.makeText(Admin_profile.this, "enter the email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(city.equals(""))
        {
            Toast.makeText(Admin_profile.this, "enter the city", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject job  = new JSONObject();

        try {
            job.put("user", username);
            job.put("n",name);
            job.put("m",mobile);
            job.put("e",email);
            job.put("c",city);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet.ip + "/comfort_food/update_admprofile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if (response.getString("key").equals("done")) {

                        Toast.makeText(Admin_profile.this, "Profile added succesfully", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor sp = getSharedPreferences("admin_info", MODE_PRIVATE).edit();

                        sp.putString("admin_id", response.getString("admin_id"));
                        sp.commit();

                        finish();
                    }

                    if (response.getString("key").equals("")) {
                        Toast.makeText(Admin_profile.this,"Error" , Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(Admin_profile.this);
        app.addToRequestQueue(jobreq);
    }

}






