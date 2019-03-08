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

public class RecGrillaAdapter extends ArrayAdapter<RecGrillaItem> {

    Context context;
    int resource;
    List<RecGrillaItem> items;

    public RecGrillaAdapter(Context context, int resource, List<RecGrillaItem> lista) {
        super(context, resource, lista);

        this.context = context;
        this.resource = resource;
        this.items = lista;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecItem dato;

        if (convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(resource, parent, false);
            dato = new RecItem();
            dato.nombre = convertView.findViewById(R.id.textView1);
            dato.sonido = convertView.findViewById(R.id.button1);
            dato.imagen = convertView.findViewById(R.id.image1);
           convertView.setTag(dato);
        }else{
            dato = (RecItem) convertView.getTag();
        }
        RecGrillaItem listItem = items.get(position);
        dato.nombre.setText(listItem.getNombre());
        Drawable draw = context.getResources().getDrawable(context.getResources().getIdentifier(listItem.imagen,"drawable",context.getPackageName()));
        dato.imagen.setImageDrawable(draw);
        dato.imagen.setTag(listItem.getImagen());
        dato.sonido.setTag(listItem.getSonido());

        return convertView;
    }
}
