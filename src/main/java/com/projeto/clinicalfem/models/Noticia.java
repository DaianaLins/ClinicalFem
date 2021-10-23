package com.projeto.clinicalfem.models;

import java.time.LocalDate;

public class Noticia {
    
    private String id;
    private String titulo;
    private String autor;
    private String data;
    private String texto;

    
    public String getId() {
        return id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setId(String id) {
        this.id = id;
    }
    
}
