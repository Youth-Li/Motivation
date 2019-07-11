package com.example.motivation;

import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class donePress implements View.OnClickListener {
    MainActivity m ;

    public donePress(MainActivity m){
        this.m = m;

    }

    public void onClick(View v){
        try {
            FileInputStream fis = m.context.openFileInput("progress");

            BufferedReader bufred = new BufferedReader(
                    new InputStreamReader(fis));
            String line = bufred.readLine();
            while(line != null) {
                System.out.println(line);
                line = bufred.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
