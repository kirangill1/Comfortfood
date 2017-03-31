package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Admin_login extends AppCompatActivity {
    private EditText username_et;
    private EditText password_et;
    private TextView login_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        
        username_et = (EditText) findViewById(R.id.edittext_user);
        password_et = (EditText) findViewById(R.id.edittext_pass);
        login_et = (TextView) findViewById(R.id.text_viewlogin);

    }

    public void login(View v) {
        String name = username_et.getText().toString();
        String pass = password_et.getText().toString();



        if(name.equals(""))
        {
            Toast.makeText(Admin_login.this, "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.equals(""))
        {
            Toast.makeText(Admin_login.this, "enter the password", Toast.LENGTH_SHORT).show();
            return;
        }




        JSONObject json = new JSONObject();

        try {
            json.put("user", name);
            json.put("pass", pass);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest job = new JsonObjectRequest("http://192.168.0.69/comfort_food/adminlogin.php", json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    if(response.getString("key").equals("done"))
                    {
                        Intent i = new Intent(Admin_login.this, Admin_Home.class);
                        startActivity(i);

                        finish();
                    }
                    else {
                        Toast.makeText(Admin_login.this, "error try again ", Toast.LENGTH_SHORT).show();

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

        job.setRetryPolicy(new DefaultRetryPolicy(20000,2,3));
        AppController app = new AppController(Admin_login.this);

        app.addToRequestQueue(job);


    }





}
