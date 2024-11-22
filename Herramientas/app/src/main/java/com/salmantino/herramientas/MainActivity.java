package com.salmantino.herramientas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ComunicaMenu{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void menu(int queboton){
        Intent in = new Intent(this, ActividadHerramientas.class);
        in.putExtra("BOTONPULSADO", queboton);
        startActivity(in);
    }
}