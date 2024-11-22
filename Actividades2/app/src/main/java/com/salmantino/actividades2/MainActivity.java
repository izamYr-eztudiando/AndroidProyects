package com.salmantino.actividades2;

import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitulo;
    private EditText edNombre;
    private Button btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        edNombre = (EditText) findViewById(R.id.edNombre);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Siguiente(View view){
        Intent siguiente = new Intent(this, Actividad2.class);
        siguiente.putExtra("dato", edNombre.getText().toString());
        Toast toast = Toast.makeText(getApplicationContext(), " Enviando el nombre "+edNombre.getText().toString(), Toast.LENGTH_LONG);
        toast.show();
        startActivity(siguiente);
    }

    public void Salir(View view){
        finishAffinity();
        System.exit(1);
    }


}