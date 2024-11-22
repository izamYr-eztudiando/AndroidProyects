package com.salmantino.calculadora;

import android.content.Intent;
import android.media.Image;
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

    private EditText edNumero1;
    private EditText edNumero2;
    private Button btnCalcular;
    private Button btnCreditos;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edNumero1 = (EditText) findViewById(R.id.edNumero1);
        edNumero2 = (EditText) findViewById(R.id.edNumero2);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCreditos = (Button) findViewById(R.id.btnCreditos);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Calcular(View view){
        Intent calcular = new Intent(this, CalculosActivity.class);
        Integer inum1 = new Integer(0);

        int num1 = inum1.parseInt(edNumero1.getText().toString());

        Integer inum2 = new Integer(0);

        int num2 = inum2.parseInt(edNumero2.getText().toString());

        int suma = num1 + num2;
        int resta = num1 - num2;
        int multiplicacion = num1 * num2;
        float division = num1/num2;

        calcular.putExtra("Sumar", suma);
        calcular.putExtra("Restar", resta);
        calcular.putExtra("Multiplicar", multiplicacion);
        calcular.putExtra("Dividir", division);

        startActivity(calcular);
    }

    public void Creditos(View view){
        Intent creditos = new Intent(this, CreditosActivity.class);
        String autor = "Isamira";
        String email = "isamira@gmail.com";
        String curso = "DAM2";
        String fecha = "AYER";
        String version = "2.0";



        creditos.putExtra("Autor", autor);
        creditos.putExtra("Email", email);
        creditos.putExtra("Curso", curso);
        creditos.putExtra("Fecha", fecha);
        creditos.putExtra("Version", version);

        startActivity(creditos);
    }

    public void Salir(View view){
        finishAffinity();
        System.exit(1);
    }

}