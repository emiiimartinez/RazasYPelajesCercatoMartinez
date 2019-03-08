package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;

import java.util.ArrayList;
import java.util.List;

public class RecLista extends AppCompatActivity {

    private Button volver;
    protected List lista=new ArrayList();

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
        prepareView();

    }
    private SharedPreferences getConfigSharedPrefs() {
        return getSharedPreferences(getString(R.string.config_preferences), Context.MODE_PRIVATE);
    }

    public Boolean listeningToFemAudio() {
        Resources res = getResources();
        Boolean femAudioSwitchPref = getConfigSharedPrefs().getBoolean(getString(R.string.fem_audio_pref_key), res.getBoolean(R.bool.pref_default_audio));
        return femAudioSwitchPref;
    }

    protected void agregarLista(String nombre, String img, ArrayList<Integer> sonido, String texto) {
        lista.add( new RecListaItem(nombre, img, sonido, texto) );
    }

    protected void cargarItems() {
        lista = new ArrayList<>();
        CaballosProvider provider = new CaballosProvider(this);
        List<CaballoModel> caballos = provider.getHorsesList();
        for (int i = 0; i < caballos.size(); i++) {
            CaballoModel caballo = caballos.get(i);
            String img =caballo.getImagen();
            String nombre = caballo.getName();
            ArrayList<Integer> sonido;
            if (listeningToFemAudio()){
                sonido = caballo.getFemSounds();
            } else {
                sonido = caballo.getMaleSounds();
            }
            agregarLista(nombre, img, sonido, nombre);
        }
    }
    public void prepareView() {

        cargarItems();
        ListView listView = findViewById(R.id.lista);
        RecListaAdapter listAdapater = new RecListaAdapter(this, R.layout.activity_rec_lista_item, lista);
        listView.setAdapter(listAdapater);
    }
}
