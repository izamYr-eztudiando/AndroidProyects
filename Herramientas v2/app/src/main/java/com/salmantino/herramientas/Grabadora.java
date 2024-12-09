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
    private MediaRecorder grabadora = null;
    private String archivoSalida = null;
    private Button btn_recorder;
    private MediaPlayer mediaPlayer;
    private String[] permissions = {
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


//    private MediaRecorder grabacion;
//    private String archivoSalida = null;
//    private Button btn_recorder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void iniciarGrabacion() {
        archivoSalida = requireActivity().getExternalFilesDir(null).getAbsolutePath() + "/Grabacion.3gp";
        //archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.3gp";
        grabadora = new MediaRecorder();
        grabadora.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabadora.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        grabadora.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        grabadora.setOutputFile(archivoSalida);

        try {
            grabadora.prepare();
            grabadora.start();
            btn_recorder.setBackgroundResource(R.drawable.btn_grabar); // Cambia el icono a detener
            Toast.makeText(requireActivity().getApplicationContext(), "Grabando", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(requireActivity().getApplicationContext(), "Error al iniciar la grabación: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void detenerGrabacion() {
        if (grabadora != null) {
            grabadora.stop();
            grabadora.release();
            grabadora = null;
            btn_recorder.setBackgroundResource(R.drawable.btn_grabar); // Cambia el icono a grabar
            Toast.makeText(requireActivity().getApplicationContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    private void reproducirAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // Libera el MediaPlayer
        }

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
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
            // Permission is not granted, request it
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