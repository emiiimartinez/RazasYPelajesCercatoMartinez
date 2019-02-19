package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;

public class ManagerImagenes {

    public Drawable[] CaballosImg(Context context) throws IOException{
       /* AssetManager assetManager = context.getAssets();
        InputStream inputStream;
        String[] images = assetManager.list("assets/caballosNumerados");
        Drawable[] drawables = new Drawable[images.length];
        try {
            for (int i = 0; i < images.length; i++) {
                inputStream = context.getAssets().open("assets/caballosNumerados/" + images[i]);
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                drawables[i] = drawable;
            }
            context.getAssets().close();
        }catch(Exception e){

        }*/
        Drawable[] drawables = new Drawable[5];
        for (int j = 1; j < 5; j++) {
            drawables[j] = context.getResources().getDrawable(context.getResources().getIdentifier("caballo"+j, "drawable", context.getPackageName()));
        }
        return drawables;
    }

}
