package com.example.gursewak.comfortfood;

import android.content.Intent;
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

public class Usersignup extends AppCompatActivity {
    public EditText name_et;
    public EditText pass_et;
    public EditText cpass_et;
    public EditText mobile_et;
    public RadioButton ngo_rb;
    public RadioButton user_rb;
    public String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usersignup);

        name_et = (EditText) findViewById(R.id.name_et);
        pass_et = (EditText) findViewById(R.id.pass_et);
        cpass_et = (EditText) findViewById(R.id.cpass_et);
        mobile_et = (EditText) findViewById(R.id.mobile_et);
        ngo_rb = (RadioButton)findViewById(R.id.ngo_rb);
        user_rb = (RadioButton)findViewById(R.id.user_rb);
    }

    public void signup(View v) {
        String name = name_et.getText().toString();
        String pass = pass_et.getText().toString();
        String cpass = cpass_et.getText().toString();
        String mobile = mobile_et.getText().toString();
        Boolean ngo = ngo_rb.isChecked();
        Boolean user = user_rb.isChecked();


        if (name.equals("")) {
            Toast.makeText(Usersignup.this, "enter the name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.equals("")) {
            Toast.makeText(Usersignup.this, "enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cpass.equals("")) {
            Toast.makeText(Usersignup.this, "re-enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mobile.equals("")) {
            Toast.makeText(Usersignup.this, "enter the mobile no", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cpass.equals(pass)) {
            Toast.makeText(Usersignup.this, "password does not match", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!user&&!ngo){
            Toast.makeText(Usersignup.this, "select user type", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user) {type= "user";

        }
        if(ngo) {
            type = "ngo";
        }

        JSONObject job = new JSONObject();

        try {
            job.put("n", name);
            job.put("p", pass);
            job.put("m", mobile);
            job.put("t",type);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest json = new JsonObjectRequest("http://192.168.0.69/comfort_food/signup.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("resultkey").equals("done"))
                    {

                        Toast.makeText(Usersignup.this , "done" , Toast.LENGTH_SHORT).show();
                    }

                    if(response.getString("resultkey").equals("user exist"))
                    {
                        Toast.makeText(Usersignup.this , "user name already exist" , Toast.LENGTH_SHORT).show();
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

        json.setRetryPolicy(new DefaultRetryPolicy(20000, 3, 2));
        AppController app = new AppController(Usersignup.this);

        app.addToRequestQueue(json);


    }
}

