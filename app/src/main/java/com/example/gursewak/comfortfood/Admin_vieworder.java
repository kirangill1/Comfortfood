package com.example.gursewak.comfortfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Admin_vieworder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vieworder);
    }

    public static class Order_configuration extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_order_configuration);
        }
    }
}
