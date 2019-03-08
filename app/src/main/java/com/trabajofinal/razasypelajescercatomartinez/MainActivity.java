package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences configPreferences = getSharedPreferences(getString(R.string.config_preferences),Context.MODE_PRIVATE);

    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        initLayout();
    }
    private void initLayout() {
        findViewById(R.id.reconocimientoButton).setBackgroundResource(R.drawable.ic_reconocimiento_regular);
        findViewById(R.id.jugarButton).setBackgroundResource(R.drawable.ic_jugar_regular);
        findViewById(R.id.aboutButton).setBackgroundResource(R.drawable.ic_info_regular);
        findViewById(R.id.configButton).setBackgroundResource(R.drawable.ic_config_regular);
    }

    public void jugar(View view) {
        findViewById(R.id.jugarButton).setBackgroundResource(R.drawable.ic_jugar_click);
        Intent intent = new Intent(this, JugarActivity.class);
        startActivity(intent);
    }

    public void reconocimiento(View view) {
        findViewById(R.id.reconocimientoButton).setBackgroundResource(R.drawable.ic_reconocimiento_click);
        Intent intent = new Intent(this, ReconocimientoActivity.class);
        startActivity(intent);
    }

    public void configuracion(View view) {
        findViewById(R.id.configButton).setBackgroundResource(R.drawable.ic_config_click);
        Intent intent = new Intent(this, ConfiguracionActivity.class);
        startActivity(intent);
    }

    public void about(View view) {
        findViewById(R.id.aboutButton).setBackgroundResource(R.drawable.ic_info_click);
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}
