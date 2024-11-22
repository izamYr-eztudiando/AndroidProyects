package com.salmantino.herramientas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActividadHerramientas extends AppCompatActivity
        implements ComunicaMenu, ManejaFlashCamara{

    //array que guardará los distintos fragment que dependera del botón pulsado
    // (linterna, musica, o giroscopio).
    private Fragment[] misFragmentos;
    private CameraManager micamara;
    private String idCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_herramientas);

        misFragmentos = new Fragment[3];
        misFragmentos[0] = new Linterna();
        misFragmentos[1] = new Musica();
        misFragmentos[2] = new Nivel();

        //CON ESTO SE OBTIENE LA INFORMACIÓN DEL BUNDLE CREADO EN MAINACTIVITY
        Bundle extras = getIntent().getExtras();

        //con esto extraemos esa información y se la pasamos al método menu
        menu(extras.getInt("BOTONPULSADO"));

        micamara= (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try{
            //array de String que dice el método para identicar cada una de las cámaras...
            idCamara = micamara.getCameraIdList()[0];
        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    //desarrollo del metodo que obliga a implementar la interfaz ComunicaMenu
    public void menu(int queboton) {

        //FragmentManager miManejador = getFragmentManager();
        FragmentManager miManejador = getSupportFragmentManager(); // Cambiado a getSupportFragmentManager()

        FragmentTransaction miTransaccion = miManejador.beginTransaction();
        //aqui le decimos a los botones iluminados sobre que boton se ha pulsado
        //programar de forma programática
        //esto es posible gracias al princpio de es un...
        // pues menú está heredando de fragment
        // Crear un nuevo fragmento de menú iluminado.
        Fragment menu_iluminado = new Menu();

        //mediante un bundle vamos a pasarle la información a este nuevo menu_iluminado
        Bundle datos = new Bundle();
        //ahora esta empaquetada toda la información del boton pulsado
        datos.putInt("BOTONPULSADO", queboton);

        //mediante este método se le pasa la información desde esta actividad hasta la clase menu
        //de tal forma que nuestro nuevo fragmento de menu (menu iluminado) ya sabe que tiene que
        // crear un nuevo fragmento de menu
        menu_iluminado.setArguments(datos);

        //aquí es donde le decimos a nuestra actividad que reemplace el menu que hay en pantalla
        // por este nuevo que hemos creado
        miTransaccion.replace(R.id.menu, menu_iluminado);
        miTransaccion.replace(R.id.herramientas, misFragmentos[queboton] );
        miTransaccion.commit();
    }

    @Override
    public void enciendeapaga(boolean estadoflash) {
        try {
            if (estadoflash) {
                Toast.makeText(this, "Flash apagado", Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    micamara.setTorchMode(idCamara, false);
                }
            } else {
                Toast.makeText(this, "Flash encendido", Toast.LENGTH_LONG).show();

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    micamara.setTorchMode(idCamara, true);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //con este método, si le damos al boton de salir del dispositivo sale.
    @SuppressLint("MissingSuperCall")
    public void onBackPressed(){
        moveTaskToBack(true);
    }
}
