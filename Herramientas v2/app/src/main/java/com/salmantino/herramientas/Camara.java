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

    private static final int REQUEST_CODE = 22;
    private Button btnCamara;
    private ImageView imageView;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View viewCamara = inflater.inflate(R.layout.fragment_camara, container, false);
        btnCamara = viewCamara.findViewById(R.id.btncamara);
        imageView = viewCamara.findViewById(R.id.imgvCamara);

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                } else {
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
        if (requestCode == REQUEST_CODE &&  resultCode == RESULT_OK)
        {
            if (data != null && data.getExtras() != null) {
                Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(imgBitmap);
            }
        }
        else {
            Toast.makeText(context, "Cancelada", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camaraIntent, REQUEST_CODE);
            } else {
                Toast.makeText(context, "Permiso de cámaara denegdo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}