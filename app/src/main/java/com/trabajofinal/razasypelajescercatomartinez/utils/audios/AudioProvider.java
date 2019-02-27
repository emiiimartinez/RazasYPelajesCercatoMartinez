package com.trabajofinal.razasypelajescercatomartinez.utils.audios;

import com.trabajofinal.razasypelajescercatomartinez.R;

import java.util.HashMap;
import java.util.Map;

public enum AudioProvider {
    INSTANCE;

    private final Map soundsMap;

    AudioProvider() {
        this.soundsMap = new HashMap();
        Map femMap = new HashMap();
        Map maleMap = new HashMap();
        // audio fem razas y pelajes por separado
        femMap.put("Petiso Argentino",R.raw.f_petiso_argentino);
        femMap.put("Rosillo", R.raw.f_rosillo);
        femMap.put("Mestizo Cruza Arabe", R.raw.f_mestizo_cruza_arabe);
        femMap.put("Alazan Tostado", R.raw.f_alazan_tostado);
        femMap.put("Spc", R.raw.f_sangre_pura_de_carrera);
        femMap.put("Mestizo", R.raw.f_mestizo);
        femMap.put("Overo Zaino", R.raw.f_overo_zaino);
        femMap.put("Alazan Ruano", R.raw.f_alazan_ruano);
        femMap.put("Blanco", R.raw.f_blanco);
        femMap.put("Mestizo Qh Con Criollo", R.raw.f_mestizo_cuarto_de_milla);
        femMap.put("Overo Azulejo", R.raw.f_overo_azulejo);
        femMap.put("Alazan", R.raw.f_alazan);
        femMap.put("Tobiano", R.raw.f_tobiano);
        femMap.put("Tordillo Canela", R.raw.f_tordillo_canela);
        femMap.put("Cuarto De Milla", R.raw.f_cuarto_de_milla);
        femMap.put("Bayo", R.raw.f_bayo);
        femMap.put("Tordillo Moro", R.raw.f_tordillo);
        femMap.put("Tordillo", R.raw.f_tordillo);
        femMap.put("Overo Rosado", R.raw.f_overo_rosado);
        femMap.put("Moro", R.raw.f_mora);
        femMap.put("Alazan Pintado", R.raw.f_alazan_pintado);
        femMap.put("Zaino", R.raw.f_zaino);
        femMap.put("Criollo", R.raw.f_criollo);
        femMap.put("Picaso", R.raw.f_picaso);
        femMap.put("Silla Argentino", R.raw.f_silla_argentino);
        femMap.put("Bayo Gateado", R.raw.f_bayo_gateado);
        femMap.put("Zaino Oscuro", R.raw.f_zaino_oscuro);
        // audio male razas y pelajes por separado
        maleMap.put("Petiso Argentino",R.raw.m_petiso_argentino);
        maleMap.put("Rosillo", R.raw.f_rosillo);
        maleMap.put("Mestizo Cruza Arabe", R.raw.m_mestizo_cruza_arabe);
        maleMap.put("Alazan Tostado", R.raw.f_alazan_tostado);
        maleMap.put("Spc", R.raw.m_spc);
        maleMap.put("Mestizo", R.raw.m_mestizo);
        maleMap.put("Overo Zaino", R.raw.f_overo_zaino);
        maleMap.put("Alazan Ruano", R.raw.horse_f_alazan_ruano);
        maleMap.put("Blanco", R.raw.f_blanco);
        maleMap.put("Mestizo Qh Con Criollo", R.raw.m_mestizo_cuarto_de_milla);
        maleMap.put("Overo Azulejo", R.raw.horse_m_overo_azulejo);
        maleMap.put("Alazan", R.raw.f_alazan);
        maleMap.put("Tobiano", R.raw.horse_m_tobiano);
        maleMap.put("Tordillo Canela", R.raw.horse_m_tordillo_canela);
        maleMap.put("Cuarto De Milla", R.raw.m_cuarto_de_milla);
        maleMap.put("Bayo", R.raw.horse_m_bayo);
        maleMap.put("Tordillo Moro", R.raw.f_tordillo);
        maleMap.put("Tordillo", R.raw.f_tordillo);
        maleMap.put("Overo Rosado", R.raw.f_overo_rosado);
        maleMap.put("Moro", R.raw.f_mora);
        maleMap.put("Alazan Pintado", R.raw.f_alazan_pintado);
        maleMap.put("Zaino", R.raw.f_zaino);
        maleMap.put("Criollo", R.raw.m_criollo);
        maleMap.put("Picaso", R.raw.f_picaso);
        maleMap.put("Silla Argentino", R.raw.m_silla_argentino);
        maleMap.put("Bayo Gateado", R.raw.f_bayo_gateado);
        maleMap.put("Zaino Oscuro", R.raw.f_zaino_oscuro);
        // add
        soundsMap.put("success", R.raw.game_relincho);
        soundsMap.put("error", R.raw.game_resoplido);
        soundsMap.put("f", femMap);
        soundsMap.put("m", maleMap);
    }

    public Integer getSoundAt(String key){
        return returnSound( (Integer) soundsMap.get(key) );
    }

    public Integer getFemSoundAt(String key){
        return returnSound(  (Integer)((Map)soundsMap.get("f")).get(key) );
    }

    public Integer getMaleSoundAt(String key){
        return returnSound( (Integer)((Map)soundsMap.get("m")).get(key) );
    }

    public Integer returnSound(Integer sound){
        if (sound == null) {
            return (Integer) soundsMap.get("error");
        }
        return sound;
    }
}
