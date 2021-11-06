package com.projeto.clinicalfem.models;

public class UsuarioAtendente {
    private String nome;
    private String email;
    private String senha;
    private String id;
    private String numero;
    private boolean adm;

    public UsuarioAtendente(){
    }
    //ta lagado pra krl KKKKKKKKKK MANO SILiM AAAAAAAAAAAAAAAAAAAAA surtos
    public UsuarioAtendente(String nome, String email, String senha, String id, String numero, boolean adm){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.numero = numero;
        this.adm = adm;
    }
    
    public String getNome() {
        return nome;
    }
    public boolean isAdm() {
        return adm;
    }
    public void setAdm(boolean adm) {
        this.adm = adm;
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
    
}
