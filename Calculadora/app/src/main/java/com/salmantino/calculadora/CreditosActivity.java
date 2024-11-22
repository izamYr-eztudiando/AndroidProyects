package com.salmantino.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreditosActivity extends AppCompatActivity {

    private TextView txtAutorValue;
    private TextView txtEmailValue;
    private TextView txtCursoValue;
    private TextView txtFechaValue;
    private TextView txtVersionValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_creditos);

        txtAutorValue = (TextView) findViewById(R.id.txtAutorValue);
        txtEmailValue = (TextView) findViewById(R.id.txtEmailValue);
        txtCursoValue = (TextView) findViewById(R.id.txtCursoValue);
        txtFechaValue = (TextView) findViewById(R.id.txtFechaValue);
        txtVersionValue = (TextView) findViewById(R.id.txtVersionValue);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String autorValue = getIntent().getExtras().getString("Autor");
        String emailValue = getIntent().getExtras().getString("Email");
        String cursoValue = getIntent().getExtras().getString("Curso");
        String fechaValue = getIntent().getExtras().getString("Fecha");
        String versionValue = getIntent().getExtras().getString("Version");

        txtAutorValue.setText(autorValue);
        txtEmailValue.setText(emailValue);
        txtCursoValue.setText(cursoValue);
        txtFechaValue.setText(fechaValue);
        txtVersionValue.setText(versionValue);

    }

    public void Atras(View view){
        Intent atras = new Intent(this, MainActivity.class);
        startActivity(atras);
    }

}