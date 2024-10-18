package org.dam16.models;

import java.util.ArrayList;
import java.util.Date;

public class LibroModel {
    private int id;
    private String titulo;
    private ArrayList<String> autor;
    private String genero;
    private Double precio;
    private Date fecha_publicacion;
    private int ejemplares;
    private boolean stock;

    public LibroModel() {
    }

    public LibroModel(int id, String titulo, ArrayList<String> autor, String genero, Double precio, Date fecha_publicacion, int ejemplares, boolean stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.precio = precio;
        this.fecha_publicacion = fecha_publicacion;
        this.ejemplares = ejemplares;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getAutor() {
        return autor;
    }

    public void setAutor(ArrayList<String> autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public int getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(int ejemplares) {
        this.ejemplares = ejemplares;
    }

    public boolean isStock() {
        return stock;
    }

    public void setStock(boolean stock) {
        this.stock = stock;
    }
}
