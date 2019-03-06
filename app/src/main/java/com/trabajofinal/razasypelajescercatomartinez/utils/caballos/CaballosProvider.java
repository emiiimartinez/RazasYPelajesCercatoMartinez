package com.trabajofinal.razasypelajescercatomartinez.utils.caballos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trabajofinal.razasypelajescercatomartinez.utils.JsonManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioProvider;

public class CaballosProvider extends AppCompatActivity {
    private List<CaballoModel> horsesList;
    private List<CaballoModel> potrillosList;
    private AppCompatActivity context;


    public CaballosProvider(Context applicationContext) {
        this.context = (AppCompatActivity) applicationContext;
        this.horsesList = new ArrayList();
        this.potrillosList = new ArrayList();
        String json = JsonManager.INSTANCE.getJSONFromRaw(R.raw.horses, this.context);
        String jsonp = JsonManager.INSTANCE.getJSONFromRaw(R.raw.horses_cruza, this.context);
        CaballoModel horse;
        try {
            JSONArray horsesJSON = new JSONArray(json);
            JSONArray potrillosJSON = new JSONArray(jsonp);
            for (int i = 0; i < horsesJSON.length(); i++) {
                JSONObject horseJSON = horsesJSON.getJSONObject(i);
                String raza = horseJSON.getString("breed");
                String pelaje = horseJSON.getString("fur");
                String imagen = horseJSON.getString("photo");
                horse = new CaballoModel(raza, pelaje,imagen);
                Map audio = new HashMap();
                audio.put("f", new Integer[]{
                        AudioProvider.INSTANCE.getFemSoundAt(raza),
                        AudioProvider.INSTANCE.getFemSoundAt(pelaje)
                });
                audio.put("m", new Integer[]{
                        AudioProvider.INSTANCE.getMaleSoundAt(raza),
                        AudioProvider.INSTANCE.getMaleSoundAt(pelaje)
                });
                horse.setAudio(audio);
                horsesList.add(horse);
            }
            for (int i = 0; i < potrillosJSON.length(); i++) {
                JSONObject potrilloJSON = potrillosJSON.getJSONObject(i);
                String nombre = potrilloJSON.getString("potrillo");
                String padres = potrilloJSON.getString("padres");
                horse = new CaballoModel(nombre,padres);
                Map audio = new HashMap();
                potrillosList.add(horse);
            }
        } catch (JSONException e) { e.printStackTrace(); }

    }

    public CaballoModel randomHorse(){
        Collections.shuffle(horsesList);
        return horsesList.get(0);
    }
    public CaballoModel randomHorseCruza(){
        Collections.shuffle(potrillosList);
        return potrillosList.get(0);
    }

    public Boolean isAHorseRaza(String word){
        for (CaballoModel horse : horsesList) {
            if ( horse.getRaza().equals(word) ) {
                return true;
            }
        }
        return false;
    }

    public Boolean isAHorsePelaje(String word) {
        for (CaballoModel horse : horsesList) {
            if ( horse.getPelaje().equals(word) ) {
                return true;
            }
        }
        return false;
    }

    public List<CaballoModel> getHorsesList() {
        return horsesList;
    }
    public List<CaballoModel> getPotrillosList() {
        return potrillosList;
    }


}
