package com.salmantino.listadoamigos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<Contacto> list;

    public CustomAdapter(Context context, List<Contacto> list){
        this.context = context;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Contacto> getList() {
        return list;
    }

    public void setList(List<Contacto> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView ImageViewContacto;
        TextView TextViewNombre;
        TextView TextViewDescripcion;

        Contacto c = list.get(i);
        if (view==null)
            view= LayoutInflater.from(context).inflate(R.layout.listview_contacto,null);

        ImageViewContacto = view.findViewById(R.id.imageViewContacto);
        TextViewNombre = view.findViewById(R.id.textViewNombre);
        TextViewDescripcion = view.findViewById(R.id.textViewDescripcion);

        ImageViewContacto.setImageResource(c.imagen);
        TextViewNombre.setText(c.nombre);
        TextViewDescripcion.setText(c.Descripcion);

        return view;
    }
}
