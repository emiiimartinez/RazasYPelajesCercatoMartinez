package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento.RecGrilla;
import com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento.RecLista;

public class ReconocimientoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences configPreferences = getSharedPreferences(getString(R.string.config_preferences), Context.MODE_PRIVATE);
        Integer id= configPreferences.getInt(getString(R.string.reco_view_mode_pref_key), R.id.listRadioBtn);
        if(id == R.id.listRadioBtn){
            Intent intent = new Intent(this, RecLista.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, RecGrilla.class);
            startActivity(intent);
        }
    }
}
