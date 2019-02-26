package com.trabajofinal.razasypelajescercatomartinez.utils.interacciones;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.trabajofinal.razasypelajescercatomartinez.JugarActivity;
import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballosProvider;
import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioPlayer;
import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class InteraccionManager {
    protected JugarActivity context;
    protected String whatToLookFor;
    protected CaballoModel caballoAcierto;
    protected Boolean searchingForRaza;
    protected Boolean searchingForPelaje;
    protected Boolean searchingForName;
    protected CaballosProvider caballosProvider;
    protected Boolean listeningToFemAudio;
    protected Boolean playingLevel2;

    public InteraccionManager(JugarActivity context, Boolean playingLevel2) {
        this.context = context;
        this.caballoAcierto = new CaballoModel();
        //this.whatToLookFor = "";
        this.searchingForRaza = false;
        this.searchingForPelaje = false;
        this.searchingForName = false;
        this.listeningToFemAudio = false;
        this.playingLevel2 = playingLevel2;
        this.caballosProvider = new CaballosProvider(this.context);
    }


    protected void setViewListItemsOnClickHandler(List<? extends View> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setOnClickListener(this.context);
        }
    }

    public void resetViewsTags(List<? extends View> list){
        for (int i = 0; i < list.size(); i++) {
            View view = list.get(i);
            view.setTag(new CaballoModel());
        }
    }

    public void informAboutWhatToLookFor(CaballoModel horseToFind, String whatToLookFor, Boolean searchingForType,
                                         Boolean searchingForHairType, Boolean searchingForFullName, Boolean listeningToFemAudio){
        this.caballoAcierto = horseToFind;
        this.whatToLookFor = whatToLookFor;
        this.searchingForRaza = searchingForType;
        this.searchingForPelaje = searchingForHairType;
        this.searchingForName = searchingForFullName;
        this.listeningToFemAudio = listeningToFemAudio;
    }

    public void showWhatToLookFor(){
        //Log.d("!!!WHAT-TO-LOOK-FOR", whatToLookFor);
    }

    public void showPossibleAnswers(List<? extends View> views) {
        resetSoundImageToRegular();
        for (int i = 0; i < views.size(); i++) {
            CaballoModel randomHorse = caballosProvider.randomHorse();
            // we dont wanna have the same horse attribute twice
            while(this.isAlreadyInViews( randomHorse, views) ){
                randomHorse = caballosProvider.randomHorse();
            }
            views.get(i).setTag(randomHorse);
            manageViewsListItem(randomHorse, i);
        }
    }

    protected boolean isAlreadyInViews(CaballoModel horse, List<? extends View> views) {
        boolean cond = false;
        for (View view : views) {
            if ( isAlreadyInViewsCondition(horse, view) ) {
                cond = true;
                break;
            }
        }
        return cond;
    }

    protected Boolean isAlreadyInViewsCondition(CaballoModel horse, View view){
        if( searchingForRaza ){
            return (((CaballoModel)view.getTag()).getRaza())
                    .equals(horse.getRaza());
        }else if( searchingForPelaje ){
            return (((CaballoModel)view.getTag()).getPelaje())
                    .equals(horse.getPelaje());
        }
        return (((CaballoModel)view.getTag()).getName())
                .equals(horse.getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void validateView(){
        ArrayList<Integer> sounds = new ArrayList<>();
        if ( viewValidationCondition() ){
            sounds.add(AudioProvider.INSTANCE.getSoundAt("success"));
            // assertions ++
            this.context.incrementAssertions();
        } else {
            sounds.add(AudioProvider.INSTANCE.getSoundAt("error"));
        }
        AudioPlayer.wannaPlaySound(sounds, this.context);
        this.context.logdGameFlow();
        // determine what to do
        determineWhatToDo();
    }

    protected void determineWhatToDo() {
        if (this.context.gameWon()){
            // inform the user
            this.context.makeToast("¡Ganaste!");
            // reset
            this.context.resetRondasyAciertos();
            // if RP}
            if(!this.context.playingRazasYPelajesJuntos()){
                // enable RPJ in settings
              //  this.context.enableRPJ();
                // ask to retry game or play next
                this.context.showNextLayout();
                // confetti
               // this.context.startConfettiAnimation();
            }else{
                if(!this.context.playingCruza()){
                    this.context.showNextLayout();
                }else {
                    // this.context.showTrophy();
                    // this.context.startTrophyAnimation();
                }
            }
        }else if (this.context.isImpossibleToWin()){
            // inform user
            this.context.makeToast("Obtuviste menos de 3 respuestas correctas en 5 intentos.");
            // reset
            this.context.resetRondasyAciertos();
            // ask to go home or retry game
            this.context.showRetryLayout();
        }else {
            // player has chances to win -> play again
            this.context.newGame();
        }
    }

    protected  Boolean viewValidationCondition( View view){
        return ( ((CaballoModel)view.getTag()).getName() )
                .contains(whatToLookFor);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void playHorseSound(CaballoModel horse) {
        ArrayList<Integer> sounds = new ArrayList<>();

        if(listeningToFemAudio){
            if( searchingForRaza ){
                sounds.add( horse.getFemRazaAudio()) ;
            }else if( searchingForPelaje ){
                sounds.add( horse.getFemPelajeAudio() );
            }else if( searchingForName ){
                sounds = horse.getFemSounds() ;
            }
        }else{
            if( searchingForRaza ){
                sounds.add( horse.getMascRazaAudio()) ;
            }else if( searchingForPelaje ){
                sounds.add( horse.getMascPelajeAudio() );
            }else if( searchingForName ){
                sounds = horse.getMaleSounds() ;
            }
        }

        AudioPlayer.wannaPlaySound(sounds, this.context);
    }


    protected abstract void initPossibleAnswersContainersArray();

    public abstract void resetViewsTags();

    public abstract void showPossibleAnswers();

    protected abstract void resetSoundImageToRegular();

    protected abstract void manageViewsListItem(CaballoModel horseImgId, int randomHorseImgId);

    public abstract void putAnswerInGame();

    public abstract void manageOnClick(View view);

    protected abstract Boolean viewValidationCondition();

    // test
    protected void setImageResource(ImageView imageView, String img){
        if(img != ""){
            Drawable draw = context.getResources().getDrawable(context.getResources().getIdentifier(img,"drawable",context.getPackageName()));
            imageView.setImageDrawable(draw);
        }else{
            imageView.setImageResource(R.drawable.horse_no_image);
        }
    }
}