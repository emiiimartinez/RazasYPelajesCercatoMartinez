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
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;

import java.util.List;

public class RecGrilla extends AppCompatActivity {
    private CaballoModel caballo;
    private CaballosProvider caballosProvider;
    private List<CaballoModel> caballos;
    private Button volver;
    private Button sonido;
    private ImageView iv;
    private TextView raza;
    private String pelaje;
    private String aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_grilla);

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

        iv= findViewById(R.id.image1);
        caballo = caballos.get(1);
        raza = findViewById(R.id.textView1);
        pelaje = caballo.getPelaje();
        aux = caballo.getRaza();
        aux= aux +" "+ pelaje;
        raza.setText(aux);
        String i = caballo.getImagen();
        Drawable draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

    }

    public void sonar(Integer i){
        new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caballo.getAudio();
            }
        };
    }
}
