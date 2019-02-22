package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // testing config_preferences
        Log.d("-----------------------", "Config Preferences -----------------------");
        SharedPreferences configPreferences = getSharedPreferences(getString(R.string.config_preferences),Context.MODE_PRIVATE);

        Boolean playinLevel2 = configPreferences.getBoolean(getString(R.string.level2_pref_key), false);
        Log.d("!!!!NIVEL2? ", String.valueOf(playinLevel2));

        Boolean femAudio = configPreferences.getBoolean(getString(R.string.fem_audio_pref_key), false);
        Log.d("!!!!FEM-AUDIO? ", String.valueOf(femAudio));

        Integer viewMode = configPreferences.getInt(getString(R.string.reco_view_mode_pref_key), R.id.listRadioBtn);
        Log.d("!!!!GRID? ", String.valueOf(viewMode == R.id.gridRadioBtn));
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        initLayout();
    }
    private void initLayout() {
        //findViewById(R.id.reconocimientoButton).setBackgroundResource(R.drawable.ic_reconocimiento_regular);
        //findViewById(R.id.playButton).setBackgroundResource(R.drawable.ic_jugar_regular);
        //findViewById(R.id.infoButton).setBackgroundResource(R.drawable.ic_info_regular);
        findViewById(R.id.configButton).setBackgroundResource(R.drawable.ic_config_regular);
    }
    /** Called when the user taps Settings Button */
    public void configuracion(View view) {
        findViewById(R.id.configButton).setBackgroundResource(R.drawable.ic_config_click);
        Intent intent = new Intent(this, Configuracion.class);
        startActivity(intent);
    }

}
