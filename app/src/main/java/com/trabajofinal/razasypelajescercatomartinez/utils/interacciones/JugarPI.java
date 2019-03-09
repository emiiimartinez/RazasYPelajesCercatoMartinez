package com.trabajofinal.razasypelajescercatomartinez.utils.interacciones;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trabajofinal.razasypelajescercatomartinez.JugarActivity;
import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JugarPI extends InteraccionManager {
    private TextView horseToFindTextView;
    private List<ImageView> imageViews;
    private ImageView soundImgView, selectedImageView;
    private Button volver;

    public JugarPI(final JugarActivity context, Boolean playingLevel2) {
        super(context, playingLevel2);

        volver = context.findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                context.startActivity(i);
            }
        });


        horseToFindTextView = this.context.findViewById(R.id.caballoAEncontrar);
        soundImgView = this.context.findViewById(R.id.audioButton);
        soundImgView.setOnClickListener(this.context);
        initPossibleAnswersContainersArray();
    }

    @Override
    protected void initPossibleAnswersContainersArray() {
        fillHorsesImageViewsArray();
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
        ImageView img= this.context.findViewById(R.id.caballo1);
        img.setBackgroundResource(R.drawable.blue_border);
        imageViews.add(img);
        img= this.context.findViewById(R.id.caballo2);
        img.setBackgroundResource(R.drawable.blue_border);
        imageViews.add(img);
        img= this.context.findViewById(R.id.caballo3);
        img.setBackgroundResource(R.drawable.blue_border);
        imageViews.add(img);
        img= this.context.findViewById(R.id.caballo4);
        img.setBackgroundResource(R.drawable.blue_border);
        imageViews.add(img);

    }

    private void fillHorsesImgViewsArrayL1() {
        imageViews = new ArrayList<>();
        ImageView img= this.context.findViewById(R.id.caballo2);
        img.setBackgroundResource(R.drawable.blue_border);
        imageViews.add(img);
        img= this.context.findViewById(R.id.caballo3);
        img.setBackgroundResource(R.drawable.blue_border);
        imageViews.add(img);
    }

    @Override
    public void resetViewsTags(){
        resetViewsTags(imageViews);
    }


    @Override
    public void showWhatToLookFor() {
        super.showWhatToLookFor();
        horseToFindTextView.setText(whatToLookFor.toUpperCase());
    }

    @Override
    public void showRespuestasPosibles() {
        showRespuestasPosibles(imageViews);
    }

    @Override
    public void showRespuestasCruza() {

    }

    @Override
    protected void resetSoundImageToRegular() {
        soundImgView.setImageResource(R.drawable.ic_audio_regular);
    }

    @Override
    protected void manageViewsListItem(CaballoModel randomHorse, int i) {
        ImageView imageView = imageViews.get(i);
        setImageResource(imageView, randomHorse.getImagen());
    }

    @Override
    public void putAnswerInGame() {
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

    protected Boolean viewValidationCondition() {
        return viewValidationCondition(selectedImageView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void manageOnClick(View view) {
        if (view == soundImgView){
            playHorseToFindSound();
        }else{
            selectedImageView = (ImageView) view;
            validateView();
        }
    }
}
