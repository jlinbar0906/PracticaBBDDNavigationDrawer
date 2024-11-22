package com.example.practicabbddnavigationdrawer.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.practicabbddnavigationdrawer.Alumno;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static  final int DataBase_version=1;
    private static final String DataBase_Nombre="escuela.db";

    private DataBaseHelper dbHelper;


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
    public long insertarAlumnos(Alumno alumno) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COLUMN_DNI, alumno.getDni());
        contentValues.put(DataBaseHelper.COLUMN_NOMBRE, alumno.getNombre());
        contentValues.put(DataBaseHelper.COLUMN_APELLIDOS, alumno.getApellidos());
        contentValues.put(DataBaseHelper.COLUMN_SEXO, alumno.getSexo());

        return db.insert(DataBaseHelper.TABLE_NAME, null, contentValues);
    }

    public int actualizarAlumnos(Alumno alumno) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.COLUMN_NOMBRE, alumno.getNombre());
        contentValues.put(DataBaseHelper.COLUMN_APELLIDOS, alumno.getApellidos());
        contentValues.put(DataBaseHelper.COLUMN_SEXO, alumno.getSexo());

        String where = DataBaseHelper.COLUMN_DNI + " = ?";
        String[] argumentoWhere = {alumno.getDni()};

        return db.update(DataBaseHelper.TABLE_NAME, contentValues, where, argumentoWhere);

    }
    public int borrarAlumnos(Alumno alumno){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String where = DataBaseHelper.COLUMN_DNI + " = ?";
        String[] argumentoWhere = {alumno.getDni()};

        return db.delete(DataBaseHelper.TABLE_NAME,where,argumentoWhere);
    }

    public List<Alumno> listarAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT dni, nombre, apellidos, sexo FROM alumnos", null);

        if (cursor.moveToFirst()) {
            do {
                String dni = cursor.getString(0);
                String nombre = cursor.getString(1);
                String apellidos = cursor.getString(2);
                String sexo = cursor.getString(3);
                lista.add(new Alumno(dni, nombre, apellidos, sexo));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }
}
