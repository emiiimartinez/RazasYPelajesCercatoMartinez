package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballosProvider;

import java.util.List;

public class RecGrilla extends AppCompatActivity {
    private CaballoModel caballo;
    private CaballosProvider caballosProvider;
    private List<CaballoModel> caballos;
    private Button volver;
    private ImageView iv;
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
        String i = caballo.getImagen();
        Drawable draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);


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

        iv= findViewById(R.id.image6);
        caballo = caballos.get(6);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

        iv= findViewById(R.id.image7);
        caballo = caballos.get(7);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

        iv= findViewById(R.id.image8);
        caballo = caballos.get(8);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);

        iv= findViewById(R.id.image9);
        caballo = caballos.get(9);
        i = caballo.getImagen();
        draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);
    }
}
