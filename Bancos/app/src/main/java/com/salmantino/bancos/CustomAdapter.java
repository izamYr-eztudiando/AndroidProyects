package com.salmantino.bancos;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter{
    Context context;
    List<Banco> lista;

    public CustomAdapter(Context context, List<Banco> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
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
        TextView TextViewCategoria;
        TextView TextViewEmpresa = null;

        Banco c = lista.get(i);
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.listview_contacto,null);

        ImageViewContacto = view.findViewById(R.id.imageViewContacto);
        TextViewNombre = view.findViewById(R.id.textViewNombre);
        TextViewCategoria = view.findViewById(R.id.textViewCategoria);
        TextViewEmpresa = view.findViewById(R.id.textViewEmpresa);

        ImageViewContacto.setImageResource(c.imagen);
        TextViewNombre.setText(c.nombre+" "+c.apellido);
        TextViewCategoria.setText(c.categoria);
        TextViewEmpresa.setText(c.empresa);

        return view;
    }
}
