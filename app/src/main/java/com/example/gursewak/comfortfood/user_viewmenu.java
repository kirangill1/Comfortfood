package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class User_viewmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_viewmenu);
    }
    public void check_outt(View v)
    {
        Intent i = new Intent(User_viewmenu.this, Payment_mode.class);
        startActivity(i);
        return;
    }
}
