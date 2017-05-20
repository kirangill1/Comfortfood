package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Admin_signup extends AppCompatActivity {

    public EditText username_et;
    public EditText name_et;
    public EditText pass_et;
    public EditText cpass_et;
    public EditText mobile_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signup);

        username_et =(EditText)findViewById(R.id.name_et);
        name_et = (EditText) findViewById(R.id.name_ett);
        pass_et = (EditText) findViewById(R.id.pass_et);
        cpass_et = (EditText) findViewById(R.id.cpass_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
    }

    public void signup(View v) {
        final String username = username_et.getText().toString();
        final String name = name_et.getText().toString();
        String pass = pass_et.getText().toString();
        String cpass = cpass_et.getText().toString();
        String mobile = mobile_et.getText().toString();


        if (username.equals("")) {
            Toast.makeText(Admin_signup.this, "enter the username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.equals("")) {
            Toast.makeText(Admin_signup.this, "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.equals("")) {
            Toast.makeText(Admin_signup.this, "enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cpass.equals("")) {
            Toast.makeText(Admin_signup.this, "re-enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!cpass.equals(pass)) {
            Toast.makeText(Admin_signup.this, "password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mobile.length() < 10) {
            Toast.makeText(Admin_signup.this, "enter valid mobile no", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();

        try {
            job.put("u", username);
            job.put("n",name);
            job.put("p", pass);
            job.put("m", mobile);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);
        JsonObjectRequest json = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/admin_signup.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("resultkey").equals("done"))
                    {
                        Toast.makeText(Admin_signup.this , "done" , Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor sp = getSharedPreferences("admin_info" , MODE_PRIVATE).edit();
                        sp.putString("admin_id",response.getString("admin_id"));

                        sp.commit();


                        Intent i = new Intent(Admin_signup.this , Admin_Home.class);

                        startActivity(i);

                        finish();
                    }

                    if(response.getString("resultkey").equals("user exist"))
                    {
                        Toast.makeText(Admin_signup.this , "user name already exist" , Toast.LENGTH_SHORT).show();
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

        json.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
        AppController app = new AppController(Admin_signup.this);

        app.addToRequestQueue(json);


    }
}




