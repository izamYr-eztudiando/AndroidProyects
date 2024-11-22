package com.salmantino.herramientas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventoDbHelper extends SQLiteOpenHelper {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Sensores.db";

        public EventoDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + EventoContract.EventoEntry.TABLE_NAME + " ("
                    + EventoContract.EventoEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + EventoContract.EventoEntry.NUMSENSOR + " INTEGER NOT NULL, "
                    + EventoContract.EventoEntry.PULSACIONES + " INTEGER NOT NULL, "
                    + EventoContract.EventoEntry.FECHA + " TEXT NOT NULL, "
                    + EventoContract.EventoEntry.HORA + " TEXT NOT NULL)");
        }

        public void registrarPulsacion(int numSensor) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(EventoContract.EventoEntry.NUMSENSOR, numSensor);
            values.put(EventoContract.EventoEntry.PULSACIONES, 1); // Incremento por pulsación
            values.put(EventoContract.EventoEntry.FECHA, obtenerFechaActual());
            values.put(EventoContract.EventoEntry.HORA, obtenerHoraActual());

            db.insert(EventoContract.EventoEntry.TABLE_NAME, null, values);
            db.close();
        }

        private String obtenerFechaActual() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return dateFormat.format(new Date());
        }

        private String obtenerHoraActual() {
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            return timeFormat.format(new Date());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Implementar en caso de necesitar una actualización de la base de datos
        }
    }