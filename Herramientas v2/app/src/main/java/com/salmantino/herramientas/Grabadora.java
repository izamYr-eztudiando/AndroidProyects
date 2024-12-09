package com.salmantino.herramientas;
import android.Manifest;
import android.media.MediaRecorder;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;


public class Grabadora extends Fragment {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private MediaRecorder grabadora = null; // objeto para la grabación de audio
    private String archivoSalida = null; // Ruta de salida del audio
    private Button btn_recorder;
    private MediaPlayer mediaPlayer; // objeto para la reproducción de audio
    private String[] permissions = { // Array de permisos para acceder a la grabación y reproducción del audio
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void iniciarGrabacion() {
        // Se pone la ruta definitiva a la variable
        archivoSalida = requireActivity().getExternalFilesDir(null).getAbsolutePath() + "/Grabacion.3gp";
        //archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.3gp";
        grabadora = new MediaRecorder(); // Se crea el objeto y se configura todo antes de grabar
        grabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        grabadora.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        grabadora.setOutputFile(archivoSalida);

        try {
            // Se prepara la grabadora
            grabadora.prepare();
            // Empieza
            grabadora.start();
            btn_recorder.setBackgroundResource(R.drawable.btn_grabar); // Cambia el icono a detener
            Toast.makeText(requireActivity().getApplicationContext(), "Grabando", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(requireActivity().getApplicationContext(), "Error al iniciar la grabación: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void detenerGrabacion() {
        // Si la grabadora no está nula
        if (grabadora != null) {
            // Se para
            grabadora.stop();
            // Se libera la grabadora del audio
            grabadora.release();
            grabadora = null;
            btn_recorder.setBackgroundResource(R.drawable.btn_grabar); // Cambia el icono a grabar
            Toast.makeText(requireActivity().getApplicationContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    private void reproducirAudio() {
        // Esto si ya tenía un audio anterior guardado
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Libera el MediaPlayer
        }
        // Creamos el objeto para la reproduccion del audio
        mediaPlayer = new MediaPlayer();
        try {
            // Metemos el archivoSalida que sería el audio grabado en la reproductora
            mediaPlayer.setDataSource(archivoSalida);
            // La preparamos
            mediaPlayer.prepare();
            // Y empezamos a reproducir el audio
            mediaPlayer.start();
            Toast.makeText(requireActivity().getApplicationContext(), "Reproduciendo audio", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireActivity().getApplicationContext(), "Error al reproducir el audio", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // Si el permiso no esta concedido, lo solicita
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        }

        // Inflamos el layout del fragmento
        View viewGrabadora = inflater.inflate(R.layout.fragment_grabadora, container, false);
        btn_recorder = viewGrabadora.findViewById(R.id.btnGrabadora);

        btn_recorder.setOnClickListener(view -> {
            if (grabadora == null) {
                iniciarGrabacion();
            } else {
                detenerGrabacion();
            }
        });

        // Botón para reproducir el audio grabado
        Button btn_reproducir = viewGrabadora.findViewById(R.id.btnReproductora);
        btn_reproducir.setOnClickListener(view -> reproducirAudio());

        return viewGrabadora;
    }

    // Libera los recursos del MediaRecorder y MediaPlayer cuando el fragmento se destruye
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (grabadora != null){
            grabadora.release();
            grabadora = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
