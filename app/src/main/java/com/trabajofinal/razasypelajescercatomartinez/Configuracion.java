package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        this.cargarDatos();
        Button aceptar= (Button)findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferencias2 =getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                String formatoDeReconocimiento="";
                String formasDeInteraccion="";
                boolean cRazasyPelajes;
                boolean cCruza;
                String nivel="";
                String audio="";
                RadioButton lista=(RadioButton) findViewById(R.id.lista);
                RadioButton grilla=(RadioButton) findViewById(R.id.grilla);
                RadioButton iC=(RadioButton) findViewById(R.id.imagenimagen);
                RadioButton iA=(RadioButton) findViewById(R.id.imagenpalabra);
                RadioButton iB=(RadioButton) findViewById(R.id.palabraimagen);
                CheckBox checkRazasyPelajes = (CheckBox) findViewById(R.id.razasypelajes);
                CheckBox checkCruza =(CheckBox) findViewById(R.id.cruzas);
                Switch sNivel=(Switch) findViewById(R.id.nivel);
                Switch sAudio=(Switch) findViewById(R.id.audio);
                if(lista.isChecked()){
                    formatoDeReconocimiento="lista";
                }
                if(grilla.isChecked()){
                    formatoDeReconocimiento="grilla";
                }
                if (iA.isChecked()){
                    formasDeInteraccion="A";
                }
                if (iB.isChecked()){
                    formasDeInteraccion="B";
                }
                if (iC.isChecked()){
                    formasDeInteraccion="C";
                }
                if (checkRazasyPelajes.isChecked()){
                    cRazasyPelajes=true;
                }
                else{
                    cRazasyPelajes=false;
                }
                if (checkCruza.isChecked()){
                    cCruza=true;
                }
                else{
                    cCruza=false;
                }
                if (sNivel.isChecked()){
                    nivel="2";
                }
                else{
                    nivel="1";
                }
                if(sAudio.isChecked()){
                    audio="femenino";
                }
                else{
                    audio="masculino";
                }
                SharedPreferences.Editor editor= preferencias2.edit();
                editor.putString("formatoDeReconocimiento",formatoDeReconocimiento);
                editor.putString("formasDeInteraccion",formasDeInteraccion);
                editor.putBoolean("checkRazasyPelajes",cRazasyPelajes);
                editor.putBoolean("checkCruza",cCruza);
                editor.putString("nivel",nivel);
                editor.putString("audio",audio);
                editor.commit();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void cargarDatos(){
        RadioButton lista=(RadioButton) findViewById(R.id.lista);
        RadioButton grilla=(RadioButton) findViewById(R.id.grilla);
        RadioButton iA= (RadioButton) findViewById(R.id.imagenpalabra);
        RadioButton iB= (RadioButton) findViewById(R.id.palabraimagen);
        RadioButton iC= (RadioButton) findViewById(R.id.imagenimagen);
        CheckBox checkRazasyPelajes = (CheckBox) findViewById(R.id.razasypelajes);
        CheckBox checkCruza =(CheckBox) findViewById(R.id.cruzas);
        Switch sNivel=(Switch) findViewById(R.id.nivel);
        Switch sAudio=(Switch) findViewById(R.id.audio);
        SharedPreferences preferencias =getSharedPreferences("preferencias",Context.MODE_PRIVATE);
        String formatoR=preferencias.getString("formatoDeReconocimiento","no hay nada");
        String formasDeI=preferencias.getString("formasDeInteraccion","no hay nada");
        boolean cRazasyPelajes=preferencias.getBoolean("checkRazasyPelajes",false);
        boolean cCruza=preferencias.getBoolean("checkCruza",false);
        String nivel=preferencias.getString("nivel","noy hay nada");
        String audio=preferencias.getString("audio","no hay nada");
        if (formatoR == "grilla"){
            grilla.setChecked(true);
        }
        else {
            lista.setChecked(true);
        }
        if (cCruza){
            checkCruza.setChecked(true);
        }
        else {
            checkRazasyPelajes.setChecked(true);
        }
        if (cRazasyPelajes){
            checkCruza.setChecked(true);
        }
        else {
            checkRazasyPelajes.setChecked(true);
        }
        if (formasDeI == "C"){
            iC.setChecked(true);
        }
        else {
            if (formasDeI == "B"){
                iB.setChecked(true);
            }
            else {
                iA.setChecked(true);
            }
        }
        if (nivel=="2"){
            sNivel.setChecked(true);
        }
        else{
            sNivel.setChecked(false);
        }
        if (audio=="femenino"){
            sAudio.setChecked(true);
        }
        else{
            sAudio.setChecked(false);
        }
    }
    public void onRadioButtonClicked(View view) {
    }
}
