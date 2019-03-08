package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

import com.trabajofinal.razasypelajescercatomartinez.R;



public class RecListaAdapter extends ArrayAdapter<RecListaItem> {

    Context context;
    int resource;
    List<RecListaItem> items;


    public RecListaAdapter(Context context, int resource, List<RecListaItem> lista) {
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

            dato.nombre = convertView.findViewById(R.id.texto1);
            dato.texto =  convertView.findViewById(R.id.Texto13);
            dato.sonido = convertView.findViewById(R.id.audio1);
            dato.imagen = convertView.findViewById(R.id.image1);
            convertView.setTag(dato);
        }else{
            dato = (RecItem) convertView.getTag();
        }

        RecListaItem listItem = items.get(pos);
        dato.nombre.setText(listItem.getNombre());
        dato.texto.setText(listItem.getNombre()); //deberia ser la descripcion
        int imageResource = context.getResources().getIdentifier(listItem.imagen, "drawable", context.getPackageName());
        Drawable draw = context.getResources().getDrawable(imageResource);
        dato.imagen.setImageDrawable(draw);
        dato.imagen.setTag(listItem.getImagen());
        dato.sonido.setTag(listItem.getSonido());

        return convertView;
    }
}
