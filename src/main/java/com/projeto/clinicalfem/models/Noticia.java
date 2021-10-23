package com.projeto.clinicalfem.models;

import java.util.Map;

public class Noticia {
    
    private String id;
    private String titulo;
    private String autor;
    private Map<String, String> timestamp;
    private String texto;

    public void setTimestamp(Map<String, String> timeStamp) {this.timestamp= timestamp;}
    public Map<String, String> getTimestamp() {return timestamp;}

    public String getId() {
        return id;
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
