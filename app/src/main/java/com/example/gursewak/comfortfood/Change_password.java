package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

public class Change_password extends AppCompatActivity {
    public EditText previous_et, newpass_et, confirmpass_et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        previous_et = (EditText) findViewById(R.id.passwrd_et);
        newpass_et = (EditText) findViewById(R.id.new_et);
        confirmpass_et = (EditText) findViewById(R.id.confirm_et);

    }

    public void add_detail(View v) {

        String previous_pass = previous_et.getText().toString();
        String new_pass = newpass_et.getText().toString();
        String confirm_pass = confirmpass_et.getText().toString();


        if (previous_pass.equals("")) {
            Toast.makeText(Change_password.this, "enter the previous password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (new_pass.equals("")) {
            Toast.makeText(Change_password.this, "enter the new password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (confirm_pass.equals("")) {
            Toast.makeText(Change_password.this, "enter the confirm password", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}

