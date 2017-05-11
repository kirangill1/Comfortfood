package com.example.gursewak.comfortfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Card_detail extends AppCompatActivity {
    public EditText card_number,cuu_number,expirey_date;
    public Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        card_number = (EditText) findViewById(R.id.card_number);
        cuu_number = (EditText) findViewById(R.id.ccu_number);
        expirey_date = (EditText) findViewById(R.id.expirey);

    }


    public void confirm_order(View view) {

        String card = card_number.getText().toString();
        String cvv = cuu_number.getText().toString();
        String expiry = expirey_date.getText().toString();


        if (card.equals("")) {
            Toast.makeText(Card_detail.this, "enter the card number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cvv.equals("")) {
            Toast.makeText(Card_detail.this, "enter the cuu number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (expiry.equals("")) {
            Toast.makeText(Card_detail.this, "enter the expiry date", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent i = new Intent(Card_detail.this, Order_confirmation.class);
        i.putExtra("rest_id" , getIntent().getStringExtra("rest_id"));
        startActivity(i);
        finish();
    }
}
