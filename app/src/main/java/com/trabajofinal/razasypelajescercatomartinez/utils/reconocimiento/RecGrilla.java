package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.trabajofinal.razasypelajescercatomartinez.MainActivity;
import com.trabajofinal.razasypelajescercatomartinez.R;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballoModel;
import com.trabajofinal.razasypelajescercatomartinez.utils.Caballos.CaballosProvider;

import java.util.List;

public class RecGrilla extends AppCompatActivity {
    private CaballoModel caballo;
    private CaballosProvider caballosProvider;
    private List<CaballoModel> caballos;
    private Button volver;
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
        caballosProvider = new CaballosProvider(this);
        caballos= caballosProvider.getHorsesList();
        LinearLayout ll = findViewById(R.id.container);

        for(int i=1; i < caballos.size(); i++){
            LinearLayout layout1 = new LinearLayout(this);
            layout1.setOrientation(LinearLayout.HORIZONTAL);
            layout1.getLayoutParams().width=LinearLayout.LayoutParams.FILL_PARENT;
            layout1.getLayoutParams().height = 0;

            for(int j=0; j<3; j++){
                if(i<caballos.size()) {
                    LinearLayout layout2 = new LinearLayout(this);
                    layout2.setOrientation(LinearLayout.HORIZONTAL);
                    layout2.getLayoutParams().width = LinearLayout.LayoutParams.FILL_PARENT;
                    layout2.getLayoutParams().height = 0;

                    ImageView iv = new ImageView(this);
                    caballo = caballos.get(i);
                    String nombre = caballo.getImagen();
                    Drawable draw = getResources().getDrawable(getResources().getIdentifier(nombre, "drawable", getPackageName()));
                    iv.setImageDrawable(draw);

                    layout2.addView(iv);
                    layout1.addView(layout2);
                    i++;
                }
            }
            if(i<caballos.size()) {
                i--;
            }
            ll.addView(layout1);

        }
    }
}
