package com.salmantino.herramientas.data;

import android.content.ContentValues;
import android.database.Cursor;

public class Evento {

    private int id;
    private int numSensor;
    private int pulsaciones;
    private String fecha;
    private String hora;

    public Evento(int numSensor, int pulsaciones, String fecha, String hora) {
        this.numSensor = numSensor;
        this.pulsaciones = pulsaciones;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Evento(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndex(EventoContract.EventoEntry.ID));
        this.numSensor = cursor.getInt(cursor.getColumnIndex(EventoContract.EventoEntry.NUMSENSOR));
        this.pulsaciones = cursor.getInt(cursor.getColumnIndex(EventoContract.EventoEntry.PULSACIONES));
        this.fecha = cursor.getString(cursor.getColumnIndex(EventoContract.EventoEntry.FECHA));
        this.hora = cursor.getString(cursor.getColumnIndex(EventoContract.EventoEntry.HORA));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(EventoContract.EventoEntry.NUMSENSOR, numSensor);
        values.put(EventoContract.EventoEntry.PULSACIONES, pulsaciones);
        values.put(EventoContract.EventoEntry.FECHA, fecha);
        values.put(EventoContract.EventoEntry.HORA, hora);
        return values;
    }
}
