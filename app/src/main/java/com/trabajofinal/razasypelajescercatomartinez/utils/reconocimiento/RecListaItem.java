package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.trabajofinal.razasypelajescercatomartinez.R;

import java.util.ArrayList;

public class RecListaItem extends AppCompatActivity {

    private String nombre, imagen, texto;
    private ArrayList<Integer> sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_grilla_item);
    }
    public RecListaItem(String nombre, String imagen, ArrayList<Integer> sonido, String texto) {

        this.nombre = nombre;
        this.imagen = imagen;
        this.sonido = sonido;
        this.texto = texto;
    }

    public String getNombre() {

        return nombre;
    }
    public String getImagen() {

        return imagen;
    }
    public Drawable getImagenDrawable() {
        return getResources().getDrawable(getResources().getIdentifier(imagen,"drawable",getPackageName()));
    }

    public ArrayList<Integer> getSonido() {
        return sonido;
    }

    public String getTexto() {

        return texto;
    }
}
