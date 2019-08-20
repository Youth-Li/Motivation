package com.example.motivation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmWindow extends AppCompatActivity implements View.OnClickListener {
    private Button yes,no;
    private TextView challange;

    public TextView getChallange() {
        return challange;
    }

    public void setChallange(String challange) {
        this.challange.setText(challange);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_window);
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
        yes = findViewById(R.id.Acceptbutton);
        no = findViewById(R.id.Denybutton);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        challange = findViewById(R.id.challange);
        setChallange(getIntent().getStringExtra("challange"));
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == yes.getId()) {
            startActivity(new Intent(ConfirmWindow.this, Pop.class));
        }
        else{
            finish();
        }
    }
}
