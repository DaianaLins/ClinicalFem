package com.projeto.clinicalfem.models;

import java.util.Base64;

import com.google.cloud.firestore.annotation.Exclude;



public class Usuarios {
    
    private String nome;
    private String email;
    private String senha;
    private String id;
    private String numero;
    private String tipo;
    private String imagem;
    private String datanasc;
  
	
    public Usuarios(){
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDatanasc() {
        return datanasc;
    }
    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
   
    public Usuarios(String nome, String email, String senha, String id, String numero, String tipo, String datanasc, String imagem){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.imagem = imagem;
        this.datanasc = datanasc;
    }
  
    
    public String getNome() {
        return nome;
    }
    
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Exclude
    public byte[] getImagemLocal() {
        byte[] decodedBytes = Base64.getDecoder().decode(imagem);
        return decodedBytes;
    }

    @Exclude
    public void setImagemLocal(byte[] imagem) {
        String encodedString = Base64.getEncoder().encodeToString(imagem);
        this.imagem = encodedString;
    }
    
}