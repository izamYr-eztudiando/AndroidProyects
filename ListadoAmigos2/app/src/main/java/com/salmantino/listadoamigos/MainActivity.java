package com.salmantino.listadoamigos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView ListViewContacto;
    List<Contacto> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListViewContacto = findViewById(R.id.ListViewContacto);
        CustomAdapter adapter = new CustomAdapter(this,getData());
        ListViewContacto.setAdapter(adapter);
        ListViewContacto.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contacto c = list.get(i);
                String mensaje = c.nombre+" "+c.Descripcion;
                Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_LONG).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private List<Contacto> getData() {
        list = new ArrayList<>();
        list.add(new Contacto(1,R.mipmap.amigo,"Carlos","632345678"));
        list.add(new Contacto(2,R.mipmap.amiga,"Ana","632345678"));
        list.add(new Contacto(3,R.mipmap.amigo,"Juan","632345678"));
        list.add(new Contacto(4,R.mipmap.amiga,"Marta","632345678"));
        list.add(new Contacto(5,R.mipmap.amigo,"Manuel","632345678"));
        list.add(new Contacto(6,R.mipmap.amiga,"Sara","632345678"));
        return list;
    }
}