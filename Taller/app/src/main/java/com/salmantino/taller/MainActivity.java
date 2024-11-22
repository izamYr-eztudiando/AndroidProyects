package com.salmantino.taller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<String> talleresList;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.listViewTalleres);

       talleresList = new ArrayList<>();
       talleresList.add("Taller Juan e Hijos");
       talleresList.add("Chapa y Pintura González");
       talleresList.add("Norauto");
       talleresList.add("Midas");

       ArrayAdapter<String> adaptadorTalleres = new ArrayAdapter<>(
               this, android.R.layout.simple_list_item_1, talleresList);

       lista.setAdapter(adaptadorTalleres);

       lista.setOnItemClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        Toast.makeText(this, "Taller seleccionado: "+talleresList.get(posicion),Toast.LENGTH_SHORT).show();
        view.animate().rotation(360).setDuration(1000).start();
    }
}