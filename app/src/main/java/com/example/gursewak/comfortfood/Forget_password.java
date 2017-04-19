package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Forget_password extends AppCompatActivity {

    EditText mobile_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mobile_et = (EditText) findViewById(R.id.mobile_et);
    }
    public void next(View v)
    {

        String mobile =  mobile_et.getText().toString();

        if(mobile.length() < 10)
        {
            Toast.makeText(Forget_password.this , " please enter valid mobile number", Toast.LENGTH_SHORT).show();
            return;
        }

         int randompin =  (int) (Math.random()*9000);

        Intent i = new Intent(Forget_password.this, Onetime_password.class);

        i.putExtra("mobile_key",mobile);
        i.putExtra("pin_key",String.valueOf(randompin));


        startActivity(i);
        finish();
    }
}
