package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Intent;
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
//jugar razas
        Button jugarr = findViewById(R.id.jugarr);
        jugarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(getApplicationContext(),JugarRazas.class);
                startActivity(in);            }
        });
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
