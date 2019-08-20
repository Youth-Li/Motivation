package com.example.motivation;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.popwindow);
        DisplayMetrics drew = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(drew);
        final int width = drew.widthPixels;
        final int height = drew.heightPixels;
        getWindow().setLayout(width, height);
        fade();
    }
    public void fade(){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);

    }

}
