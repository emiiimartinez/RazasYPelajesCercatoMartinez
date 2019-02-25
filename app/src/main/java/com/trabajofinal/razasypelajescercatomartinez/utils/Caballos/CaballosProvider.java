package com.trabajofinal.razasypelajescercatomartinez.utils.caballos;

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

public class CaballosProvider {
    private List<CaballoModel> horsesList;
    private AppCompatActivity context;

    public CaballosProvider(AppCompatActivity context) {
        this.context = context;
        this.horsesList = new ArrayList();
        String json = JsonManager.INSTANCE.getJSONFromRaw(R.raw.horses, this.context);
        CaballoModel horse;
        try {
            JSONArray horsesJSON = new JSONArray(json);
            for (int i = 0; i < horsesJSON.length(); i++) {
                JSONObject horseJSON = horsesJSON.getJSONObject(i);
                String raza = horseJSON.getString("breed");
                String pelaje = horseJSON.getString("fur");
                horse = new CaballoModel(raza, pelaje);
                Integer image = CaballosImagenProvider.INSTANCE.getImgAt(horse.getName());
                Map audio = new HashMap();
                audio.put("f", new Integer[]{
                        AudioProvider.INSTANCE.getFemSoundAt(raza),
                        AudioProvider.INSTANCE.getFemSoundAt(pelaje)
                });
                audio.put("m", new Integer[]{
                        AudioProvider.INSTANCE.getMaleSoundAt(raza),
                        AudioProvider.INSTANCE.getMaleSoundAt(pelaje)
                });
                horse.setImagen(image);
                horse.setAudio(audio);
                horsesList.add(horse);
            }
        } catch (JSONException e) { e.printStackTrace(); }

    }

    public CaballoModel randomHorse(){
        Collections.shuffle(horsesList);
        return horsesList.get(0);
    }

    public Boolean isAHorseType(String word){
        for (CaballoModel horse : horsesList) {
            if ( horse.getRaza().equals(word) ) {
                return true;
            }
        }
        return false;
    }

    public Boolean isAHorseHairType(String word) {
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

}
