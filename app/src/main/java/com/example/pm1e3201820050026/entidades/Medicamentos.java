package com.example.pm1e3201820050026.entidades;


import android.graphics.drawable.Drawable;

public class Medicamentos{
    private int id;
    private String Descripcion;
    private String Cantidad;
    private String Tiempo;
    private String Periocidad;
    private Drawable imagen;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String tiempo) {
        Tiempo = tiempo;
    }

    public String getPeriocidad() {
        return Periocidad;
    }

    public void setPeriocidad(String periocidad) {
        Periocidad = periocidad;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }


    public void setImagen(byte[] blob) {
    }

}