package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Admin_home extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

    }

    public void restaurant_detail(View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void left_food (View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void menu (View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void view_order (View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void view_feedback (View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void admin_profile (View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void logout (View v)
    {
        Intent i = new Intent(Admin_home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    }

