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

import org.json.JSONException;
import org.json.JSONObject;

public class userchange_pass extends AppCompatActivity {
    public EditText previous_et, newpass_et, confirmpass_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchange_pass);

        previous_et = (EditText) findViewById(R.id.passwrd_et);
        newpass_et = (EditText) findViewById(R.id.new_et);
        confirmpass_et = (EditText) findViewById(R.id.confirm_et);

    }

    public void add_detail(View v) {

        String previous_pass = previous_et.getText().toString();
        String new_pass = newpass_et.getText().toString();
        String confirm_pass = confirmpass_et.getText().toString();


        if (previous_pass.equals("")) {
            Toast.makeText(userchange_pass.this, "enter the previous password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (new_pass.equals("")) {
            Toast.makeText(userchange_pass.this, "enter the new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirm_pass.equals("")) {
            Toast.makeText(userchange_pass.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("admin_info", MODE_PRIVATE);
        final String admin_id = sp.getString("user_id", "");

        try {
            job.put("previouspass", previous_pass);
            job.put("newpass", new_pass);
            job.put("user_id", admin_id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://" + Internet.ip + "/comfort_food/user_changepassword.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if (response.getString("key").equals("done")) {

                        Toast.makeText(userchange_pass.this, "password changed successfully", Toast.LENGTH_SHORT).show();


                        finish();
                    }

                    if (response.getString("key").equals("not done")) {

                        Toast.makeText(userchange_pass.this, "error", Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(userchange_pass.this);

        app.addToRequestQueue(jobreq);
    }
}