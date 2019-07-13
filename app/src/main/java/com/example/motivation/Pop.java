package com.example.motivation;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Timer;
import java.util.TimerTask;

public class Pop extends Activity {
    @Override
        protected void onCreate(Bundle b){
            super.onCreate(b);
            setContentView(R.layout.popwindow);
        DisplayMetrics drew =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(drew);
        final int width = drew.widthPixels;
        final int height = drew.heightPixels;
        getWindow().setLayout(width,height);
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        } , 1000,1000);

       finish();
        }
}
