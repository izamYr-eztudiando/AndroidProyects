package com.salmantino.monedasapp.data;

import java.util.UUID;  //la genera el ide
//nuevas
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import com.salmantino.monedasapp.data.MonedasContract.MonedaEntry;

/**
 * Entidad "Moneda"
 */

// Definimos la clase moneda, su constructor y getters and setters (POJO)
public class Moneda {
    private String id;
    private String moneda;
    private String fecha;
    private String precio;
    private String material;
    private String imagen;

    public Moneda(String moneda,
                  String fecha, String precio,
                  String material, String imagen) {
        this.id = UUID.randomUUID().toString();//Genera un identificador único para cada moneda.
        this.moneda = moneda;
        this.fecha = fecha;
        this.precio = precio;
        this.material = material;
        this.imagen = imagen;
    }

    //Recibe un Cursor que es el resultado de una consulta en la base de datos
    //anotación suprime advertencia sobre el uso de getColumnIndex(), que puede
    // ser una operación ineficiente en algunos casos.
    @SuppressLint("Range")
    public Moneda(Cursor cursor) {
        //extrae los valores de cada columna correspondiente a la moneda.
        id = cursor.getString(cursor.getColumnIndex(MonedaEntry.ID));
        moneda = cursor.getString(cursor.getColumnIndex(MonedaEntry.MONEDA));
        fecha = cursor.getString(cursor.getColumnIndex(MonedaEntry.FECHA));
        precio = cursor.getString(cursor.getColumnIndex(MonedaEntry.PRECIO));
        material = cursor.getString(cursor.getColumnIndex(MonedaEntry.MATERIAL));
        imagen = cursor.getString(cursor.getColumnIndex(MonedaEntry.IMAGEN));
    }

    //convierte la instancia de Moneda en un objeto ContentValues, que es una estructura de datos
    // utilizada para insertar o actualizar registros en una base de datos en Android como SQlite3.
    //ContentValues: Almacena parejas clave-valor, donde las claves son los nombres de las columnas
    // definidos en MonedaEntry y los valores son los datos de la instancia de Moneda.
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(MonedaEntry.ID, id);
        values.put(MonedaEntry.MONEDA, moneda);
        values.put(MonedaEntry.FECHA, fecha);
        values.put(MonedaEntry.PRECIO, precio);
        values.put(MonedaEntry.MATERIAL, material);
        values.put(MonedaEntry.IMAGEN, imagen);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPrecio() {
        return precio;
    }

    public String getMaterial() {
        return material;
    }

    public String getImagen() {
        return imagen;
    }
}



