package com.salmantino.herramientas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.salmantino.herramientas.data.SensoresContract.SensoresEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SensoresDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Herramientas.db";

    public SensoresDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + SensoresEntry.TABLE_NAME + " ("
                + SensoresEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SensoresEntry.ID + " TEXT NOT NULL,"
                + SensoresEntry.NUMSENSOR + " INTEGER NOT NULL,"
                + SensoresEntry.FECHA + " TEXT NOT NULL,"
                + SensoresEntry.HORA + " TEXT NOT NULL,"
                + SensoresEntry.PULSACIONES + " TEXT NOT NULL,"
                + "UNIQUE (" + SensoresEntry.ID + "))");

        // Insertar datos ficticios para prueba inicial
        mockData(db);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase){
    }

    public long mockSensor(SQLiteDatabase db, Sensores sensor) {
        return db.insert(
                SensoresEntry.TABLE_NAME,
                null,
                sensor.toContentValues()
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long saveSensor(Sensores sensor) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                SensoresEntry.TABLE_NAME,
                null,
                sensor.toContentValues()
        );
    }

    public void registrarPulsacion(int numSensor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SensoresEntry.NUMSENSOR, numSensor);
        values.put(SensoresEntry.PULSACIONES, 1);
        values.put(SensoresEntry.FECHA, obtenerFechaActual());
        values.put(SensoresEntry.HORA, obtenerHoraActual());

        db.insert(SensoresEntry.TABLE_NAME, null, values);
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


}
