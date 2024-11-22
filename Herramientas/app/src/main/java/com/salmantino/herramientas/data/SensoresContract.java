package com.salmantino.herramientas.data;

import android.provider.BaseColumns;

public class SensoresContract {

    public static abstract class SensoresEntry implements BaseColumns{

        public static final String TABLE_NAME = "sensores";

        public static final String ID = "id";
        public static final String NUMSENSOR = "numsensor";
        public static final String FECHA = "fecha";
        public static final String HORA = "hora";
        public static final String PULSACIONES = "pulsaciones";
    }
}
