package com.salmantino.bancos;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView ListViewEmpresa;
    List<Banco> list;

    public List<Banco> getData() {
        list=new ArrayList<>();
        list.add(new Banco(0,R.mipmap.santander,"Sara","Hernandez","CEO","Santander","678654321",1000,1,10000,3500));
        list.add(new Banco(1,R.mipmap.sabadell,"Antonio","Iniesta","CEO","sabadell","678654322",100,2,10000,3500));
        list.add(new Banco(2,R.mipmap.banesto,"Juan","Salinas","CEO","Banesto","678654323",180,3,10000,3500));
        list.add(new Banco(3,R.mipmap.cajaduero,"Mateo","Conde","CEO","CajaDuero","678654324",120,4,10000,3500));
        list.add(new Banco(4,R.mipmap.liberbank,"Arturo","Gutierrez","CEO","Liberbank","678654325",100,5,10000,3500));
        list.add(new Banco(5,R.mipmap.novagalicia,"Sonia","Santos","CEO","NovaGalicia","678654326",140,6,10000,3500));
        return list;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListViewEmpresa = findViewById(R.id.ListViewContacto);



        CustomAdapter adapter = new CustomAdapter(this,getData());
        ListViewEmpresa.setAdapter(adapter);
        ListViewEmpresa.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Banco c = list.get(i);
                Toast.makeText(getBaseContext(),c.nombre,Toast.LENGTH_LONG).show();
                if (c.empresa.equalsIgnoreCase("Sabadell")){
                    view.animate().rotation(360).setDuration(1500).start();
                    return;
                }

                Intent siguiente = new Intent(getApplicationContext(), DetalleActivity.class);
                siguiente.putExtra("banco",c.getEmpresa());
                Toast toast = Toast.makeText(getApplicationContext(), "Enviando el dato: "+c.getEmpresa(), Toast.LENGTH_LONG);
                toast.show();
                startActivity(siguiente);
            }
        });





    }




}