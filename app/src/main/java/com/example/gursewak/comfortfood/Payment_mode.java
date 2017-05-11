package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Payment_mode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);
    }
    public void online_pay(View v)
    {
        Intent i = new Intent(Payment_mode.this,Card_detail.class);
        i.putExtra("rest_id" , getIntent().getStringExtra("rest_id"));
        startActivity(i);
        finish();
    }

    public void pay_pickup(View v)
    {
        Intent i = new Intent(Payment_mode.this, Order_confirmation.class);
        i.putExtra("rest_id" , getIntent().getStringExtra("rest_id"));
        startActivity(i);
        finish();

    }
}
