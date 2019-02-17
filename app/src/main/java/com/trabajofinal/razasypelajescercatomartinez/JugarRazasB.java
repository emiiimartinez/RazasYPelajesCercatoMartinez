package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static java.lang.Thread.sleep;

public class JugarRazasB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_razas_b);
        SharedPreferences preferencias =getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String formatoR=preferencias.getString("formatoDeReconocimiento","no hay nada");
        String formasDeI=preferencias.getString("formasDeInteraccion","no hay nada");
        boolean cRazasyPelajes=preferencias.getBoolean("checkRazasyPelajes",false);
        boolean cCruza=preferencias.getBoolean("checkCruza",false);
        String nivel=preferencias.getString("nivel","noy hay nada");
        String audio=preferencias.getString("audio","no hay nada");
        ImageButton cab1 = (ImageButton) findViewById(R.id.caballo1);
        ImageButton cab2 = (ImageButton) findViewById(R.id.caballo2);
        ImageButton cab3 = (ImageButton) findViewById(R.id.caballo3);
        ImageButton cab4 = (ImageButton) findViewById(R.id.caballo4);
        if (nivel=="1"){
            cab1.setVisibility(View.INVISIBLE);
            cab4.setVisibility(View.INVISIBLE);
        }
        ImageButton volver= (ImageButton)findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        cab1.setOnClickListener(new View.OnClickListener() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.resopla);
            @Override
            public void onClick(View view) {
                mp.start();
                try {
                    sleep(1000);
                    mp.start();
                    sleep(1000);
                    mp.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        cab2.setOnClickListener(new View.OnClickListener() {
            MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.relincho1);
            @Override
            public void onClick(View view) {
                mp.start();
                mp.release();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

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
