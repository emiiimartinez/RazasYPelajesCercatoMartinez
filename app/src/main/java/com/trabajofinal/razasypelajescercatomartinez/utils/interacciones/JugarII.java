package com.trabajofinal.razasypelajescercatomartinez.utils.interacciones;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.trabajofinal.razasypelajescercatomartinez.JugarActivity;
import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;

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
    public void resetViewsTags() {
        resetViewsTags(imageViews);
    }
    @Override
    public void showWhatToLookFor() {
      super.showWhatToLookFor();
      setImageResource(horseToFindImgView, caballoAcierto.getPotrillo());
    }

    @Override
    public void showRespuestasCruza() {
        showRespuestasCruza(imageViews);
    }

    @Override
    public void showRespuestasPosibles() {
    }

    @Override
    protected void resetSoundImageToRegular() {

    }

    @Override
    protected void manageViewsListItem(CaballoModel randomHorse, int i) {
        ImageView imageView = imageViews.get(i);
        setImageResource(imageView, randomHorse.getPadres());
    }

    @Override
    public void putAnswerInGame() {
        if ( !isAlreadyInViews(caballoAcierto, imageViews) ){
            Collections.shuffle(imageViews);
            ImageView randomImgView = imageViews.get(0);
            randomImgView.setTag(caballoAcierto);
            setImageResource(randomImgView, caballoAcierto.getPadres());
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void playHorseToFindSound() {

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void manageOnClick(View view) {
            selectedImageView = (ImageView) view;
            validateView();
    }
    @Override
    protected Boolean viewValidationCondition() {
        return validateCruza(selectedImageView);
    }
}
