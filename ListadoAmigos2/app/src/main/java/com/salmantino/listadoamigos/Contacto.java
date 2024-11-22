package com.salmantino.listadoamigos;

public class Contacto {
    public int id;
    public int imagen;
    public String nombre;
    public String Descripcion;

    public Contacto(int id, int imagen, String nombre, String descripcion){
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        Descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
