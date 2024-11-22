package com.salmantino.monedasapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.salmantino.monedasapp.data.MonedasContract.MonedaEntry;

/**
 * Manejador de la base de datos
 * permite manejar la base de datos de manera eficiente.
 * Implementa métodos para crear y actualizar la base de datos.
 */
public class MonedasDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Monedas.db";

    //llama al constructor de SQLiteOpenHelper, proporcionando el contexto,
    // el nombre de la base de datos y la versión.
    public MonedasDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Se llama cuando la base de datos se crea por primera vez.
    // Ejecuta una sentencia SQL para crear una tabla moneda con las columnas correspondientes
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + MonedaEntry.TABLE_NAME + " ("
                + MonedaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MonedaEntry.ID + " TEXT NOT NULL,"
                + MonedaEntry.MONEDA + " TEXT NOT NULL,"
                + MonedaEntry.FECHA + " TEXT NOT NULL,"
                + MonedaEntry.PRECIO + " TEXT NOT NULL,"
                + MonedaEntry.MATERIAL + " TEXT NOT NULL,"
                + MonedaEntry.IMAGEN + " TEXT,"
                + "UNIQUE (" + MonedaEntry.ID + "))");

        // Insertar datos ficticios para prueba inicial
        mockData(db);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockMoneda(sqLiteDatabase, new Moneda("Trishekel", "-216","300",
                "Plata","trishekel.PNG"));
        mockMoneda(sqLiteDatabase, new Moneda("Shekel", "-218", "200",
                "Plata","shekel.JPG"));
        mockMoneda(sqLiteDatabase, new Moneda("1/5 shekel", "-230", "3333",
                "Plata","shehel1-5.PNG"));
        mockMoneda(sqLiteDatabase, new Moneda("Dracma", "-237", "4444",
                "Plata","aureo_calico.PNG"));
        mockMoneda(sqLiteDatabase, new Moneda("calico", "-212", "5555",
                "Oro", "calico.PNG"));
        mockMoneda(sqLiteDatabase, new Moneda("Dicalco", "-256","2366",
                "Plata","shekel2a.PNG"));
        mockMoneda(sqLiteDatabase, new Moneda("Denario", "-208","1111",
                "Plata","trishekel3.PNG"));
        mockMoneda(sqLiteDatabase, new Moneda("3 Sicios", "-201", "3002",
                "Plata", "shekel2.PNG"));
    }
    //Inserta una moneda en la base de datos.
    // Utiliza el método toContentValues() de la clase Moneda para convertir la información de
    // la moneda en un formato aceptado por la base de datos.
    public long mockMoneda(SQLiteDatabase db, Moneda moneda) {
        return db.insert(
                MonedaEntry.TABLE_NAME,
                null,
                moneda.toContentValues());
    }
    //maneja actualizaciones de la base de datos.
    // Actualmente está vacío porque no hay cambios de versión definidos aún.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }
    //CRUD inserta una nueva moneda en la base de datos y devuelve el ID de la fila insertada.
    public long saveMoneda(Moneda moneda) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                MonedaEntry.TABLE_NAME,
                null,
                moneda.toContentValues());

    }
//Devuelve un cursor con todas las filas de la tabla moneda.
// El cursor es un puntero que permite iterar sobre los resultados de una consulta SQL.
    public Cursor getAllMonedas() {
        return getReadableDatabase()
                .query(
                        MonedaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }
    //Devuelve un cursor con los datos de una moneda específica, identificada por su ID.
    public Cursor getMonedaById(String monedaId) {
        Cursor c = getReadableDatabase().query(
                MonedaEntry.TABLE_NAME,
                null,
                MonedaEntry.ID + " LIKE ?",
                new String[]{monedaId},
                null,
                null,
                null);
        return c;
    }
    //Elimina una moneda de la base de datos, identificada por su ID.
    public int deleteMoneda(String monedaId) {
        return getWritableDatabase().delete(
                MonedaEntry.TABLE_NAME,
                MonedaEntry.ID + " LIKE ?",
                new String[]{monedaId});
    }
    //Actualiza los datos de una moneda específica en la base de datos.
    public int updateMoneda(Moneda moneda, String monedaId) {
        return getWritableDatabase().update(
                MonedaEntry.TABLE_NAME,
                moneda.toContentValues(),
                MonedaEntry.ID + " LIKE ?",
                new String[]{monedaId}
        );
    }
}
