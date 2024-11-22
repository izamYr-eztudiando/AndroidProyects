package com.salmantino.herramientas;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Nivel extends Fragment implements SensorEventListener {

    // creamos los objetos sensorManager
    private SensorManager miManager;
    private Sensor miSensor;
    private NivelPantalla pantalla;

    public Nivel() {
        // Required empty public constructor
    }

    //Creamos el metodo onCreate
    public final void onCreate(Bundle savedInstanceState){
        //como sobreescribe debe recibir el super de la clase padre.
        super.onCreate(savedInstanceState);

        //con este código hemos accedido al servicio de sensores de nuestro dispositivo
        miManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        //ahora se va utilizar un sensor específico
        miSensor = miManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //se explica que la variable lado (clase nivelPantalla) puede variar de tamaño según
        // dispositivo
        int lado = getResources().getDimensionPixelSize(R.dimen.maximo);

        //vamos a instanciar nuestra clase nivelPantalla, recibe el contexto (this) en este caso
        // getActivity y el lado
        pantalla = new NivelPantalla(getActivity(), lado);

    }

    @Override
    //sabemos que el metodo onCreateView es el encargado de dibujar la interfaz
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //ahora mismo a interfaz pantalla la tenemos en la variable pantalla
        //return inflater.inflate(R.layout.fragment_nivel, container, false);

        //con esta línea de código podemos decirle a nuestra aplicación en sus fragmentos que pueda
        // girar sin caerse la app hay que hacer lo mismo en todos los fragmentos.
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        return pantalla;
    }

    public void onResume(){
        super.onResume();
        //ponemos a la escucha el sensor tal como indica la api:
        //mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        miManager.registerListener(this,miSensor,SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause(){

        super.onPause();
        //debemos desarrollar lo sufiente como para que cuando la aplicación pase a segundo plano
        // deje de registrar, deje de estar a la escucha de esos eventos
        miManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       //este metodo es el que hay que desarrollar si hay que registrar un cambio en la inclinación
       // del dispositivo
       //cuando se reciba una variación en el sensor que se va a guardar en el objeto event.values,
       // le pasamos ese objeto a nuestro método angulos
       pantalla.angulos(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
