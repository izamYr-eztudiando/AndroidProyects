package com.salmantino.herramientas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.biometric.BiometricFragment;
import androidx.core.content.ContextCompat;
import java.util.concurrent.Executor;


public class Huella extends Fragment {

    private Activity activity;
    private ImageView btnHuella;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = requireActivity();
    }

    // OnCreate que contiene los fragmento de la huella
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View viewHuella = inflater.inflate(R.layout.fragment_huella, container, false);
        btnHuella = viewHuella.findViewById(R.id.imgHuella);

        btnHuella.setOnClickListener(view -> iniciarAutenticacion());

        return viewHuella;
    }

    private void iniciarAutenticacion() {
        // Crear un Executor para manejar el hilo principal
        Executor executor = ContextCompat.getMainExecutor(activity);

        // Crear el callback para manejar los resultados de autenticación
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(activity, "Autenticación exitosa", Toast.LENGTH_SHORT).show();
                // Aquí puedes registrar la pulsación en la base de datos o realizar otra acción
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(activity, "Autenticación fallida", Toast.LENGTH_SHORT).show();
            }
        };

        // Crear una instancia de BiometricPrompt
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, callback);

        // Configurar la información de autenticación
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación con huella")
                .setSubtitle("Confirma tu identidad")
                .setDescription("Usa tu huella digital para continuar")
                .setNegativeButtonText("Cancelar")
                .build();

        // Mostrar el cuadro de diálogo de autenticación
        biometricPrompt.authenticate(promptInfo);
    }
}