package com.example.gursewak.comfortfood;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Add_newitem extends AppCompatActivity {
    public EditText item_name,item_price, item_type, item_qty;

    public ImageView menu_img ;

    String menu_image_string = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_newitem);

        item_name = (EditText) findViewById(R.id.item_namee);
        item_price = (EditText)findViewById(R.id.item_pricee);
        item_type = (EditText)findViewById(R.id.item_typee);
        item_qty = (EditText)findViewById(R.id.item_qty);


        menu_img = (ImageView) findViewById(R.id.menu_image);
    }

    public void save_menu(View v)
    {
        SharedPreferences sp = getSharedPreferences("admin_info" , MODE_PRIVATE);
        final String admin_id = sp.getString("admin_id" , "");
        JSONObject  job = new JSONObject();
        try {

            job.put("rest_id" ,admin_id );
            job.put("item_name" , item_name.getText().toString());
            job.put("item_price" , item_price.getText().toString());
            job.put("item_type", item_type.getText().toString());
            job.put("item_qty", item_qty.getText().toString());
            job.put("image" , menu_image_string);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(job);

        JsonObjectRequest jobreq = new JsonObjectRequest("http://"+Internet.ip+"/comfort_food/add_menu_item.php", job, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println(response);

                try {
                    if(response.getString("key").equals("done"))
                    {

                        Toast.makeText(Add_newitem.this , "item added succesfully" , Toast.LENGTH_SHORT).show();

                        finish();
                    }

                    if(response.getString("key").equals("restaurant exist"))
                    {
                        Toast.makeText(Add_newitem.this , "item already exist" , Toast.LENGTH_SHORT).show();
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

        AppController app = new AppController(Add_newitem.this);

        app.addToRequestQueue(jobreq);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    public void add_image(View view) {


        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        //File file = new File(Environment.getExternalStorageDirectory(),
        //      counter+".jpg");
        //Uri photoPath = Uri.fromFile(file);
        // i.putExtra(MediaStore.EXTRA_OUTPUT, photoPath);
        startActivityForResult(i, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100 && data != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                Bitmap bitmap2 = decodeUri(Add_newitem.this , filePath , 400);
                menu_image_string = getStringImage(bitmap2);
                //Setting the Bitmap to ImageView
                menu_img.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public String getStringImage(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public  Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

}
