package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballosProvider;

import java.util.List;

public class RecLista extends AppCompatActivity {
    private CaballoModel caballo;
    private CaballosProvider caballosProvider;
    private List<CaballoModel> caballos;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_lista);
        caballosProvider = new CaballosProvider(this);
        caballos= caballosProvider.getHorsesList();
        iv= findViewById(R.id.image1);
        caballo = caballos.get(1);
        String i = caballo.getImagen();
        Drawable draw = getResources().getDrawable(getResources().getIdentifier(i,"drawable",getPackageName()));
        iv.setImageDrawable(draw);
    }
}
