package com.projeto.clinicalfem.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Noticia {
    
    private String id;
    private String titulo;
    private String autor;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String texto;

    public String getId() {
        return id;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
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
