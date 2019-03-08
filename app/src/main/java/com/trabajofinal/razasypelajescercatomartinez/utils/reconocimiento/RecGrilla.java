package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioPlayer;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;

import java.util.ArrayList;
import java.util.List;

public class RecGrilla extends AppCompatActivity {

    private Button volver;


    protected List list=new ArrayList();;

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
        prepareView();
    }

   /* public RecGrilla(RecoActivity context) {
        super(context);
    }*/
   private SharedPreferences getConfigSharedPrefs() {
        return getSharedPreferences(getString(R.string.config_preferences), Context.MODE_PRIVATE);
    }
   public Boolean listeningToFemAudio() {
       Resources res = getResources();
       Boolean femAudioSwitchPref = getConfigSharedPrefs().getBoolean(getString(R.string.fem_audio_pref_key), res.getBoolean(R.bool.pref_default_audio));
       return femAudioSwitchPref;
   }
    protected void agregarLista(String img, String nombre, ArrayList<Integer> sonido) {
        list.add( new RecGrillaItem(img, nombre, sonido) );
    }
    protected void agregarLista() {
        list = new ArrayList<>();
        CaballosProvider horsesProvider = new CaballosProvider(this);
        List<CaballoModel> caballos = horsesProvider.getHorsesList();
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
            agregarLista(img, nombre, sonido);
        }
    }
    public void prepareView() {

        agregarLista();
        GridView gridView = findViewById(R.id.grilla);
        RecGrillaAdapter customGridAdapter = new RecGrillaAdapter(this, R.layout.activity_rec_grilla_item, list);
        gridView.setAdapter(customGridAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClickCaballo(View view) {

        ArrayList<Integer> sounds = (ArrayList<Integer>) view.getTag() ;
        AudioPlayer.wannaPlaySound(sounds, this);
    }

}
