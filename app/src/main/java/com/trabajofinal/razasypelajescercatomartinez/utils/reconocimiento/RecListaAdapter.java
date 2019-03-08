package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trabajofinal.razasypelajescercatomartinez.R;

import java.util.List;

public class RecListaAdapter extends ArrayAdapter<RecListaItem> {

    Context context;
    int resource;
    List<RecListaItem> items;
    RecItem dato = new RecItem();

    public RecListaAdapter(Context context, int resource, List<RecListaItem> lista) {
        super(context, resource, lista);

        context = context;
        resource = resource;
        items = lista;

    }


    @Override
    public View getView(int pos, View convertView, ViewGroup parent){

        if(convertView==null) {
            LayoutInflater layout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layout.inflate(resource,parent);

            dato.nombre = convertView.findViewById(R.id.texto1);
            dato.texto =  convertView.findViewById(R.id.Texto13);
            dato.sonido = convertView.findViewById(R.id.button1);
            dato.sonido = convertView.findViewById(R.id.image1);
            convertView.setTag(dato);
        }else{
            dato = (RecItem) convertView.getTag();
        }

        RecListaItem listItem = items.get(pos);
        dato.nombre.setText(listItem.getNombre());
        dato.texto.setText(listItem.getNombre()); //deberia ser la descripcion
        dato.imagen.setImageDrawable(listItem.getImagenDrawable());
        dato.imagen.setTag(listItem.getImagen());
        dato.sonido.setTag(listItem.getSonido());

        return convertView;
    }
}
