package com.salmantino.herramientas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class Menu extends Fragment {

    //vamos a crear un array de los botones (linterna, altavoz, giroscopio)
    private final int[] BOTONESMENU = {R.id.linterna, R.id.musica, R.id.nivel};

    //array que almacena los botones iluminados
    private final int[] BOTONESILUMINADOS = {R.drawable.linterna2, R.drawable.musica2,
            R.drawable.nivel2};

    //variable que viajará desde las activdades hasta aquí para saber que botón se pulsó
    private int boton;

    public Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //devuelve un view que será el fragmento que queremos cargar
        View mimenu = inflater.inflate(R.layout.fragment_menu, container, false);

        boton = -1; //con esto evitamos que tome el valor 0 que está contemplado en el array,
        // posición 0 es linterna (por eso se ilumina de amarillo sino hacemos esto).
        //No se puede ejecutar la primera vez que la iniciemos pa no ha recibo la info
        //Esta instrucción solo debe llevarse a cabo si ha recibido la información de la actividad
        // herramientas, pero si se ejecuta por primera vez caerá la aplicación,
        //entonces se deberá aplicar esta información SI es diferente de nulo, es decir si NO es la
        // primera vez sí se ejecuta
        if (getArguments() != null) {
            //la información que estamos enviando desde actividad herramientas. Recordamos que cada
            // vez que nosotros construimos un menú todo parte de
            // OncreateView en clase Menu, es dentro de ese método donde definitivamente se crea ese
            // fragment de menú
            //rescatamos la información que viaja en el bundle.
            boton = getArguments().getInt("BOTONPULSADO");
        }

        //crear objeto de tipo ImageButon para trabajar con los botones del menu
        ImageButton botonmenu;

        //vamos a poner los botones del menu a la escucha del evento click.
        //recorremos el array donde tengo lo botones y ponerlos a la escucha
        for (int i = 0; i < BOTONESMENU.length; i++) {

            //almacenamos dentro de la variable objeto botonmenu cada una de las variables;
            botonmenu = (ImageButton) mimenu.findViewById(BOTONESMENU[i]);

            //aqui le decimos al bucle for que SI el boton es igual a i coloque la imagen del array
            // hemos creado para los botones iluminados
            if (boton == i) {
                botonmenu.setImageResource(BOTONESILUMINADOS[i]);
            }

            //debemos saber en qué botón hemos pulsado, esta variable es la misma que pasamos por
            // parámetro al método de la interfaz comunicaMenu
            final int queBoton = i;

            //con este método pone los botones a la escucha y lo hará a cada vuelta de bucle
            botonmenu.setOnClickListener(new View.OnClickListener() {
                //Al ser una interfaz tiene que implementar el método

                public void onClick(View v) {
                    // este método es el que tiene que hacer las dos cosas que hemos dicho, detectar
                    // en qué actividad se encuentra el botón pulsado y enviar al método menú de la
                    // interfaz comunica menú en qué botones hemos pulsado
                    //para detectar en qué actividad nos encontramos
                    Activity estaActividad = getActivity();

                    //hay que enviar al menu de la interfaz comunicamenú la información.
                    //esta línea así da error "estaActividad.menu(queBoton);"
                    // pq estoy utilizando una variable de tipo activity que es de una actividad,
                    // para llamar a un método que es menú que pertenece a una interfaz llamada
                    // comunica menu:
                    //entonces no corresponden los tipos, para que correspondan los tipos hacemos
                    // un casting
                    ((ComunicaMenu) estaActividad).menu(queBoton);
                }
            });
        }
        return mimenu;
    }
}