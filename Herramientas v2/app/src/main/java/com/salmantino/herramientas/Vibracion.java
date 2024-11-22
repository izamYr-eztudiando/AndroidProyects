package com.salmantino.herramientas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Activity;
import android.widget.ImageView;
import android.os.Vibrator;
import android.content.Context;
import android.content.pm.ActivityInfo;

public class Vibracion extends Fragment {

    private final int[] MIPMAP_VIBRACION = {
            R.drawable.vibracion_off,
            R.drawable.vibracion_on
    };

    private Activity activity;
    private ImageView btnVibrar;

    public Vibracion() {
        // Required empty public constructor
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = requireActivity();
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewVibracion = inflater.inflate(R.layout.fragment_vibracion, container, false);
        btnVibrar = viewVibracion.findViewById(R.id.imgVibrator); // creación del boton vibrar, asociandolo con su view e id.
        //Método anónimo
        btnVibrar.setOnClickListener(view -> {
            Vibrator vibrador = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE); // creación del objeto vibrador activando la vibración
            vibrador.vibrate(1000); // tiempo de duración de la vibración
            // long[] tiempo = new long[]{0,1000,2000,3000}
            // vibrador.vibrate(tiempo, 0);
        });
        return viewVibracion; // retornamos la vibración ya configurada
    }

    public void onPause() { super.onPause(); }

}