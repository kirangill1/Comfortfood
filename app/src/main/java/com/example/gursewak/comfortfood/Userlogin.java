package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.content.SharedPreferences;
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


               username_et = (EditText) findViewById(R.id.edittext_user);
                password_et = (EditText) findViewById(R.id.edittext_pass);
                login_et = (TextView) findViewById(R.id.text_viewlogin);



            }

            public void login(View v) {
                final String name = username_et.getText().toString();
                String pass = password_et.getText().toString();



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



                JSONObject json = new JSONObject();

                try {
                    json.put("user",name);
                    json.put("p",pass);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest job = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/userlgin.php", json, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            if(response.getString("key").equals("done"))
                            {
                                // code to save username throught app
                                SharedPreferences.Editor sp = getSharedPreferences("user_info" , MODE_PRIVATE).edit();
                                sp.putString("username",name);
                                sp.commit();


                                Intent i = new Intent(Userlogin.this , Restaurant_names.class);

                                startActivity(i);

                                finish();
                            }

                            else
                            {
                                Toast.makeText(Userlogin.this,"error try again",Toast.LENGTH_SHORT).show();
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

    public void forget_password(View v)
    {
        Intent i = new Intent(Userlogin.this,Forget_password.class);
        startActivity(i);
        return;
    }
    public void change_password(View v)
    {
        Intent i = new Intent(Userlogin.this,userchange_pass.class);
        startActivity(i);
        return;
    }


   }




