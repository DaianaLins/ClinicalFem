package com.projeto.clinicalfem.models;

import java.time.LocalDate;
import java.util.Base64;

public class UsuarioMedicoSpring {
    private String nome;
    private String email;
    private String senha;
    private String id;
    private String numero;
    private boolean adm;
    private byte[] imagem;
    private LocalDate datanasc;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public boolean isAdm() {
        return adm;
    }
    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    public byte[] getImagem() {
        return imagem;
    }
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    public LocalDate getDatanasc() {
        return datanasc;
    }
    public void setDatanasc(LocalDate datanasc) {
        this.datanasc = datanasc;
    }
    public void setDataNascLocal(String datanasc) {
        if(datanasc != null){
            this.datanasc = LocalDate.parse(datanasc);
        }else{
            this.datanasc = null;
        }
    }
    UsuarioMedicoSpring(){}
    UsuarioMedicoSpring(String nome, String email, String senha, String id, String numero, boolean adm, String datanasc, String imagem){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.numero = numero;
        this.adm = adm;
        setImagemLocal(imagem);
        setDataNascLocal(datanasc);
    }
    public void setImagemLocal(String imagem) {
        if(imagem != null){
            byte[] decodedBytes = Base64.getDecoder().decode(imagem);
            this.imagem = decodedBytes;
        }else{
            this.imagem = null;
        }
    }

    public String getImagemLocal(){
        String encodedString = null;
        if(imagem != null){
            encodedString = Base64.getEncoder().encodeToString(imagem);
        }
        return encodedString;
    }
}