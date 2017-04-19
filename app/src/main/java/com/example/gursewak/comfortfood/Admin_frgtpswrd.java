package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;

public class Admin_frgtpswrd extends AppCompatActivity {
    EditText email_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_frgtpswrd);

        email_et = (EditText) findViewById(R.id.mobile_et);
    }
    public void next_(View v)
    {

        String email =  email_et.getText().toString();

        if(! Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Toast.makeText(Admin_frgtpswrd.this , "please enter valid email" , Toast.LENGTH_SHORT).show();
            return;
        }

        int randompin =  (int) (Math.random()*9000);

        Intent i = new Intent(Admin_frgtpswrd.this, Admin_onetmpswrd.class);

        i.putExtra("email_key",email);
        i.putExtra("pin_key",String.valueOf(randompin));


        startActivity(i);
        finish();
    }
}

