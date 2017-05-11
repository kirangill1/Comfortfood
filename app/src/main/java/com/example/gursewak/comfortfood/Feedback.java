package com.example.gursewak.comfortfood;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Feedback extends AppCompatActivity {
    EditText name_et, date_et, message_et;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        name_et = (EditText)findViewById(R.id.name__);
        date_et = (EditText)findViewById(R.id.date__);
        message_et = (EditText)findViewById(R.id.message__);
        send = (Button)findViewById(R.id.send);
    }
    public void send_data(View v)
    {
        String name = name_et.getText().toString();
        String date = date_et.getText().toString();
        String message = message_et.getText().toString();

        if(name.equals("") )
        {
            Toast.makeText(Feedback.this, "enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(date.equals(""))
        {
            Toast.makeText(Feedback.this, "enter the date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (message.equals("")) {
            Toast.makeText(Feedback.this, "enter your message", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject job = new JSONObject();



        try {
            job.put("n", name);
            job.put("d",date);
            job.put("m", message);


        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/feedback.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("done"))
                    {

                        Toast.makeText(Feedback.this , "feedback added succesfully" , Toast.LENGTH_SHORT).show();

                        finish();
                    }

                    if(response.getString("key").equals("not done"))
                    {
                        Toast.makeText(Feedback.this , "Error re-enter it" , Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);

            }
        });

        jobreq.setRetryPolicy(new DefaultRetryPolicy(20000 , 2 , 2));

        AppController app = new AppController(Feedback.this);

        app.addToRequestQueue(jobreq);
    }

    public void select_date (View v)
    {
        Calendar mcurrentDate = Calendar.getInstance();
        final int year= mcurrentDate.get(Calendar.YEAR);
        final int month = mcurrentDate.get(Calendar.MONTH);
        final int day = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog mDatePicker = new DatePickerDialog(Feedback.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
               date_et.setText(new StringBuilder().append(selectedyear).append("/").append(selectedmonth + 1).append("/").append(selectedday));
                int month_k = selectedmonth + 1;

            }
        }, year, month, day);
        mDatePicker.setTitle("Please select date");
        // TODO Hide Future Date Here
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());

        // TODO Hide Past Date Here
        //  mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis());
        mDatePicker.show();
    }
    }


