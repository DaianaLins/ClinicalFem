package com.projeto.clinicalfem.models;

public class Historico {
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialista() {
        return especialista;
    }
    public void setEspecialista(String especialista) {
        this.especialista = especialista;
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
    private String nome;
    private String especialista;
    private String data;
    private String texto;
    private String id;
    public Historico(){

    }
    public Historico(String nome, String especialista, String data, String texto, String id){
        this.nome = nome;
        this.especialista = especialista;
        this. data = data;
        this.texto = texto;
        this.id = id;
    }

}
