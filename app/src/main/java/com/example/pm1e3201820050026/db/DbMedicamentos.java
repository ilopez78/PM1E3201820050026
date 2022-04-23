package com.example.pm1e3201820050026.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.example.pm1e3201820050026.entidades.Medicamentos;
import java.util.ArrayList;

public class DbMedicamentos extends DbHelper {

    Context context;

    public DbMedicamentos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarMedicamentos(String Descripcion, String Cantidad, String Tiempo, String Periocidad, Drawable Imagen) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Descripcion", Descripcion);
            values.put("Cantidad", Cantidad);
            values.put("Tiempo", Tiempo);
            values.put("Periocidad", Periocidad);
            values.put("Imagen", String.valueOf(Imagen));

            id = db.insert(TABLE_MEDICAMENTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }//Metodo Insert

    public ArrayList<Medicamentos> mostrarMedicamentos() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Medicamentos> listaMedicamentos = new ArrayList<>();
        Medicamentos medicamento;
        Cursor cursorMedicamentos;

        cursorMedicamentos = db.rawQuery("SELECT * FROM " + TABLE_MEDICAMENTOS, null);

        if (cursorMedicamentos.moveToFirst()) {
            do {
                medicamento = new Medicamentos();
                medicamento.setId(cursorMedicamentos.getInt(0));
                medicamento.setDescripcion(cursorMedicamentos.getString(1));
                medicamento.setCantidad(cursorMedicamentos.getString(2));
                medicamento.setTiempo(cursorMedicamentos.getString(3));
                medicamento.setPeriocidad(cursorMedicamentos.getString(4));
                //medicamento.setImagen(cursorMedicamentos.getBlob(5));
                listaMedicamentos.add(medicamento);
            } while (cursorMedicamentos.moveToNext());
        }

        cursorMedicamentos.close();

        return listaMedicamentos;
    }//Mostrar Medicamentos

    public Medicamentos verMedicamento(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Medicamentos medicamento = null;
        Cursor cursorMedicamentos;

        cursorMedicamentos = db.rawQuery("SELECT * FROM " + TABLE_MEDICAMENTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorMedicamentos.moveToFirst()) {
            medicamento = new Medicamentos();
            medicamento.setId(cursorMedicamentos.getInt(0));
            medicamento.setDescripcion(cursorMedicamentos.getString(1));
            medicamento.setCantidad(cursorMedicamentos.getString(2));
            medicamento.setTiempo(cursorMedicamentos.getString(3));
            medicamento.setPeriocidad(cursorMedicamentos.getString(4));
            //medicamento.setImagen(cursorMedicamentos.getBlob(5));
        }

        cursorMedicamentos.close();
        return medicamento;
    }//VerEmpelado

    public boolean editarMedicamento(int id, String Descripcion, String Cantidad, String Tiempo, String Periocidad) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_MEDICAMENTOS + " SET Descripcion = '" + Descripcion + "', Cantidad = '" + Cantidad + "', Tiempo = '" + Tiempo +"', Periocidad = '" + Periocidad + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }//editar empleado

    public boolean eliminarMedicamento(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_MEDICAMENTOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }//eliminar Medicamento
}