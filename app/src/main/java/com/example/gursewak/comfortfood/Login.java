package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    public TextView admin_login;
    public TextView user_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        admin_login = (TextView)findViewById(R.id.user_tv);
        user_login = (TextView)findViewById(R.id.admin_tv);
    }

    public void Alogin(View v)
    {
        Intent i = new Intent(Login.this, Adminlogin.class);
        startActivity(i);
        finish();

    }
    public void Ulogin(View v)
    {
        Intent i = new Intent(Login.this, Userlogin.class);
        startActivity(i);
        finish();
    }
}
