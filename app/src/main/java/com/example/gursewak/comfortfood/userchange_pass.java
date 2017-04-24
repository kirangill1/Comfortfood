package com.example.gursewak.comfortfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class userchange_pass extends AppCompatActivity {
    public EditText previous_et, newpass_et, confirmpass_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userchange_pass);

        previous_et = (EditText) findViewById(R.id.passwrd_et);
        newpass_et = (EditText) findViewById(R.id.new_et);
        confirmpass_et = (EditText) findViewById(R.id.confirm_et);

    }
    public void add_detail(View v) {

        String previous_pass = previous_et.getText().toString();
        String new_pass = newpass_et.getText().toString();
        String confirm_pass = confirmpass_et.getText().toString();


        if (previous_pass.equals("")) {
            Toast.makeText(userchange_pass.this, "enter the previous password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (new_pass.equals("")) {
            Toast.makeText(userchange_pass.this, "enter the new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirm_pass.equals("")) {
            Toast.makeText(userchange_pass.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }


    }

}
