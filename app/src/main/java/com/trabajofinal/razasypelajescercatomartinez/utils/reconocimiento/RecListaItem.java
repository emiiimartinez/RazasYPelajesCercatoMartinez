package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioPlayer;

import java.util.ArrayList;

public class RecListaItem extends AppCompatActivity {

    String nombre, imagen, texto, potrillo, padre;
    ArrayList<Integer> sonido;

    public RecListaItem(String nombre, String imagen, ArrayList<Integer> sonido, String texto) {

        this.nombre = nombre;
        this.imagen = imagen;
        this.sonido = sonido;
        this.texto = texto;
    }
    public RecListaItem(String potrillo, String padre) {

        this.potrillo = potrillo;
        this.padre = padre;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClickCaballo(View view) {

        ArrayList<Integer> sounds = getSonido() ;
        AudioPlayer.wannaPlaySound(sounds, this);
    }
}
