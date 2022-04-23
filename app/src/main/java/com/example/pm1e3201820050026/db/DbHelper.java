package com.example.pm1e3201820050026.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "medicamentos.db";
    public static final String TABLE_MEDICAMENTOS = "t_medicamentos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MEDICAMENTOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Descripcion TEXT NOT NULL," +
                "Cantidad INTEGER NOT NULL," +
                "Tiempo TEXT NOT NULL," +
                "Periocidad INTEGER," +
                "Imagen BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MEDICAMENTOS);
        onCreate(sqLiteDatabase);
    }
}