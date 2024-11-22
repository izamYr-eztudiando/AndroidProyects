package com.salmantino.herramientas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Linterna extends Fragment {

    private ImageView botonCamara;

    //el valor por defecto de una variable booleana en java es false.
    private boolean encendida;

    public Linterna() {
        // Requiere siempre de un constructor vacío
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmento = inflater.inflate(R.layout.fragment_linterna, container, false);

        //con esta línea ya tenemos almacenado la imagen de la linterna en la variable botonCamara
        botonCamara = (ImageView) fragmento.findViewById(R.id.linterna);

        if(encendida) botonCamara.setImageResource(R.drawable.linterna2);

        //con esta línea de código podemos decirle a nuestra aplicación en sus fragmentos que pueda
        //girar sin caerse la app hay que hacer lo mismo en todos los fragmentos.
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        //Ahora vamos a ponerlo a la escucha
        botonCamara.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                if(encendida){
                    //como decir que apague el flash de la camara
                    botonApagaFlash();
                    encendida = false; //no lo ponemos porque por defecto está en false
                } else {
                    botonEnciendeFlash();
                    encendida = true;
                }
            }
        }); //clase anónima dentro del método onCreateView

        return fragmento;
    }//esta llave cierra el método onCreateView

    //hacemos el método botonEnciendeFlash
    public void botonEnciendeFlash(){

        botonCamara.setImageResource(R.drawable.linterna2);

        //para detectar en qué actividad nos encontramos
        Activity estaActividad = getActivity();

        //hay que enviar al menu de la interfaz comunicamenú la información.
        //esta línea así da error "estaActividad.menu(queBoton);"
        //pq estoy utilizando una variable de tipo activity que es de una actividad,
        //para llamar a un método que es menú que pertenece a una interfaz llamada comunica menu;
        //entonces no corresponden los tipos, para que correspondadn los tipos hacemos un casting
        ((Manejaflashcamara)estaActividad).enciendeapaga((encendida));
    }

    public void botonApagaFlash(){

        botonCamara.setImageResource(R.drawable.linterna);
        Activity estaActividad = getActivity();
        ((Manejaflashcamara)estaActividad).enciendeapaga(encendida);
    }

}