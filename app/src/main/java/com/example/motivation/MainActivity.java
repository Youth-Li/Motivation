package com.example.motivation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button top , mid , bot;

    private  ArrayList<challangeStruct> easy;
    private TextView txtbox;
    private final int NUMOFGRETTINGS = 34;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();

    }
    public void init(){
        easy = new ArrayList<challangeStruct>();
        //Wire up buttons to work.
        txtbox = findViewById(R.id.Question);
        top = findViewById(R.id.Topbutton);
        mid = findViewById(R.id.Midbutton);
        bot = findViewById(R.id.Botbutton);
        txtbox.setText(loadGrettingData());
        try {
            loadData();
        }
        catch (FileNotFoundException e){
            System.out.println("Error : no file found.");
        }
        catch (IOException k){
            System.out.println("Error reading file");
        }

        // Init what the buttons do when clicked.
        top.setOnClickListener(this);
        mid.setOnClickListener(this);
        bot.setOnClickListener(this);

        setButText(top);
        setButText(mid);
        setButText(bot);
    }
    public challangeStruct setButText(Button b) {
        challangeStruct tmp = smallClick();
        b.setText(tmp.getChallange() + "\n\n"+"Duration: "+tmp.getDays()+" days"+"\n"+"Level: "+tmp.getLevel());
       // System.out.println(tmp.getLevel());
        if(tmp.getLevel() == 1){
            b.setBackgroundColor(Color.BLUE);
            b.setTextColor(Color.WHITE);
            return tmp;
        }
        if(tmp.getLevel() == 2){
            b.setBackgroundColor(Color.GREEN);
            b.setTextColor(Color.BLACK);
            return tmp;
        }
        if(tmp.getLevel() == 3){
            b.setBackgroundColor(Color.YELLOW);
            b.setTextColor(Color.BLACK);
            return tmp;
        }
        if(tmp.getLevel() == 4){
            b.setBackgroundColor(Color.MAGENTA);
            b.setTextColor(Color.WHITE);
            return tmp;
        }
        if(tmp.getLevel() == 5){
            b.setBackgroundColor(Color.RED);
            b.setTextColor(Color.BLACK);
        }
    return tmp;
    }

    public void onClick(View view){
        Button b = (Button)view;
      Intent i = new Intent(MainActivity.this, ConfirmWindow.class);
      i.putExtra("challange",b.getText().toString() );



        startActivity(i);
        }
        public void currentChallange(String s){

                try{
                    OutputStreamWriter wrt  = new OutputStreamWriter(openFileOutput("progress", Context.MODE_PRIVATE));
                    wrt.write(s);
                    wrt.write("\n");
                    wrt.write("done");
                    wrt.close();

                }
                catch (IOException e){
                    e.printStackTrace();
                }

        }
    public challangeStruct smallClick(){
        Random r = new Random();
        int x = r.nextInt(easy.size()-1);
       return easy.remove(x);
    }

    public int loadData() throws  IOException {

        BufferedReader bufred = new BufferedReader(
                new InputStreamReader(getAssets().open("ledger.txt")));
        String line = bufred.readLine();
        String tmp = "";
        while(line != null){
           tmp = line.substring(line.length()-3);

           line = line.substring(0, line.length()-3);
           //where 48 is the offset to get from ascii int to regular int.
           if(tmp.charAt(2) == 'd'){
               easy.add(new challangeStruct((int)tmp.charAt(0)-48,(int)tmp.charAt(1)-48, line));
               line = bufred.readLine();
               continue;
           }
           if(tmp.charAt(2)=='w'){
               easy.add(new challangeStruct((int)tmp.charAt(0)-48,((int)tmp.charAt(1)-48)*7, line));
               line = bufred.readLine();
           continue;
           }
           if(tmp.charAt(2) == 'y'){
               easy.add(new challangeStruct((int)tmp.charAt(0)-48,((int)tmp.charAt(1)-48)*365, line));
           }
            line = bufred.readLine();
        }
        bufred.close();
        return 0;
    }
    public String loadGrettingData(){
        Random r = new Random();
        int limit =  r.nextInt(NUMOFGRETTINGS-1);
        String s = "";
        try {
            BufferedReader bufred = new BufferedReader(
                    new InputStreamReader(getAssets().open("welcomeledger.txt")));
            s = bufred.readLine();
            for(int x = 0 ; x < limit ; x++){
              s =  bufred.readLine();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return s;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
