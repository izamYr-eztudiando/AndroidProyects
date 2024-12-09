package com.salmantino.herramientas;

import static android.app.Activity.*;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camara extends Fragment {

    private static final int REQUEST_CODE = 22; // Código constante para identificar la solicitud de permisos y actividad de captura de imagen
    private Button btnCamara;
    private ImageView imageView; // En el Image View se mostrará la imagen capturada
    private Context context; // Utilizamos el contecto de la aplicación para acceder a recursos y servicios del sistema

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Devolvemos en el objeto context, el contexto del fragmento
        context = requireContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Insuflamos
        View viewCamara = inflater.inflate(R.layout.fragment_camara, container, false);
        btnCamara = viewCamara.findViewById(R.id.btncamara);
        imageView = viewCamara.findViewById(R.id.imgvCamara);
        // Cuando pulsemos el botón se ejecutará el código siguiente
        btnCamara.setOnClickListener(new View.OnClickListener() { 
            @Override
            public void onClick(View v) {
                // Se verifica si el permiso de la cámara se ha concedido
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Si no ha sido concedido, se solicita el permiso
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                } ele {
                    // Si se concedió, se crea un Intent para abrir la cámara y se llama a startActivityForResult para iniciar la actividad de captura de imagen
                    Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camaraIntent, REQUEST_CODE);
                }
            }
        });

        return viewCamara;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Verificacion de que la el código coincide y el resultado es bueno 
        if (requestCode == REQUEST_CODE &&  resultCode == RESULT_OK)
        {
            // Si la captura fue exitosa, se obtiene el bitmap de la imagen desde los extras del intent de la camara y se pone en el ImageView
            if (data != null && data.getExtras() != null) {
                Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(imgBitmap);
            }
        }
            // Sino mensaje de error
        else {
            Toast.makeText(context, "Cancelada", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            // Solicitud de permisos, verificación de que el usuario respondió cediendo los permisos
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camaraIntent, REQUEST_CODE);
            } else {
                Toast.makeText(context, "Permiso de cámaara denegdo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
