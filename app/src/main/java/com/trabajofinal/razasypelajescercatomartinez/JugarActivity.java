package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.InteraccionManager;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.JugarII;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.JugarIP;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.JugarPI;

import java.util.Random;

public class JugarActivity extends AppCompatActivity implements View.OnClickListener {

    private CaballoModel caballoCorrecto;
    private String caballoAEncontrar, caballoAnterior;
    private ImageView volver;
    private InteraccionManager interaccionManager;
    private CaballosProvider caballosProvider;
    private AnimationDrawable confettiAnimation, trophyAnimation;
    private int rondas, aciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resetRondasyAciertos();
        minijuego();
        newGame();
    }

    public void minijuego() {
        if (playingRazasYPelajesJuntos()) {
            prepareLayout();
        } else {
            if (playingCruza()) {
                prepareCruza();
            } else {
                prepareLayout();
            }
        }
    }

    public void prepareCruza() {
        setContentView(R.layout.activity_jugar_ii);
        interaccionManager = new JugarII(this, playingLevel2());
        caballosProvider = new CaballosProvider(this);
    }

    public void prepareLayout() {
        if (playingWithBInteraction()) {
            setContentView(R.layout.activity_jugar_pi);
            interaccionManager = new JugarPI(this, playingLevel2());
        } else {
            setContentView(R.layout.activity_jugar_ip);
            interaccionManager = new JugarIP(this, playingLevel2());
        }
        caballosProvider = new CaballosProvider(this);
    }

    public void resetRondasyAciertos() {
        rondas = 0;
        aciertos = 0;
    }


    public void startConfettiAnimation() {
        ImageView confettiImgView = (ImageView) findViewById(R.id.confettiImageView);
        confettiImgView.setBackgroundResource(R.drawable.anim_confetti);
        confettiAnimation = (AnimationDrawable) confettiImgView.getBackground();
        confettiAnimation.setOneShot(true);
        confettiAnimation.start();
    }

    private void startAnimation(int imgViewId, int base, int top, String baseName) {
        final AnimationDrawable anim;
        ImageView img = findViewById(imgViewId);
        anim = new AnimationDrawable();
        for (int i = base; i <= top; i += 1) {
            String name = baseName + i;
            anim.addFrame(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName())), 250);
            System.gc();
        }
        anim.setOneShot(false);
        img.setImageDrawable(anim);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                anim.start();
            }
        };

        img.post(run);
    }

     public void startTrophyAnimation() {
         startAnimation(R.id.trophyImageView, 1, 5, "rsz_copa_rotando");
     }

    public void volver(View view) {
        finish();
    }

    public void retry(View view) {
        minijuego();
        newGame();
    }

    public void next(View view) {
        if (playingRazasYPelajesJuntos()) {
            playCruza();
        } else {
            playRazasYPelajesJuntos();
        }
        minijuego();
        newGame();
    }


    @Override
    public void onClick(View view) {
        if (view == volver) {
            findViewById(R.id.volver).setBackgroundResource(R.drawable.ic_home_click);
            finish();
        } else {
            interaccionManager.manageOnClick(view);
        }
    }

    public Boolean playingRazasYPelajesJuntos() {
        Integer minijuegoPref = getConfigSharedPrefs().getInt(getString(R.string.minijuego_pref_key), R.id.RPRadioBtn);
        return (minijuegoPref == R.id.RPJRadioBtn);
    }

    public Boolean playingCruza() {
        Integer minijuegoPref = getConfigSharedPrefs().getInt(getString(R.string.minijuego_pref_key), R.id.RPJRadioBtn);
        return minijuegoPref == R.id.CRadioBtn;
    }

    private Boolean playingWithBInteraction() {
        Integer interactionPref = getConfigSharedPrefs().getInt(getString(R.string.interaction_pref_key), R.id.InteracARadBtn);
        return interactionPref == R.id.InteracBRadBtn;
    }

    public Boolean listeningToFemAudio() {
        Resources res = getResources();
        Boolean femAudioSwitchPref = getConfigSharedPrefs().getBoolean(getString(R.string.fem_audio_pref_key), res.getBoolean(R.bool.pref_default_audio));
        return femAudioSwitchPref;
    }

    private Boolean playingLevel2() {
        Resources res = getResources();
        Boolean level2SwitchPref = getConfigSharedPrefs().getBoolean(getString(R.string.level2_pref_key), res.getBoolean(R.bool.pref_default_nivel));
        return level2SwitchPref;
    }

    private void playGame(Integer game) {
        SharedPreferences.Editor editor = getConfigSharedPrefs().edit();
        editor.putInt(getString(R.string.minijuego_pref_key), game);
        editor.commit();
    }

    public void playRazasYPelajesJuntos() {
        playGame(R.id.RPJRadioBtn);
    }

    public void playCruza() {
        playGame(R.id.CRadioBtn);
    }
    public void playRazasYPelajes() {
        playGame(R.id.RPRadioBtn);
    }


    private SharedPreferences getConfigSharedPrefs() {
        return getSharedPreferences(getString(R.string.config_preferences), Context.MODE_PRIVATE);
    }

    private String randomRazaOPelaje() {
        Random random = new Random();
        String[] temp = new String[]{caballoCorrecto.getRaza(), caballoCorrecto.getPelaje()};
        return temp[random.nextInt(temp.length)];
    }

    private void horseToFind() {
       if(playingCruza()){
       caballoCorrecto = caballosProvider.randomHorseCruza();
       caballoAEncontrar = caballoCorrecto.getPadres();
       }
       else{ caballoCorrecto = caballosProvider.randomHorse();
        if (playingRazasYPelajesJuntos()) {
           caballoAEncontrar = caballoCorrecto.getName();
       } else {
           caballoAEncontrar = randomRazaOPelaje();
       }
       }

    }

    private void determineHorseToFind() {
        horseToFind();
        while (caballoAEncontrar.equals(caballoAnterior)) {
            horseToFind();
        }
        caballoAnterior = caballoAEncontrar;

    }

    private Boolean searchingForRaza() {
        return caballosProvider.isAHorseRaza(caballoAEncontrar);
    }

    private Boolean searchingForPelaje() {
        return caballosProvider.isAHorsePelaje(caballoAEncontrar);
    }

    private Boolean searchingForNombre() {
        return (!searchingForRaza() && !searchingForPelaje());
    }

    public void incrementAciertos() {
        aciertos++;
    }

    public Boolean gameWon() {
        return aciertos >= 3 && rondas == 5;
    }

    public Boolean gameLost() {
        return aciertos < 3 && rondas == 5;
    }

     public void showTrophy() {
         setContentView(R.layout.activity_game_trophy);
     }

    public void showRetryLayout() {
        setContentView(R.layout.activity_jugar_playagain);
    }

    public void showNextLayout() {
        setContentView(R.layout.activity_jugar_next);
    }
    public void setearCruza(){
        interaccionManager.busqueda(caballoCorrecto, caballoAEncontrar);
        interaccionManager.showWhatToLookFor();
        interaccionManager.showRespuestasCruza();
    }

    public void newGame() {
        rondas++;
        interaccionManager.resetViewsTags();
        determineHorseToFind();

        if(playingCruza()){
            setearCruza();
        } else {
            interaccionManager.busqueda(caballoCorrecto, caballoAEncontrar, searchingForRaza(),
                    searchingForPelaje(), searchingForNombre(), listeningToFemAudio());
            interaccionManager.showWhatToLookFor();
            interaccionManager.showRespuestasPosibles();
       }
        interaccionManager.putAnswerInGame();
    }


}
