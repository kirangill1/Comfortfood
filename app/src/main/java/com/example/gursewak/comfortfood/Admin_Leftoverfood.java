package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Admin_Leftoverfood extends AppCompatActivity {

    public RadioButton left_food,no_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_leftoverfood);

        left_food = (RadioButton)findViewById(R.id.radio_button);
        no_food = (RadioButton)findViewById(R.id.radio_button1);
    }

    public void left_food(View v)
    {
        Intent i = new Intent(Admin_Leftoverfood.this,Menu.class);
        startActivity(i);
        return;
    }

    public void no_food(View v)
    {
        Intent i = new Intent(Admin_Leftoverfood.this,No_leftfood.class);
        startActivity(i);
        return;
    }

}
