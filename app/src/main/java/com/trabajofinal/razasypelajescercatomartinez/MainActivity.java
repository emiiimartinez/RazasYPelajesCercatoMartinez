package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferencias =getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String formatoR=preferencias.getString("formatoDeReconocimiento","no hay nada");
        String formasDeI=preferencias.getString("formasDeInteraccion","no hay nada");
        boolean cRazasyPelajes=preferencias.getBoolean("checkRazasyPelajes",false);
        boolean cCruza=preferencias.getBoolean("checkCruza",false);
        String nivel=preferencias.getString("nivel","noy hay nada");
        String audio=preferencias.getString("audio","no hay nada");

        if (formasDeI == "A"){
//jugar razas A
            Button jugarr = findViewById(R.id.jugarr);
            jugarr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in= new Intent(getApplicationContext(),JugarRazasA.class);
                    startActivity(in);            }
            });
        }
        else {
//jugar razas B
            Button jugarr = findViewById(R.id.jugarr);
            jugarr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in= new Intent(getApplicationContext(),JugarRazasB.class);
                    startActivity(in);            }
            });
        }

//jugar cruzas
        Button jugarc = findViewById(R.id.jugarc);
        jugarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),JugarCruza.class);
                startActivity(i);            }
        });
//reconocimiento
       /* ImageButton rec = findViewById(R.id.reconocimiento);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),Configuracion.class);
                startActivity(i);            }
        });*/
//Configuracion
        ImageButton conf = findViewById(R.id.configuracion);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),Configuracion.class);
                startActivity(i);            }
        });
//about
        ImageButton about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),About.class);
                startActivity(i);            }
        });

        //FragmentManager FM = getFragmentManager();
        //FragmentTransaction FT = FM.beginTransaction();
        //Fragment fragment = new FragmentUNO();
        //FT.add(R.id.fragment_container, fragment);
        //FT.commit();
    }
}
