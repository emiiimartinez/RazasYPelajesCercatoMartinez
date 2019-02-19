package com.trabajofinal.razasypelajescercatomartinez;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

public class ReconocimientoGrilla extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reconocimiento_grilla);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton volver= (ImageButton)findViewById(R.id.volvergrilla);
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
            LinearLayout layout = (LinearLayout)findViewById(R.id.layout1);
            int i=1;
            while(i<drawables.length)
            {
                LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
                for(int j = 0; j<3;j++){
                    if(i < drawables.length) {
                        ImageView image = new ImageView(this);
                        image.setLayoutParams(new android.view.ViewGroup.LayoutParams(80, 60));
                        image.setMaxHeight(20);
                        image.setMaxWidth(20);
                        image.setImageDrawable(drawables[i]);
                        // Adds the view to the layout
                        layout.addView(image);
                        //sumo i para avanzar
                        i++;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
