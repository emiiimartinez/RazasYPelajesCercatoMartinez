package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class ConfiguracionActivity extends AppCompatActivity {
    Switch levelSwitch;
    Switch audioSwitch;
    RadioGroup interactionRadioGroup;
    RadioGroup recoViewModeRadioGroup, recoFilterRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        setValues();
    }

    private void setValues() {
        levelSwitch = findViewById(R.id.levelSwitch);
        audioSwitch = findViewById(R.id.audioSwitch);
        //recoFilterRadioGroup = findViewById(R.id.filterViewModeRadioGroup);
        interactionRadioGroup = findViewById(R.id.interactionRadioGroup);
        recoViewModeRadioGroup = findViewById(R.id.recoViewModeRadioGroup);

        // set all values
        SharedPreferences configPreferences = getSharedPreferences(getString(R.string.config_preferences),Context.MODE_PRIVATE);
        levelSwitch.setChecked(configPreferences
                .getBoolean(getString(R.string.level2_pref_key), false));
        audioSwitch.setChecked(configPreferences
                .getBoolean(getString(R.string.fem_audio_pref_key), false));
     //   recoFilterRadioGroup.check(configPreferences.getInt(getString(R.string.reco_filter_key), R.id.r));
        interactionRadioGroup.check(configPreferences
                .getInt(getString(R.string.interaction_pref_key), R.id.InteracARadBtn));
        recoViewModeRadioGroup.check(configPreferences
                .getInt(getString(R.string.reco_view_mode_pref_key), R.id.listRadioBtn));
    }

    public void onAccept(View view) {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.config_preferences),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.fem_audio_pref_key), audioSwitch.isChecked());
        editor.putBoolean(getString(R.string.level2_pref_key), levelSwitch.isChecked());
       // editor.putInt(getString(R.string.reco_filter_key), recoFilterRadioGroup.getCheckedRadioButtonId());
        editor.putInt(getString(R.string.interaction_pref_key), interactionRadioGroup.getCheckedRadioButtonId());
        editor.putInt(getString(R.string.reco_view_mode_pref_key), recoViewModeRadioGroup.getCheckedRadioButtonId());
        editor.apply();
        finish();
    }

}
