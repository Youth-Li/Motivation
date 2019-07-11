package com.example.motivation;

import android.content.Context;
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
    Button small , med , large ,done;
    ArrayList<String> easy;
   // ArrayList<String> medium;
    //ArrayList<String> largo;
    TextView txtbox;
    Context context;



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
        context = getApplicationContext();
        easy = new ArrayList<String>();
        //medium = new ArrayList<String>();
        //largo = new ArrayList<String>();
        txtbox = findViewById(R.id.Answer);
        donePress d = new donePress(this);
        try {
            loadData();
        }
        catch (FileNotFoundException e){
            System.out.println("Error : no file found.");
        }
        catch (IOException k){
            System.out.println("Error reading file");
        }
        Random rnd = new Random();
        int primary = Color.WHITE;
        int color = Color.argb(255, (rnd.nextInt(256)+ primary)/2, (rnd.nextInt(256)+ primary)/2, (rnd.nextInt(256)+ primary)/2);
        small = findViewById(R.id.choice1);
        small.setBackgroundColor(color);
        small.setTextColor(primary);
        med = findViewById(R.id.choice2);
        med.setTextColor(primary);
        color = Color.argb(255,(rnd.nextInt(256)+ primary)/2, (rnd.nextInt(256)+ primary)/2, (rnd.nextInt(256)+ primary)/2);
        med.setBackgroundColor(color);
        large = findViewById(R.id.choice3);
        large.setTextColor(primary);
        color = Color.argb(255, (rnd.nextInt(256)+ primary)/2, (rnd.nextInt(256)+ primary)/2, (rnd.nextInt(256)+ primary)/2);
        large.setBackgroundColor(color);
        done = findViewById(R.id.donebut);
        done.setOnClickListener(d);
        small.setOnClickListener(this);
        med = findViewById(R.id.choice2);
        med.setOnClickListener(this);
        large = findViewById(R.id.choice3);
        large.setOnClickListener(this);
        reRoll();
    }
    public void reRoll(){
        small.setText(smallClick());
        med.setText(smallClick());
        large.setText(smallClick());
    }
    public void onClick(View view){
        txtbox.setTextColor(Color.parseColor("#B71C1C"));
        txtbox.setText("Game ON!");
        if(done.getVisibility() != View.VISIBLE) {
            switch (view.getId()) {
                case R.id.choice1:
                    currentChallange(small.getText().toString());
                    break;
                case R.id.choice2:
                    currentChallange(med.getText().toString());
                    break;
                case R.id.choice3:
                    currentChallange(large.getText().toString());
                    break;
                default:
                    throw new RuntimeException("Unknown button ID");


            }//the method has no regulation on how many times the button is pressed and that could cause trouble.
        }
        done.setVisibility(View.VISIBLE);
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
    public String smallClick(){
        Random r = new Random();
        int x = r.nextInt(easy.size()-1);
       return easy.remove(x);
    }

    public int loadData() throws  IOException {

        BufferedReader bufred = new BufferedReader(
                new InputStreamReader(getAssets().open("ledger.txt")));
            String line = bufred.readLine();

                while(line != null){
                    line = bufred.readLine();
                    easy.add(line);
                }
        bufred.close();
        return 0;
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
