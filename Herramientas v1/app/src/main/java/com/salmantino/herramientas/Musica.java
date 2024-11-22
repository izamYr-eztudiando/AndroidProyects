package com.salmantino.herramientas;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.widget.ImageView;


public class Musica extends Fragment {

    //variables para trabajar con los servicios y musica
    private boolean encendida;

    private ImageView botonMusica;

    public Musica() {
        // Required empty public constructor
    }

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //encendida = false;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmento = inflater.inflate(R.layout.fragment_musica, container, false);

        //debemos identificar quien es el botón musica (el del icono del altavoz)
        botonMusica = (ImageView) fragmento.findViewById(R.id.musica);

        //si encendida es verdadera (está encendida) utiliza como recurso el icono con el fondo
        // amarillo esto lo utilizamos para que el icono permanezca en amarillo aún cuando salgamos
        // de esa opción, de tal manera que la canción se siga reproduciendo y cuando volvamos a esa
        // opción el icono siga encendido (con el fondo amarillo)
        if(encendida) botonMusica.setImageResource(R.drawable.musica2);
        //con esta línea de código podemos decirle a nuestra aplicación en sus fragmentos que pueda
        // girar sin caerse la app hay que hacer lo mismo en todos los fragmentos.
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        //lo siguiente es ponerlo a la escucha
        botonMusica.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //hacemos un condicional que diga si está o no encendida la musica mediante un
                // método que todavía no hemos creado que le llamaremos apagaMusica()
                if(encendida) apagaMusica();
                else enciendeMusica();
            }
        });

        //Metodo onCreateView nos debe devolver, en este caso, el fragmento.
        return fragmento;
    }

    //Vamos a crear los dos métodos que encenderá y apagará la musica
    public void enciendeMusica() {
        //le decimos el boton que hará esta función (altavoz con fondo amarillo)
        botonMusica.setImageResource(R.drawable.musica2);

        //Ahora le tenemos que decir que comience el servicio y la api dice que la hagamos con
        // un intent.
        Intent miReproductor = new Intent(getActivity(), ServicioMusica.class);

        //para que comience el servicio
        getActivity().startService(miReproductor);

        //en este metodo le tenemos que indicar que la variable booleana encendida pasa a estar
        // a true. encendida = true;
        //esto lo que hace es que cambia el estado de la variable, del que está al contrario.
        encendida = !encendida;
    }

    //Vamos ahor a crear el método apagaMusica
    public void apagaMusica(){
        //le decimos el boton que hará esta función (altavoz sin fondo).
        botonMusica.setImageResource(R.drawable.musica);

        //Ahora le tenemos que decir que comience el servicio y la api dice que la hagamos con un
        // intent.
        Intent miReproductor = new Intent(getActivity(), ServicioMusica.class);

        //para que pare el servicio
        getActivity().stopService(miReproductor);

        //en este metodo le tenemos que indicar que la variable booleana encendida pasa a estar
        // a true. encendida = true;
        //esto lo que hace es que cambia el estado de la variable, del que está al contrario.
        encendida = !encendida;
    }

}
