package com.example.gursewak.comfortfood;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    private EditText username_et;
    private EditText name;
    private EditText mobile;
    private EditText email;
    private EditText city;
    private EditText address;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username_et = (EditText)findViewById(R.id.username_Et);
        name = (EditText)findViewById(R.id.name_Et);
        mobile = (EditText)findViewById(R.id.mobile_Et);
        email = (EditText)findViewById(R.id.email_Et);
        city = (EditText)findViewById(R.id.City_Et);
        address = (EditText)findViewById(R.id.address_Et);
        get_values();
    }

    public void get_values()
    {
        JSONObject job = new JSONObject();

        // code to get saved username
        SharedPreferences sp = getSharedPreferences("user_info" , MODE_PRIVATE);
        final String username = sp.getString("username" , "");

        try {
            job.put("username_key", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/edit_profile.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    JSONArray jarr =  response.getJSONArray("key");

                    JSONObject job_response = (JSONObject) jarr.get(0);

                    username_et.setText(job_response.getString("username"));
                    name.setText( job_response.getString("name") );
                    mobile.setText( job_response.getString("mobile"));
                    email.setText( job_response.getString("email"));
                    city.setText(job_response.getString("city"));
                    address.setText(job_response.getString("address"));



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

        AppController app = new AppController(Profile.this);

        app.addToRequestQueue(jobreq);
    }


}
