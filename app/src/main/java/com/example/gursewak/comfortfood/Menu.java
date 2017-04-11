package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void check_out(View v)
    {
        Intent i = new Intent(Menu.this, Payment_mode.class);
        startActivity(i);
        return;
    }

    public void add_menu(View v)
    {
        Intent i = new Intent(Menu.this,Add_newitem.class);
        startActivity(i);
        return;
    }
}
