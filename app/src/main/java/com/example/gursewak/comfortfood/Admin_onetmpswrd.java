package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_onetmpswrd extends AppCompatActivity {

    EditText otp_et;
    String pin;
    String mobile ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_onetmpswrd);

        otp_et = (EditText) findViewById(R.id.otp_et);

         mobile  = getIntent().getStringExtra("phone_key");

        pin = getIntent().getStringExtra("pin_key");

        Toast.makeText(Admin_onetmpswrd.this, pin, Toast.LENGTH_SHORT).show();

    }

    public  void verify_(View v)
    {
        String otp = otp_et.getText().toString();

        if(otp.equals(pin))
        {
            Toast.makeText(Admin_onetmpswrd.this,"code matched", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Admin_onetmpswrd.this, Admin_newpswrd.class );

            i.putExtra("mobile_key", mobile);
            startActivity(i);
            finish();





        }
        else {
            Toast.makeText(Admin_onetmpswrd.this,"code do not match", Toast.LENGTH_SHORT).show();
        }
    }
}