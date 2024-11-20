package com.example.practicabbddnavigationdrawer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static  final int DataBase_version=1;
    private static final String DataBase_Nombre="escuela.db";


    public static final String TABLE_NAME = "Alumnos";
    public static final String COLUMN_DNI = "dni";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_APELLIDOS = "apellidos";
    public static final String COLUMN_SEXO = "sexo";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_DNI + " TEXT PRIMARY KEY, " +
                    COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    COLUMN_APELLIDOS + " TEXT NOT NULL, " +
                    COLUMN_SEXO + " TEXT NOT NULL" +
                    ");";

    public DataBaseHelper(Context context) {
        super(context, DataBase_Nombre, null, DataBase_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS personas " );
        onCreate(sqLiteDatabase);
    }
}
