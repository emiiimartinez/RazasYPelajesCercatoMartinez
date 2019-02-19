package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

public class ReconocimientoLista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocimiento_lista);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton volver= findViewById(R.id.volverlista);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        ManagerImagenes im = new ManagerImagenes();

        try {
            Drawable[] drawables = im.CaballosImg(getApplicationContext());
            LinearLayout layout = (LinearLayout)findViewById(R.id.imageLayout);
            for(int i=0;i<drawables.length;i++)
            {
                ImageView image = new ImageView(this);
                image.setLayoutParams(new android.view.ViewGroup.LayoutParams(80,60));
                image.setMaxHeight(80);
                image.setMaxWidth(60);
                image.setImageDrawable(drawables[i]);
                // Adds the view to the layout
                layout.addView(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
