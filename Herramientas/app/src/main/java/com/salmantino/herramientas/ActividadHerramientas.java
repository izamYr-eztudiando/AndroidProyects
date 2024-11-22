package com.salmantino.herramientas;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.widget.Toast;

import com.salmantino.herramientas.data.SensoresDbHelper;

public class ActividadHerramientas extends AppCompatActivity implements ComunicaMenu, Manejaflashcamara{

    //array que guardará los distintos fragment que dependera del botón pulsado
    // (linterna, música o giroscopio)
    private Fragment[] misFragmentos;
    private CameraManager micamara;
    private String idCamara;
    //CAMBIO 1 - incluimos como atributo para usarlo en toda la clase
    private SensoresDbHelper dbHelper;

    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_herramientas);

        misFragmentos = new Fragment[3];
        misFragmentos[0] = new Linterna();
        misFragmentos[1] = new Musica();
        misFragmentos[2] = new Nivel();

        //CAMBIO 2 - creamos la instancia de nuestro helper
        dbHelper = new SensoresDbHelper(this);
        dbHelper.getWritableDatabase();

        //Con esto se obtiene la información del bundle creado en MainActivity
        Bundle extras = getIntent().getExtras();

        //Con esto extraemos esa información y se la pasamos al método menu
        menu(extras.getInt("BOTONPULSADO"));

        micamara = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            //array de String que dice el método para identificar cada una de las camaras
            idCamara = micamara.getCameraIdList()[0];
        }catch (Exception e){
            e.printStackTrace();
        }

        /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */
    }

    @Override
    public void menu(int queboton){

        FragmentManager miManejador = getSupportFragmentManager();

        FragmentTransaction miTransaccion = miManejador.beginTransaction();
        //aqui le decimos a los botones iluminados sobre que boton se ha pulsado
        //programar de forma programática
        //esto es posible gracias al principio de es un...
        //pues menú está heredando de fragment
        Fragment menu_iluminado = new Menu();

        //mediante un bundle vamos a pasarle la información a este nuevo menu_iluminado
        Bundle datos = new Bundle();
        //ahora esta empaquetada toda la información del boton pulsado
        datos.putInt("BOTONPULSADO", queboton);

        //mediante este método se le pasa la información desde esta actividad hasta la clase menu
        //de tal forma que nuestro nuevo fragmento de menu (menu iluminado) ya sabe que tiene que
        //crear un nuevo fragmento de menu
        menu_iluminado.setArguments(datos);

        //aquí es donde le decimos a nuestra actividad que reemplace el menu que hay en pantalla
        //por este nuevo que hemos creado
        miTransaccion.replace(R.id.menu, menu_iluminado);
        miTransaccion.replace(R.id.herramientas, misFragmentos[queboton]);
        miTransaccion.commit();

        // CAMBIO 3 - registramos la pulsación del sensor
        dbHelper.registrarPulsacion(queboton);
    }

    @Override
    public void enciendeapaga(boolean estadoflash){
        try {
            if (estadoflash){
                Toast.makeText(this, "Flash apagado", Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    micamara.setTorchMode(idCamara, false); //setTorchMode permite apagar y enceder la camara
                }
            } else {
                Toast.makeText(this, "Flash encendido", Toast.LENGTH_LONG).show();

                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    micamara.setTorchMode(idCamara, true);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressLint("MissingSuperCall")
    public void onBackPressed(){
        moveTaskToBack(true);
    }

}