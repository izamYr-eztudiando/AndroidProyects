package com.salmantino.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculosActivity extends AppCompatActivity {

    private TextView txtResultadoSuma;
    private TextView txtResultadoResta;
    private TextView txtResultadoMultiplicacion;
    private TextView txtResultadoDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculos);

        txtResultadoSuma = (TextView) findViewById(R.id.txtResultadoSuma);
        txtResultadoResta = (TextView) findViewById(R.id.txtResultadoResta);
        txtResultadoMultiplicacion = (TextView) findViewById(R.id.txtResultadoMultiplicacion);
        txtResultadoDivision = (TextView) findViewById(R.id.txtResultadoDivision);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int resultadoSuma = getIntent().getExtras().getInt("Sumar");
        int resultadoResta = getIntent().getExtras().getInt("Restar");
        int resultadoMultiplicacion = getIntent().getExtras().getInt("Multiplicar");
        float resultadoDivision = getIntent().getExtras().getFloat("Dividir");

        String cadSuma = Integer.toString(resultadoSuma);
        String cadResta = Integer.toString(resultadoResta);
        String cadMultiplicacion = Integer.toString(resultadoMultiplicacion);
        String cadDivision = Float.toString(resultadoDivision);

        txtResultadoSuma.setText(cadSuma);
        txtResultadoResta.setText(cadResta);
        txtResultadoMultiplicacion.setText(cadMultiplicacion);
        txtResultadoDivision.setText(cadDivision);


    }

    public void Volver(View view){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }


}