package com.salmantino.herramientas.data;

import java.util.UUID;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.salmantino.herramientas.data.SensoresContract.SensoresEntry;

public class Sensores {
    private String id;
    private int numSensor;
    private String hora;
    private String fecha;
    private String pulsaciones;

    public Sensores(int numSensor, String hora,String fecha, String pulsaciones){
        this.id = UUID.randomUUID().toString();
        this.numSensor = numSensor;
        this.hora = hora;
        this.fecha = fecha;
        this.pulsaciones = pulsaciones;
    }

    @SuppressLint("Range")
    public Sensores(Cursor cursor){
        id = cursor.getString(cursor.getColumnIndex(SensoresEntry.ID));
        numSensor = cursor.getInt(cursor.getColumnIndex(SensoresEntry.NUMSENSOR));
        fecha = cursor.getString(cursor.getColumnIndex(SensoresEntry.FECHA));
        hora = cursor.getString(cursor.getColumnIndex(SensoresEntry.HORA));
        pulsaciones = cursor.getString(cursor.getColumnIndex(SensoresEntry.PULSACIONES));
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(SensoresEntry.NUMSENSOR, numSensor);
        values.put(SensoresEntry.FECHA, fecha);
        values.put(SensoresEntry.HORA, hora);
        values.put(SensoresEntry.PULSACIONES, pulsaciones);
        return values;
    }

    public String getId() {
        return id;
    }

    public int getnumSensor() {
        return numSensor;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora(){
        return hora;
    }

    public String getPulsaciones() {
        return pulsaciones;
    }
}
