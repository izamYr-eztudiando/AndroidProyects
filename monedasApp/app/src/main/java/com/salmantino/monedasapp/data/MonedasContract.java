package com.salmantino.monedasapp.data;

import android.provider.BaseColumns;

/**
 * Esquema de la base de datos para la tabla de monedas
 * que almacena información sobre monedas.
 * actúa como un contenedor de constantes que describe la estructura de la tabla
 * dentro de una base de datos SQLite en Android.
 * Los contratos de bases de datos en Android se usan para:
 * Definir los nombres de las tablas y las columnas.
 * Proporcionar una referencia para evitar errores como nombres de columnas mal escritos.
 * Facilitar el manejo de la base de datos a lo largo del proyecto.
 */
public class MonedasContract {

    // BaseColumns es una interfaz en Android que define nombres de columna comunes como _ID y
    // _COUNT, que son estándar en muchas tablas de bases de datos SQLite.
    // Al implementar esta interfaz, la clase MonedaEntry heredará estas columnas automáticamente.
    public static abstract class MonedaEntry implements BaseColumns{

        public static final String TABLE_NAME ="moneda";

        public static final String ID = "id";
        public static final String MONEDA = "moneda";
        public static final String FECHA = "fecha";
        public static final String PRECIO = "precio";
        public static final String MATERIAL = "material";
        public static final String IMAGEN = "imagen";
    }
}
