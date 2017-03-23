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

import static com.example.gursewak.comfortfood.R.drawable.phone;

public class Userlogin extends AppCompatActivity {

    private EditText username_et;
    private EditText password_et;
    private TextView login_et;
    private TextView signup_et;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);


               password_et = (EditText) findViewById(R.id.edittext_user);
                password_et = (EditText) findViewById(R.id.edittext_pass);
                login_et = (TextView) findViewById(R.id.text_viewlogin);



            }

            public void login(View v) {
                String name = password_et.getText().toString();
                String pass = password_et.getText().toString();

                String username = "kirangill";
                String password = "12345";

                if(name.equals(""))
                {
                    Toast.makeText(Userlogin.this, "enter the name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.equals(""))
                {
                    Toast.makeText(Userlogin.this, "enter the password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (name.equals(username) && pass.equals(password)) {

                    Intent i = new Intent(Userlogin.this, Restaurant_names.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Userlogin.this, "incorrect input", Toast.LENGTH_SHORT).show();
                }
                JSONObject json = new JSONObject();

                try {
                    json.put("user",username);
                    json.put("p",pass);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest job = new JsonObjectRequest("http:/192.168.1.5/comfortfood/user_login", json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
                job.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
                AppController app = new AppController(Userlogin.this);

                app.addToRequestQueue(job);
            }

    public void signup(View v)
            {
                Intent i=new Intent(Userlogin.this, Usersignup.class);
                startActivity(i);
                return;
            }






        }




