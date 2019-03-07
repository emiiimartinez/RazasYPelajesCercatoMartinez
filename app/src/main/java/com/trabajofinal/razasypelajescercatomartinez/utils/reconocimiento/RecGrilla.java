package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.caballos.CaballosProvider;

import java.util.ArrayList;
import java.util.List;

public class RecGrilla extends AppCompatActivity {
    private CaballoModel caballo;
    private CaballosProvider caballosProvider;
    private List<CaballoModel> caballos;
    private Button volver;
    private Button sonido;
    private ImageView iv;
    private TextView raza;
    private String pelaje;
    private String aux;


    protected List list=new ArrayList();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_grilla);

        volver = findViewById(R.id.volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

   /* public RecGrilla(RecoActivity context) {
        super(context);
    }*/

    protected void manageList(String img, CaballoModel horse, ArrayList<Integer> sounds) {
        list.add( new RecGrillaItem(
                img, horse.getName().toUpperCase(), sounds
        ) );
    }
    protected void fulfillItems() {
        list = new ArrayList<>();
        CaballosProvider horsesProvider = new CaballosProvider(this);
        List<CaballoModel> horses = horsesProvider.getHorsesList();
        for (int i = 0; i < horses.size(); i++) {
            CaballoModel horse = horses.get(i);
            String img =horse.getImagen();
            ArrayList<Integer> sounds;
            if (listeningToFemAudio()){
                sounds = horse.getFemSounds();
            } else {
                sounds = horse.getMaleSounds();
            }
            manageList(img, horse, sounds);
        }
    }
    public void prepareView() {
        // fulfill gridItems
        fulfillItems();
        // get view and set custom adapter
        GridView gridView = findViewById(R.id.grilla);
        RecGrillaAdapter customGridAdapter = new RecGrillaAdapter(this, R.layout.activity_rec_grilla_item, list);
        gridView.setAdapter(customGridAdapter);
    }


}
