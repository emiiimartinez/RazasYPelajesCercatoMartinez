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
        SharedPreferences preferencias = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        final String formatoR = preferencias.getString("formatoDeReconocimiento", "lista");
        final String formasDeI = preferencias.getString("formasDeInteraccion", "A");
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putInt("countRondas", 1);
        editor.putInt("countAciertos", 0);
        editor.commit();
        if (formatoR.equals("lista")) {
            //reconocimiento
            ImageButton rec = findViewById(R.id.reconocimiento);
            rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ReconocimientoLista.class);
                    startActivity(i);
                }
            });
        } else {
            //reconocimiento
            ImageButton rec = findViewById(R.id.reconocimiento);
            rec.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(), ReconocimientoGrilla.class);
                    startActivity(i);
                }
            });
        }

//Configuracion
        ImageButton conf = findViewById(R.id.configuracion);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Configuracion.class);
                startActivity(i);
            }
        });
//about
        ImageButton about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
            }
        });

        //jugar
        ImageButton jugar = findViewById(R.id.jugar);
        if (formasDeI.equals("A")) {
//jugar razas A
            jugar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(getApplicationContext(), JugarRazasA.class);
                    startActivity(in);
                }
            });
        } else {
            if (formasDeI.equals("B")) {

//jugar razas B
                jugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent in = new Intent(getApplicationContext(), JugarRazasB.class);
                        startActivity(in);
                    }
                });
            } else {

                //jugar cruzas
                jugar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), JugarCruza.class);
                        startActivity(i);
                    }
                });
            }
        }

        //FragmentManager FM = getFragmentManager();
        //FragmentTransaction FT = FM.beginTransaction();
        //Fragment fragment = new FragmentUNO();
        //FT.add(R.id.fragment_container, fragment);
        //FT.commit();
    }
}
