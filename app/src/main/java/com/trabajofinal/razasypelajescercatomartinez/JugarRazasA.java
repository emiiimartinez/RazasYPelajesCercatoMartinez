package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static java.lang.Thread.sleep;

public class JugarRazasA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar_razas_a);
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String nivel = preferencias.getString("nivel", "1");
        String audio = preferencias.getString("audio", "no hay nada");

        final int[] countRondas = {preferencias.getInt("countRondas", 1)};
        final int[] countAciertos = {preferencias.getInt("countAciertos", 0)};
        final SharedPreferences.Editor editor = preferencias.edit();
        Button op1 = findViewById(R.id.opcion1);
        Button op2 = findViewById(R.id.opcion2);
        Button op3 = findViewById(R.id.opcion3);
        Button op4 = findViewById(R.id.opcion4);
        if (nivel.equals("1")) {
            op1.setVisibility(View.INVISIBLE);
            op4.setVisibility(View.INVISIBLE);
        }
        ImageButton volver = findViewById(R.id.volver);
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

            op1.setOnClickListener(new View.OnClickListener() {
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
                    Intent i = new Intent(getApplicationContext(), JugarRazasA.class);
                    startActivity(i);
                }
            });

            op2.setOnClickListener(new View.OnClickListener() {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.relincho1);

                @Override
                public void onClick(View view) {
                    mp.start();
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countAciertos[0]++;
                    editor.putInt("countAciertos", countAciertos[0]);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(), JugarRazasA.class);
                    startActivity(i);
                }
            });

            op3.setOnClickListener(new View.OnClickListener() {
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
                    Intent i = new Intent(getApplicationContext(), JugarRazasA.class);
                    startActivity(i);
                }
            });


            op4.setOnClickListener(new View.OnClickListener() {
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
                    Intent i = new Intent(getApplicationContext(), JugarRazasA.class);
                    startActivity(i);
                }
            });

        } else {
            if (countAciertos[0] > 2) {
                editor.putInt("countRondas", 1);
                editor.putInt("countAciertos", 0);
                editor.commit();
                Intent i = new Intent(getApplicationContext(), JugarRazasYPelajesA.class);
                startActivity(i);

            }else{
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        }



    }
}
