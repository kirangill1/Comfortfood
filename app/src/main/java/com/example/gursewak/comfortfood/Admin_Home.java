package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__home);

    }

    public void restaurant_detail(View v)
    {
        Intent i = new Intent(Admin_Home.this ,Admin_restaurantDetail.class);
        startActivity(i);
        return;
    }

    public void left_food (View v)
    {
        Intent i = new Intent(Admin_Home.this ,Admin_Leftoverfood.class);
        startActivity(i);
        return;
    }

    public void menu (View v)
    {
        Intent i = new Intent(Admin_Home.this ,Menu.class);
        startActivity(i);
        return;
    }

    public void view_order (View v)
    {
        Intent i = new Intent(Admin_Home.this ,Admin_vieworder.class);
        startActivity(i);
        return;
    }

    public void view_feedback (View v)
    {
        Intent i = new Intent(Admin_Home.this ,Adminfeedback.class);
        startActivity(i);
        return;
    }

    public void admin_profile (View v)
    {
        Intent i = new Intent(Admin_Home.this ,Admin_profile.class);
        startActivity(i);
        return;
    }

    public void logout (View v)
    {

        finish();
    }

    public void change_password(View view) {

        Intent i = new Intent(Admin_Home.this , Change_password.class);
        startActivity(i);
    }
}

