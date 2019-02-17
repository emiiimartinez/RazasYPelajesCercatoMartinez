package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static java.lang.Thread.sleep;

public class JugarCruza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_cruza);

        ImageButton volver= (ImageButton)findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        ImageButton cab1 = (ImageButton) findViewById(R.id.caballo1);
        cab1.setOnClickListener(new View.OnClickListener() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.relincho1);

            @Override
            public void onClick(View view) {
                mp.start();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        ImageButton cab2 = (ImageButton) findViewById(R.id.caballo2);
        cab2.setOnClickListener(new View.OnClickListener() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.resopla);

            @Override
            public void onClick(View view) {
                mp.start();
                try {
                    sleep(1000);
                    mp.start();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        ImageButton cab3 = (ImageButton) findViewById(R.id.caballo3);
        cab3.setOnClickListener(new View.OnClickListener() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.resopla);

            @Override
            public void onClick(View view) {
                mp.start();
                try {
                    sleep(1000);
                    mp.start();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        ImageButton cab4 = (ImageButton) findViewById(R.id.caballo4);
        cab4.setOnClickListener(new View.OnClickListener() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.resopla);

            @Override
            public void onClick(View view) {
                mp.start();
                try {
                    sleep(1000);
                    mp.start();
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }
}
