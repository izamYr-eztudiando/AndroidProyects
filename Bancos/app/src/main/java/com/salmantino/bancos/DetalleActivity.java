package com.salmantino.bancos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        List<Banco> lista;

        ImageView ImageViewContacto;
        TextView TextViewNombre;
        TextView TextViewCategoria;
        TextView TextViewEmpresa = null;

        MainActivity ma = new MainActivity();
        lista = ma.getData();
        Banco c = lista.get(0);

        ImageViewContacto = findViewById(R.id.imageViewContacto1);
        TextViewNombre = findViewById(R.id.textViewNombre1);
        TextViewCategoria = findViewById(R.id.textViewCategoria1);
        TextViewEmpresa = findViewById(R.id.textViewEmpresa1);

        ImageViewContacto.setImageResource(c.imagen);
        TextViewNombre.setText(c.nombre+" "+c.apellido);
        TextViewCategoria.setText(c.categoria);
        TextViewEmpresa.setText(c.empresa);
    }

}
