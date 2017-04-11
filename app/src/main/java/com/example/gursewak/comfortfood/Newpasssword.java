package com.example.gursewak.comfortfood;

import android.content.Intent;
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

public class Newpasssword extends AppCompatActivity {

    EditText password ;
    EditText confirmpass;
    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpasssword);

        mobile = getIntent().getStringExtra("mobile_key");

        password = (EditText) findViewById(R.id.passwrd_et);
        confirmpass = (EditText)findViewById(R.id.confirmpass_et);

    }

    public void add(View v)
    {

        String s_pass =  password.getText().toString();
        String c_pass = confirmpass.getText().toString();


         if(s_pass.equals(""))
    {
        Toast.makeText(Newpasssword.this, "enter the pasword", Toast.LENGTH_SHORT).show();
        return;
    }
        if(c_pass.equals(""))
        {
            Toast.makeText(Newpasssword.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!s_pass.equals(c_pass)) {
            Toast.makeText(Newpasssword.this, "password does not match", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject job = new JSONObject();

        try {
            job.put("mobile_key" , mobile );
            job.put("pass_key" , s_pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

      //  Toast.makeText(Newpasssword.this , String.valueOf(job) , Toast.LENGTH_SHORT).show();

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/update_pass.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if(response.getString("key").equals("done"))
                    {

                        Toast.makeText(Newpasssword.this , "updated successfully" , Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(Newpasssword.this , "error try again" , Toast.LENGTH_SHORT).show();
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
        AppController app = new AppController(Newpasssword.this);

        app.addToRequestQueue(jobreq);

    }
}
