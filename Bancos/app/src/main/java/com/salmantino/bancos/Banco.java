package com.salmantino.bancos;

public class Banco {
    public int id;
    public int imagen;
    public String nombre;
    public String apellido;
    public String categoria;
    public String empresa;
    public String telefono;
    public int accionistas;
    public int top;
    public int clientes;
    public int empleados;

    public Banco(int id, int imagen, String nombre, String apellido, String categoria, String empresa) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
        this.empresa = empresa;
    }

    public Banco(int id, int imagen, String nombre, String apellido, String categoria, String empresa, String telefono, int accionistas, int top, int clientes, int empleados) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
        this.empresa = empresa;
        this.telefono = telefono;
        this.accionistas = accionistas;
        this.top = top;
        this.clientes = clientes;
        this.empleados = empleados;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getAccionistas() {
        return accionistas;
    }

    public void setAccionistas(int accionistas) {
        this.accionistas = accionistas;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getClientes() {
        return clientes;
    }

    public void setClientes(int clientes) {
        this.clientes = clientes;
    }

    public int getEmpleados() {
        return empleados;
    }

    public void setEmpleados(int empleados) {
        this.empleados = empleados;
    }
}
