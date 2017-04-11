package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

public class Restaurant_names extends AppCompatActivity {
    DrawerLayout drawer ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_names);

        drawer = (DrawerLayout) findViewById(R.id.activity_restaurant_names_drawer);

    }
    public  void lines(View v)
    {
        drawer.openDrawer(Gravity.LEFT);
    }

    public void brother_dhaba(View v)
    {
        Intent i = new Intent(Restaurant_names.this,User_viewmenu.class);
        startActivity(i);
        return;
    }

}
