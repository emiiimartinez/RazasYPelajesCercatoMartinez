package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FinalCopa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_copa);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton volver= (ImageButton)findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        ImageView trophy = (ImageView) findViewById(R.id.copa);
      //  trophy.setBackgroundResource(R.drawable.copaanimation);
        AnimationDrawable gyroAnimation = (AnimationDrawable) trophy.getBackground();

        gyroAnimation.start();

    }

}
