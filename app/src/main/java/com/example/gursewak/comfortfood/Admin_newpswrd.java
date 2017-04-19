package com.example.gursewak.comfortfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Admin_newpswrd extends AppCompatActivity {
    EditText password ;
    EditText confirmpass;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_newpswrd);

        email = getIntent().getStringExtra("email_key");

        password = (EditText) findViewById(R.id.passwrd_et);
        confirmpass = (EditText)findViewById(R.id.confirmpass_et);

    }

    public void add_(View v)
    {

        String s_pass =  password.getText().toString();
        String c_pass = confirmpass.getText().toString();


        if(s_pass.length() <8)
        {
            Toast.makeText(Admin_newpswrd.this, "enter atlest 8 digit password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(c_pass.equals(""))
        {
            Toast.makeText(Admin_newpswrd.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!s_pass.equals(c_pass)) {
            Toast.makeText(Admin_newpswrd.this, "password does not match", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject job = new JSONObject();

        try {
            job.put("email_key" , email );
            job.put("pass_key" , s_pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        //  Toast.makeText(Newpasssword.this , String.valueOf(job) , Toast.LENGTH_SHORT).show();

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/update_admnpass.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("done"))
                    {

                        Toast.makeText(Admin_newpswrd.this , "updated successfully" , Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Admin_newpswrd.this , "error try again" , Toast.LENGTH_SHORT).show();
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

        jobreq.setRetryPolicy(new DefaultRetryPolicy(2000 , 2 , 2));
        AppController app = new AppController(Admin_newpswrd.this);

        app.addToRequestQueue(jobreq);

    }
}


