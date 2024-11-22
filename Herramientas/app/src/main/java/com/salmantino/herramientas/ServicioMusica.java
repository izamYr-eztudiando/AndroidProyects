package com.salmantino.herramientas;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class ServicioMusica extends Service{

    //Creamos el objeto media player con el que vamos a reproducir nuestra musica
    MediaPlayer miReproductor;

    public void onCreate(){
        super.onCreate();
        //Dentro del metodo donde inicializamos el objeto tipo media Player llamado miReproductor:
        miReproductor = MediaPlayer.create(this, R.raw.muestracortaestribillo);

        //loop para que se reproduzca constantemente
        miReproductor.setLooping(true);

        //volumen
        miReproductor.setVolume(50, 50);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //iniciamos el reproductor (objeto de tipo MediaPlayer).
        miReproductor.start();

        //llamamos devuelve la constante de clase start_not_sticky.
        return START_NOT_STICKY;
    }

    public void onDestroy(){
        super.onDestroy();

        //le decimos que si la musica está sonando que la pare.
        //el método isPlaying para saber si el objeto miReproductor esta activo o no.
        if (miReproductor.isPlaying()){
            //metodo para parar el reproductor.
            miReproductor.stop();
            //Metodo para liberar recursos.
            miReproductor.release();
            //para eliminar recursos de la memoria.

        }
    }


//    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
