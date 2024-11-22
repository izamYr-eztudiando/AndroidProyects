package com.salmantino.herramientas.data;

import android.provider.BaseColumns;

public class EventoContract {
    public static abstract class EventoEntry implements BaseColumns {
        public static final String TABLE_NAME = "eventos";
        public static final String ID = "id";
        public static final String NUMSENSOR = "numsensor";
        public static final String PULSACIONES = "pulsaciones";
        public static final String FECHA = "fecha";
        public static final String HORA = "hora";
    }
}