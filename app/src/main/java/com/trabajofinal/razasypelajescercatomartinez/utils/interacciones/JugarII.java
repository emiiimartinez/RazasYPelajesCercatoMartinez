package com.trabajofinal.razasypelajescercatomartinez.utils.interacciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.trabajofinal.razasypelajescercatomartinez.JugarActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;

public class JugarII extends InteraccionManager {

    public JugarII(JugarActivity context, Boolean playingLevel2) {
        super(context, playingLevel2);
    }


    @Override
    protected void initPossibleAnswersContainersArray() {

    }

    @Override
    public void resetViewsTags() {

    }

    @Override
    public void showPossibleAnswers() {

    }

    @Override
    protected void resetSoundImageToRegular() {

    }

    @Override
    protected void manageViewsListItem(CaballoModel horseImgId, int randomHorseImgId) {

    }

    @Override
    public void putAnswerInGame() {

    }

    @Override
    public void manageOnClick(View view) {

    }

    @Override
    protected Boolean viewValidationCondition() {
        return null;
    }
}
