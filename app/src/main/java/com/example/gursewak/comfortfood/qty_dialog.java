package com.example.gursewak.comfortfood;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gursewak on 4/27/2017.
 */

public class qty_dialog extends Dialog

{

    public Button done_btn , cancel_btn;

    public EditText qty_edit;

    public String menuid , name , price;

    public Context c;


    public qty_dialog(Context context , String menuid , String name , String price) {
        super(context);

        this.menuid = menuid;
        this.name = name;
        this.price = price;

        this.c = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.qty_dialog_layout);

        qty_edit = (EditText) findViewById(R.id.qty_edit);

        done_btn = (Button) findViewById(R.id.done_btn);

        cancel_btn = (Button) findViewById(R.id.cancel_btn);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {
                if (! qty_edit.getText().toString().equals("") && ! qty_edit.getText().toString().equals("0")) {
                    JSONObject job = new JSONObject();
                    try {
                        job.put("menu_id", menuid);
                        job.put("name", name);
                        job.put("price", price);
                        job.put("qty", qty_edit.getText().toString());

                        User_viewmenu.cart_array.put(job);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(c , "item added successfully", Toast.LENGTH_SHORT).show();
                    User_viewmenu.cart_counter.setText(String.valueOf(User_viewmenu.cart_array.length()));
                    dismiss();
                }

                else {
                    Toast.makeText(c , "please select quantity more then 0", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
