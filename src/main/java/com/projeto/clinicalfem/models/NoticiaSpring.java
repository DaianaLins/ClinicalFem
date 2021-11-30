package com.projeto.clinicalfem.models;

import java.time.LocalDate;

public class NoticiaSpring {
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public NoticiaSpring(String id, String titulo, String autor, String data, String texto){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        setDataLocal(data);
        this.texto = texto;
    }
    public String getDataLocal() {
        return data.toString();
    }

    public void setDataLocal(String data) {
        if(data != null){
            this.data = LocalDate.parse(data);
        }else{
            this.data = null;
        }
    }
    private String id;
    private String titulo;
    private String autor;
    private LocalDate data;
    private String texto;
}
