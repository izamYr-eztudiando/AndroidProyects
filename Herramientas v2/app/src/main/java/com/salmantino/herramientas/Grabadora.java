package com.salmantino.herramientas;
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

    MediaRecorder grabadora = null;
    String ruta = null;
    ImageView imgGrabacion = null;



    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btn_recorder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

    }
/*
    public void recorder(View view){
        if (grabacion == null) {
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.3gp";
            grabacion = new MediaRecorder();
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            grabacion.setOutputFile(archivoSalida);

            try {
                grabacion.prepare();
                grabacion.start();
            } catch (IOException e) {
                e.getSuppressed();
            }

            btn_recorder.setBackgroundResource(R.drawable.btn_grabar);
            Toast.makeText(requireActivity().getApplicationContext(), "Grabando", Toast.LENGTH_SHORT).show();
        } else {
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            btn_recorder.setBackgroundResource(R.drawable.btn_grabar);
            Toast.makeText(requireActivity().getApplicationContext(), "Grabación finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    public void reproduccion(View view){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        } catch (IOException e){
            e.printStackTrace();
        }

        mediaPlayer.start();
        Toast.makeText(requireActivity().getApplicationContext(), "Reproduciendo audio", Toast.LENGTH_SHORT).show();
    }
*/
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (ContextCompat.checkSelfPermission(requireActivity().getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity().getApplicationContext(), android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.RECORD_AUDIO}, 1000);
        }
        // Inflamos el layout del fragmento
        View viewGrabadora = inflater.inflate(R.layout.fragment_grabadora, container, false);

        // Ahora puedes encontrar el botón usando la vista inflada
        btn_recorder = viewGrabadora.findViewById(R.id.grabadora);

        // Aquí puedes configurar el botón, por ejemplo, agregar un listener

      /*  btn_recorder.setOnClickListener(view -> {
            if (grabacion == null) {
                recorder(view);
            } else {
                reproduccion(view);
            }
        });
*/
        return viewGrabadora;
    }
}