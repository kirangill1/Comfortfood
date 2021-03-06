package com.example.gursewak.comfortfood;

/**
 * Created by gursewak on 3/14/2017.
 */


    import android.app.Activity;
    import android.content.Context;


    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.toolbox.Volley;



    public class AppController  {

        private RequestQueue mRequestQueue;
        private Activity c;


        private static AppController mInstance;

        public AppController(Activity c)
        {
            mInstance = this;
            this.c = c;

        }

        public static synchronized AppController getInstance()
        {
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null)
            {
                mRequestQueue = Volley.newRequestQueue(c);
            }

            return mRequestQueue;
        }



        public  void addToRequestQueue(Request req) {

            getRequestQueue().add(req);
        }


    }

