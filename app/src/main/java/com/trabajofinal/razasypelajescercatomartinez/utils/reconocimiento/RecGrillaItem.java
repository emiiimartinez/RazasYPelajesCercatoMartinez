package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;

import java.util.ArrayList;

public class RecGrillaItem extends AppCompatActivity {
    String imagen;
    String nombre;
    ArrayList<Integer> sonido;

    public RecGrillaItem(String imagen, String nombre, ArrayList<Integer> sonido) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.sonido = sonido;
    }

    public String getImagen() {

        return imagen;
    }
    public Drawable getImagenDrawable() {
       return getResources().getDrawable(getResources().getIdentifier(this.imagen,"drawable",getPackageName()));
    }
    public String getNombre() {

        return nombre;
    }

    public ArrayList<Integer> getSonido() {
        return sonido;
    }
}
