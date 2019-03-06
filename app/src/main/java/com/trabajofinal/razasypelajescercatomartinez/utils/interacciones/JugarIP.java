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
import java.util.List;
import java.util.Random;

public class JugarIP extends InteraccionManager {

    private ImageView horseToFindImgView;
    private TextView selectedTextView;
    private List<TextView> horsesTextViews;
    private List<ImageView> soundsImageViews;
    private Button volver;
    public JugarIP(final JugarActivity context, Boolean playingLevel2) {
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
        fillHorsesTextViewsArray();
        setViewListItemsOnClickHandler(horsesTextViews);
        fillSoundsImgViewsArray();
        setSoundsImgViewsBackgroundImage();
        setViewListItemsOnClickHandler(soundsImageViews);
    }


    private void fillSoundsImgViewsArray() {
        if (playingLevel2){
            fillSoundsImgViewsArrayL2();
        }
        else{
            fillSoundsImgViewsArrayL1();
        }
    }

    private void fillSoundsImgViewsArrayL2() {
        soundsImageViews = new ArrayList<>();
        soundsImageViews.add((ImageView)this.context.findViewById(R.id.audio1));
        soundsImageViews.add((ImageView) this.context.findViewById(R.id.audio2));
        soundsImageViews.add((ImageView) this.context.findViewById(R.id.audio3));
        soundsImageViews.add((ImageView) this.context.findViewById(R.id.audio4));
    }

    private void fillSoundsImgViewsArrayL1() {
        soundsImageViews = new ArrayList<>();
        soundsImageViews.add((ImageView) this.context.findViewById(R.id.audio2));
        soundsImageViews.add((ImageView) this.context.findViewById(R.id.audio3));
    }

    private void setSoundsImgViewsBackgroundImage() {
        for (int i = 0; i < soundsImageViews.size(); i++) {
            soundsImageViews.get(i).setImageResource(R.drawable.ic_audio_regular);
        }
    }

    private void fillHorsesTextViewsArray() {
        if (playingLevel2){
            fillHorsesTextViewsArrayL2();
        }
        else{
            fillHorsesTextViewsArrayL1();
        }
    }

    private void fillHorsesTextViewsArrayL2() {
        horsesTextViews = new ArrayList<>();
        horsesTextViews.add((TextView) this.context.findViewById(R.id.texto1));
        horsesTextViews.add((TextView) this.context.findViewById(R.id.texto2));
        horsesTextViews.add((TextView) this.context.findViewById(R.id.texto3));
        horsesTextViews.add((TextView) this.context.findViewById(R.id.texto4));
    }

    private void fillHorsesTextViewsArrayL1() {
        horsesTextViews = new ArrayList<>();
        horsesTextViews.add((TextView) this.context.findViewById(R.id.texto2));
        horsesTextViews.add((TextView) this.context.findViewById(R.id.texto3));
    }

    @Override
    public void resetViewsTags() {
        resetViewsTags(soundsImageViews);
        resetViewsTags(horsesTextViews);
    }

    @Override
    public void showWhatToLookFor() {
        super.showWhatToLookFor();
        setImageResource(horseToFindImgView, caballoAcierto.getImagen());
    }

    @Override
    public void showRespuestasPosibles() {
        showRespuestasPosibles(horsesTextViews);
    }

    @Override
    public void showRespuestasCruza() {

    }

    @Override
    protected void resetSoundImageToRegular() {
        setSoundsImgViewsBackgroundImage();
    }

    @Override
    protected void manageViewsListItem( CaballoModel randomHorse, int i) {
        TextView textView = horsesTextViews.get(i);
        setText(textView, randomHorse);
        soundsImageViews.get(i).setTag( textView.getTag() );
    }

    private void setText(TextView textView, CaballoModel horse) {
        if( searchingForRaza ){
            textView.setText(horse.getRaza().toUpperCase());
        }else if( searchingForPelaje){
            textView.setText(horse.getPelaje().toUpperCase());
        }else if( searchingForName ){
            textView.setText(horse.getName().toUpperCase());
        }
    }

    @Override
    public void putRespuestaCorrecta() {
        // if there is nothing matching the answer, upload horseToFind
        if ( !isAlreadyInViews(caballoAcierto, horsesTextViews) ){
            Random random = new Random();
            Integer randIndex = random.nextInt(horsesTextViews.size());
            TextView randomTextView = horsesTextViews.get(randIndex);
            randomTextView.setTag(caballoAcierto);
            setText(randomTextView, caballoAcierto);
            soundsImageViews.get(randIndex).setTag( caballoAcierto );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void playSelectedHorseSound(View view) {
        playHorseSound( (CaballoModel) view.getTag() );
    }

    protected Boolean viewValidationCondition() {
        return viewValidationCondition(selectedTextView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void manageOnClick(View view) {
        Boolean wasASound = false;
        for (ImageView soundImgView: soundsImageViews) {
            if (view == soundImgView) {
                wasASound = true;
                ( (ImageView)this.context.findViewById(view.getId()) )
                        .setImageResource(R.drawable.ic_audio_click);
                playSelectedHorseSound(view);
            }
        }
        if(!wasASound){
            // an image view was clicked
            selectedTextView = (TextView) view;
            validateView();
        }
    }

}
