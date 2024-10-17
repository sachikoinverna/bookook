package org.dam16.models;

public class GeneroModel {
    private int idGenero;
    private String genero;
    public GeneroModel() {
    }
    public GeneroModel(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }
    public int getIdGenero() {
        return idGenero;
    }
    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
}
