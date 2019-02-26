package com.trabajofinal.razasypelajescercatomartinez.utils.interacciones;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.trabajofinal.razasypelajescercatomartinez.JugarActivity;
import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JugarII extends InteraccionManager {
    private ImageView horseToFindImgView;
    private List<ImageView> imageViews;
    private ImageView soundImgView, selectedImageView;
    private Button volver;
    public JugarII(final JugarActivity context, Boolean playingLevel2) {
        super(context, playingLevel2);
        volver = context.findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(i);
            }
        });
        horseToFindImgView = this.context.findViewById(R.id.caballoAEncontarImg);
        initPossibleAnswersContainersArray();
    }


    @Override
    protected void initPossibleAnswersContainersArray() {
        fillHorsesImageViewsArray();
        // set onclick listener
        setViewListItemsOnClickHandler(imageViews);
    }
    private void fillHorsesImageViewsArray() {
        if (playingLevel2){
            fillHorsesImgViewsArrayL2();
        }
        else{
            fillHorsesImgViewsArrayL1();
        }
    }

    private void fillHorsesImgViewsArrayL2() {
        imageViews = new ArrayList<>();
        imageViews.add((ImageView) this.context.findViewById(R.id.caballo1));
        imageViews.add((ImageView) this.context.findViewById(R.id.caballo2));
        imageViews.add((ImageView) this.context.findViewById(R.id.caballo3));
        imageViews.add((ImageView) this.context.findViewById(R.id.caballo4));
    }

    private void fillHorsesImgViewsArrayL1() {
        imageViews = new ArrayList<>();
        imageViews.add((ImageView) this.context.findViewById(R.id.caballo2));
        imageViews.add((ImageView) this.context.findViewById(R.id.caballo3));
    }

    @Override
    public void resetViewsTags() {
        resetViewsTags(imageViews);
    }
    @Override
    public void showWhatToLookFor() {
        super.showWhatToLookFor();
        setImageResource(horseToFindImgView, whatToLookFor);
    }
    @Override
    public void showPossibleAnswers() {
        showPossibleAnswers(imageViews);
    }

    @Override
    protected void resetSoundImageToRegular() {

    }

    @Override
    protected void manageViewsListItem(CaballoModel randomHorse, int i) {
        ImageView imageView = imageViews.get(i);
        setImageResource(imageView, randomHorse.getImagen());
    }

    @Override
    public void putAnswerInGame() {
        // if there is nothing matching the answer, upload horseToFind
        if ( !isAlreadyInViews(caballoAcierto, imageViews) ){
            Collections.shuffle(imageViews);
            ImageView randomImgView = imageViews.get(0);
            randomImgView.setTag(caballoAcierto);
            setImageResource(randomImgView, caballoAcierto.getImagen());
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void playHorseToFindSound() {
        playHorseSound(caballoAcierto);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void manageOnClick(View view) {
        if (view == soundImgView){
            //setImageResource( ((ImageView)this.context.findViewById(R.id.audioButton)), R.drawable.ic_audio_click);
            playHorseToFindSound();
        }else{
            // se hizo click en una imagen
            selectedImageView = (ImageView) view;
            validateView();
        }
    }
    @Override
    protected Boolean viewValidationCondition() {
        return viewValidationCondition(selectedImageView);
    }
}
