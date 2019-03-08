package com.trabajofinal.razasypelajescercatomartinez.utils.reconocimiento;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.trabajofinal.razasypelajescercatomartinez.R;
import java.util.List;

public class RecGrillaAdapter extends ArrayAdapter<RecGrillaItem> {

    Context context;
    int layoutResourceId;
    List<RecGrillaItem> data;

    public RecGrillaAdapter(Context context, int resource, List<RecGrillaItem> objects) {
        super(context, resource, objects);

        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    static class DataHolder{
        Button soundImgView;
        ImageView horseImageView;
        TextView horseTextView;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder dataHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
            convertView = layoutInflater.inflate(layoutResourceId, parent, false);
            dataHolder = new DataHolder();

            dataHolder.horseImageView = convertView.findViewById(R.id.image1);
            dataHolder.soundImgView = convertView.findViewById(R.id.button1);
            dataHolder.horseTextView = convertView.findViewById(R.id.textView1);
            convertView.setTag(dataHolder);
        }else{
            dataHolder = (DataHolder) convertView.getTag();
        }
        RecGrillaItem listItem = data.get(position);
        dataHolder.horseTextView.setText(listItem.getNombre());
        Drawable draw = context.getResources().getDrawable(context.getResources().getIdentifier(listItem.imagen,"drawable",context.getPackageName()));

        dataHolder.horseImageView.setImageDrawable(draw);
        dataHolder.horseImageView.setTag(listItem.getImagen());
        dataHolder.soundImgView.setTag(listItem.getSonido());
        return convertView;
    }
}
