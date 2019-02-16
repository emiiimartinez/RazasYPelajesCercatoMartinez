package com.trabajofinal.razasypelajescercatomartinez;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class Audio extends Service {
    @Override
    public void onCreate() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    MediaPlayer mp = MediaPlayer.create(this, R.raw.relincho1);
    MediaPlayer er = MediaPlayer.create(this, R.raw.resopla);

    public void bien(){
        mp.start();
        mp.release();
    }
    public void mal(){
        er.start();
        er.release();
    }

}
