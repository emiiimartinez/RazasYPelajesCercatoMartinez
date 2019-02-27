package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballosProvider;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.InteraccionManager;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.JugarIP;
import com.trabajofinal.razasypelajescercatomartinez.utils.interacciones.JugarPI;

import java.util.Random;

public class JugarActivity extends AppCompatActivity implements View.OnClickListener {

    // used by both interaction modes
    private CaballoModel caballoCorrecto;
    private String whatToLookFor, lastLookedFor;
    private ImageView volver;
    private InteraccionManager interaccionManager;
    private CaballosProvider caballosProvider;
    private AnimationDrawable confettiAnimation, trophyAnimation;
    private int rondas, aciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // reset
        resetRondasyAciertos();
        // layout according to the chosen interaction mode
        prepareLayout();
        // let's play!
        newGame();
    }



    public void prepareLayout(){
        if( playingWithBInteraction() ){
            setContentView(R.layout.activity_jugar_pi);
            interaccionManager = new JugarPI(this, playingLevel2());
        }else{
            setContentView(R.layout.activity_jugar_ip);
            interaccionManager = new JugarIP(this, playingLevel2());
        }
        // horses provider
        caballosProvider = new CaballosProvider(this);
        // home btn
    }

    public void resetRondasyAciertos() {
        rondas = 0;
        aciertos = 0;
    }


    public void startConfettiAnimation(){
        // confetti
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

   /* public void startTrophyAnimation() {
        startAnimation(R.id.trophyImageView, 1, 8, "rsz_copa_rotando");
    }
*/
    public void volver(View view){
        Log.d("!!!!GAME-FLOW", "volver");
        finish();
    }

    public void retry(View view){
        Log.d("!!!!GAME-FLOW", "retry");
        prepareLayout();
        // play again
        newGame();
    }

    public void next(View view){
        Log.d("!!!!GAME-FLOW", "next");
        // -> select 'playing RPJ'
        playRazasYPelajesJuntos();
        prepareLayout();
        // play again
        newGame();
    }


    @Override
    public void onClick(View view) {
        if(view == volver){
            findViewById(R.id.volver).setBackgroundResource(R.drawable.ic_home_click);
            finish();
        }
        else{
            interaccionManager.manageOnClick(view);
        }
    }

    public Boolean playingRazasYPelajesJuntos(){
        Integer minijuegoPref = getConfigSharedPrefs().getInt(getString(R.string.minijuego_pref_key), R.id.InteracARadBtn);
        return minijuegoPref == R.id.InteracBRadBtn;
    }
    public Boolean playingCruza(){
        Integer minijuegoPref = getConfigSharedPrefs().getInt(getString(R.string.minijuego_pref_key), R.id.InteracARadBtn);
        return minijuegoPref == R.id.InteracCRadBtn;
    }

    private Boolean playingWithBInteraction(){
        Integer interactionPref = getConfigSharedPrefs().getInt(getString(R.string.interaction_pref_key), R.id.InteracARadBtn);
        Log.d("!!!!!!!!!!!!INTERACTION", "playingWithBInteraction? " + String.valueOf(interactionPref == R.id.InteracBRadBtn));
        return interactionPref == R.id.InteracBRadBtn;
    }

    public Boolean listeningToFemAudio(){
        Resources res = getResources();
        Boolean femAudioSwitchPref = getConfigSharedPrefs().getBoolean(getString(R.string.fem_audio_pref_key), res.getBoolean(R.bool.pref_default_audio));
        return  femAudioSwitchPref;
    }

    private Boolean playingLevel2(){
        Resources res = getResources();
        Boolean level2SwitchPref = getConfigSharedPrefs().getBoolean(getString(R.string.level2_pref_key), res.getBoolean(R.bool.pref_default_nivel));
        return  level2SwitchPref;
    }

   private void playGame(Integer game){
        SharedPreferences.Editor editor = getConfigSharedPrefs().edit();
        editor.putInt(getString(R.string.minijuego_pref_key), game);
        editor.commit();
    }

    public void playRazasYPelajesJuntos(){
        playGame(R.id.InteracBRadBtn);
    }

    private SharedPreferences getConfigSharedPrefs(){
        return getSharedPreferences(getString(R.string.config_preferences),Context.MODE_PRIVATE);
    }

    private String randomRazaOPelaje() {
        Random random = new Random();
        String[] temp = new String[]{caballoCorrecto.getRaza(), caballoCorrecto.getPelaje()};
        return temp[random.nextInt(temp.length)];
    }

    private void horseToFind(){
        caballoCorrecto = caballosProvider.randomHorse();
        // si se trata del juego RPJ, pongo directamente como 'a buscar' al nombre de la foto del caballo
        // random, sino, digo bueno, vamos a buscar o bien la raza o el pelaje asociado a la foto
        if(playingRazasYPelajesJuntos()) {
            whatToLookFor = caballoCorrecto.getName();
        }else{
            whatToLookFor  = randomRazaOPelaje();
        }
    }

    private void determineHorseToFind(){
        horseToFind();
        while( whatToLookFor.equals(lastLookedFor) ){
            horseToFind();
        }
        lastLookedFor = whatToLookFor;
    }

    private Boolean searchingForType(){
        return caballosProvider.isAHorseType(whatToLookFor);
    }

    private Boolean searchingForHairType(){
        return  caballosProvider.isAHorseHairType(whatToLookFor);
    }

    private Boolean searchingForFullName(){
        return (!searchingForType() && !searchingForHairType());
    }

    public void incrementAssertions(){
        aciertos++;
    }

    public Boolean gameWon(){
        return aciertos>=3 && rondas==5;
    }

    public Boolean isImpossibleToWin(){
        return aciertos<3 && rondas==5;
    }

    public void logdGameFlow(){
        Log.d("!!!!GAME-FLOW", "ROUNDS:"+rondas+" ASSERTIONS:"+aciertos);
    }

    public void makeToast(String string){
        Toast toast = Toast.makeText(this, string, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 40);
        toast.show();
    }

   /* public void showTrophy() {
        setContentView(R.layout.activity_game_trophy);
        // home btn
        homeImgView = findViewById(R.id.homeImgView);
        homeImgView.setOnClickListener(this);
    }
*/
    public void showRetryLayout() {
        setContentView(R.layout.activity_jugar_playagain);
    }

    public void showNextLayout() {
        setContentView(R.layout.activity_jugar_next);
    }

    public void newGame() {
        // new round
        rondas++;
        // reset tags
        interaccionManager.resetViewsTags();
        // determine horse to find
        determineHorseToFind();
        // tell the interactionM the answer data
        interaccionManager.informAboutWhatToLookFor(caballoCorrecto, whatToLookFor, searchingForType(),
                searchingForHairType(), searchingForFullName(), listeningToFemAudio());
        // show in ui
        interaccionManager.showWhatToLookFor();
        // populate img views with random imgs
        interaccionManager.showPossibleAnswers();
        // put a random img view with the answer img ONLY if it isn't shown yet
        interaccionManager.putAnswerInGame();
    }

    /*private SharedPreferences getEnableGamesSharedPrefs(){
        return getSharedPreferences(String.valueOf(R.string.enable_games_preferences), Context.MODE_PRIVATE);
    }

    public void enableRPJ() {
        enableGame(getString(R.string.RPJenabled_pref_key));
    }

    private void enableGame(String key){
        Boolean RPJenabled = getEnableGamesSharedPrefs().getBoolean(getString(R.string.RPJenabled_pref_key), false);

        if (!RPJenabled) {
            Log.d("!!!!GAME-FLOW", "enableRPJ");
            SharedPreferences.Editor editor = getEnableGamesSharedPrefs().edit();
            editor.putBoolean(key, true);
            editor.apply();
            editor.commit();
        }
    }*/
}
