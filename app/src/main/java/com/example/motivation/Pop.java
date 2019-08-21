package com.example.motivation;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
        AnimationDrawable anim ;
        ImageView i = findViewById(R.id.fire);
        i.setBackgroundResource(R.drawable.animote);
        anim = (AnimationDrawable) i.getBackground();
        anim.start();
    }
    public void fade(){
        ImageView image = (ImageView)findViewById(R.id.gameon);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        image.startAnimation(animation1);

    }

}
