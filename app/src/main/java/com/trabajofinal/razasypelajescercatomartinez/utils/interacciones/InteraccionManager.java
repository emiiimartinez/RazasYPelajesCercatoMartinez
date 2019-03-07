package com.trabajofinal.razasypelajescercatomartinez.utils.interacciones;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.trabajofinal.razasypelajescercatomartinez.JugarActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;
import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioPlayer;
import com.trabajofinal.razasypelajescercatomartinez.utils.audios.AudioProvider;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public abstract class InteraccionManager {
    protected JugarActivity context;
    protected String whatToLookFor;
    protected CaballoModel caballoAcierto;
    protected Boolean searchingForRaza;
    protected Boolean searchingForPelaje;
    protected Boolean searchingForName;
    protected Boolean searchingForPotrillo;
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
        this.searchingForPotrillo=false;
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

    public void busqueda(CaballoModel horseToFind, String whatToLookFor, Boolean searchingForType,
                         Boolean searchingForHairType, Boolean searchingForFullName, Boolean listeningToFemAudio){
        this.caballoAcierto = horseToFind;
        this.whatToLookFor = whatToLookFor;
        this.searchingForRaza = searchingForType;
        this.searchingForPelaje = searchingForHairType;
        this.searchingForName = searchingForFullName;
        this.listeningToFemAudio = listeningToFemAudio;
    }
    public void busqueda(CaballoModel horseToFind, String whatToLookFor){
        this.caballoAcierto = horseToFind;
        this.searchingForPotrillo = true;
        this.whatToLookFor = whatToLookFor;
    }

    public void showWhatToLookFor(){
        Log.d("!!!WHAT-TO-LOOK-FOR", whatToLookFor);
    }

    public void showRespuestasPosibles(List<? extends View> views) {
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
    public void showRespuestasCruza(List<? extends View> views) {
        for (int i = 0; i < views.size(); i++) {
            CaballoModel randomHorseCruza = caballosProvider.randomHorseCruza();
            views.get(i).setTag(randomHorseCruza);
            manageViewsListItem(randomHorseCruza, i);
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
        }else if(searchingForPotrillo){
            return(((CaballoModel)view.getTag()).getPadres()).equals(horse.getPadres());
        }
        return (((CaballoModel)view.getTag()).getName())
                .equals(horse.getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void validateView(){
        ArrayList<Integer> sounds = new ArrayList<>();
        if ( viewValidationCondition() ){
            sounds.add(AudioProvider.INSTANCE.getSoundAt("success"));
            this.context.incrementAciertos();
        } else {
            sounds.add(AudioProvider.INSTANCE.getSoundAt("error"));
        }
        AudioPlayer.wannaPlaySound(sounds, this.context);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // determine what to do
        determineWhatToDo();
    }

    protected void determineWhatToDo() {
        if (this.context.gameWon()){
            // inform the user
            //this.context.makeToast("¡Ganaste!");
            // reset
            this.context.resetRondasyAciertos();
            // if RP}
            if(!this.context.playingCruza()){
                // enable RPJ in settings
              //  this.context.enableRPJ();
                // ask to retry game or play next
                this.context.showNextLayout();
                // confetti
               this.context.startConfettiAnimation();
            }else{
                this.context.playRazasYPelajes();
                     this.context.showTrophy();
                     this.context.startTrophyAnimation();

            }
        }else if (this.context.gameLost()){
            // inform user
          //  this.context.makeToast("Obtuviste menos de 3 respuestas correctas en 5 intentos.");
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
    protected  Boolean validateCruza( View view){
        return ( ((CaballoModel)view.getTag()).getPadres() ).contains(whatToLookFor);
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

    public abstract void showRespuestasPosibles();

    public abstract void showRespuestasCruza();

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
