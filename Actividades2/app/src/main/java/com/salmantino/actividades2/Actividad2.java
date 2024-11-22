package com.salmantino.actividades2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actividad2 extends AppCompatActivity {

    private TextView txtTitulo1;
    private TextView txtResultado;
    private Button btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actividad2);

        txtTitulo1 = (TextView) findViewById(R.id.txtTitulo1);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        btnAtras = (Button) findViewById(R.id.btnAtras);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String resultado = getIntent().getStringExtra("dato");
        String cadena = "Bienvenido/a "+resultado;
        txtResultado.setText(cadena);

    }

    public void Atras(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }
}