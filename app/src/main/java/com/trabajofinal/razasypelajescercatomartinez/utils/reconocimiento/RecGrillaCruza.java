package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.trabajofinal.razasypelajescercatomartinez.R;

import java.util.List;

public class RecGrillaCruza extends ArrayAdapter<RecGrillaItem> {
    Context context;
    int resource;
    List<RecGrillaItem> items;


    public RecGrillaCruza(Context context, int resource, List<RecGrillaItem> lista) {
        super(context, resource, lista);

        this.context = context;
        this.resource = resource;
        this.items = lista;

    }

    @NonNull
    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        RecItem dato;

        if(convertView==null) {
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
            dato = new RecItem();

            dato.potrillo = convertView.findViewById(R.id.potrilloImg);
            dato.padres = convertView.findViewById(R.id.padresImg);
            convertView.setTag(dato);
        }else{
            dato = (RecItem) convertView.getTag();
        }

        RecGrillaItem listItem = items.get(pos);
        int imageResource = context.getResources().getIdentifier(listItem.potrillo, "drawable", context.getPackageName());
        Drawable draw = context.getResources().getDrawable(imageResource);
        dato.potrillo.setImageDrawable(draw);
        dato.potrillo.setTag(listItem.getImagen());

        imageResource = context.getResources().getIdentifier(listItem.padre, "drawable", context.getPackageName());
        draw = context.getResources().getDrawable(imageResource);
        dato.padres.setImageDrawable(draw);
        dato.padres.setTag(listItem.getImagen());

        return convertView;
    }
}
