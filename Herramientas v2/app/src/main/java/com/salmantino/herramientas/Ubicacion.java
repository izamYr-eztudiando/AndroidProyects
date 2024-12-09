package com.salmantino.herramientas;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class Ubicacion extends Fragment {
    private FusedLocationProviderClient fusedLocationProviderClient; // Clase para acceder a la ubicación del dispositivo cliente
    private TextView locationTv; //Para mostrar la ubicación del dispositivo
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1; // Constante para identificar la solicitud de permisos de ubicación
    // Tiene el valor 1, porque es un código de solicitud único, es un identificador y no da conflicto.
    private Button btnUbicacion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializamos el objeto utilizando el contexto de la actividad
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity()); 
    }

    @SuppressLint("MissingPermission") // Anotación para suprimir advertencias sobre permisos de ubicación
    private void getCurrentLocation() {
        // Verificar si se concedieron los permisos de ubicación, sino, se solicita al usuario que los permita
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
            return;
        }

        // Creamos el objeto LocationRequest que define como solicitar la ubicación, estableciendo prioridad a la alta precisión
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // Intervalo de actualización en milisegundos
        locationRequest.setFastestInterval(5000); // Intervalo más rápido en milisegundos

        // Se solicita actualizaciones de ubicación, por ello el objeto fused... se actualiza
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) { // LocationResult clase API de Google Play Services que encapsula los resultados de una solicitud de actualizaciones de ubicación.
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) { // getLocation devuelve la lista de objetos Location que representan las ubicaciones obtenidas
                    if (location != null) {
                        locationTv.setText("Latitud: " + location.getLatitude() + "\n" + "Longitud: " + location.getLongitude());
                    }
                }
            }
        }, Looper.getMainLooper()); // Looper es una clase que permite que un hilo ejecute un bucle de mensajes, y este método lo que hace es obtener el Looper del main (hilo principal) necesario para ejecutar código con la interfaz

//        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
//            if (location != null) {
//                locationTv.setText("Latitud: " + location.getLatitude() + "\n" + "Longitud: " + location.getLongitude());
//            } else {
//                locationTv.setText("No se pudo obtener la ubicación");
//            }
//        });
    }


    // Este método se llama cuando el usuario responde a la solicitud de permisos. Si da los permisos se llama
    // al método getCurrentLocation(), sino, se muestra un mensaje de advertencia
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //array de los resultados de la solicitud de permisos, 
                //si hay más de 0 indica que el usuario respondió al menos a una solicitud. Y encima si esa respuesta (la posicion[0]) es concedida se llama al método getCurrentLocation()
                getCurrentLocation();
            } else {
                Toast.makeText(getContext(), "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
                //locationTv.setText("Permiso de ubicación denegado");
            }
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewUbicacion = inflater.inflate(R.layout.fragment_ubicacion, container, false);
        locationTv = viewUbicacion.findViewById(R.id.tv_ubicacion);
        btnUbicacion = viewUbicacion.findViewById(R.id.btn_actualizar_ubicacion);
        btnUbicacion.setOnClickListener(view -> getCurrentLocation());
        //getCurrentLocation();

        return viewUbicacion;
    }

}
