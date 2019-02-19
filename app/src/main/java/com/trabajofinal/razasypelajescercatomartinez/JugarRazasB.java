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
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String formatoR = preferencias.getString("formatoDeReconocimiento", "no hay nada");
        String formasDeI = preferencias.getString("formasDeInteraccion", "no hay nada");
        boolean cRazasyPelajes = preferencias.getBoolean("checkRazasyPelajes", false);
        boolean cCruza = preferencias.getBoolean("checkCruza", false);
        String nivel = preferencias.getString("nivel", "1");
        String audio = preferencias.getString("audio", "no hay nada");

        final int[] countRondas = {preferencias.getInt("countRondas", 1)};
        final int[] countAciertos = {preferencias.getInt("countAciertos", 0)};
        final SharedPreferences.Editor editor = preferencias.edit();

        ImageButton cab1 = (ImageButton) findViewById(R.id.caballo1);
        ImageButton cab2 = (ImageButton) findViewById(R.id.caballo2);
        ImageButton cab3 = (ImageButton) findViewById(R.id.caballo3);
        ImageButton cab4 = (ImageButton) findViewById(R.id.caballo4);
        if (nivel.equals("1")) {
            cab1.setVisibility(View.INVISIBLE);
            cab4.setVisibility(View.INVISIBLE);
        }
        ImageButton volver = (ImageButton) findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
        if (countRondas[0] < 5) {

            countRondas[0]= countRondas[0]+1;
            editor.putInt("countRondas", countRondas[0]);
            editor.apply();

            cab1.setOnClickListener(new View.OnClickListener() {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.resopla);

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
                    Intent i = new Intent(getApplicationContext(), JugarRazasB.class);
                    startActivity(i);
                }
            });

            cab2.setOnClickListener(new View.OnClickListener() {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.relincho1);

                @Override
                public void onClick(View view) {
                    mp.start();
                    try {
                        sleep(3000);
                        mp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    countAciertos[0]++;
                    editor.putInt("countAciertos", countAciertos[0]);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(), JugarRazasB.class);
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
                        mp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent i = new Intent(getApplicationContext(), JugarRazasB.class);
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
                        mp.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent i = new Intent(getApplicationContext(), JugarRazasB.class);
                    startActivity(i);
                }
            });
        } else {
             if (countAciertos[0] > 2) {
            editor.putInt("countRondas", 1);
            editor.putInt("countAciertos", 0);
            editor.commit();
            Intent i = new Intent(getApplicationContext(), JugarRazasyPelajesB.class);
            startActivity(i);

             }else{
                 Intent i = new Intent(getApplicationContext(), MainActivity.class);
                 startActivity(i);

             }
        }
    }
}
