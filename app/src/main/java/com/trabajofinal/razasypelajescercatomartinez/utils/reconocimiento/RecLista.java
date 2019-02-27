package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballosProvider;

import java.util.List;

public class RecLista extends AppCompatActivity {
    private CaballoModel caballo;
    private CaballosProvider caballosProvider;
    private List<CaballoModel> caballos;
    private ImageView iv;
    private TextView raza;
    private TextView pelaje;
    private TextView texto;
    private String lorem;
    private Button audio;
    private Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_lista);

        volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        caballosProvider = new CaballosProvider(this);
        caballos= caballosProvider.getHorsesList();

        lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

        iv= findViewById(R.id.image1);
        raza = findViewById(R.id.texto1);
        pelaje = findViewById(R.id.Texto12);
        texto = findViewById(R.id.Texto13);
        caballo = caballos.get(1);
        String i = caballo.getImagen();
        Drawable draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);
        raza.setText(caballo.getRaza());
        pelaje.setText(caballo.getPelaje());
        texto.setText(lorem);


        iv= findViewById(R.id.image2);
        caballo = caballos.get(2);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

        iv= findViewById(R.id.image3);
        caballo = caballos.get(3);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

        iv= findViewById(R.id.image4);
        caballo = caballos.get(4);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

        iv= findViewById(R.id.image5);
        caballo = caballos.get(5);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);
    }
}
