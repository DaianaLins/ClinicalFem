package com.projeto.clinicalfem.models;

import com.google.cloud.firestore.annotation.Exclude;
import java.util.Base64;

public class UsuarioMedico {
    private String nome;
    public String getNome() {
        return nome;
    }
    public String getCrm() {
        return crm;
    }
    public void setCrm(String crm) {
        this.crm = crm;
    }
    public boolean isAdm() {
        return adm;
    }
    public void setAdm(boolean adm) {
        this.adm = adm;
    }
    public String getNomeImagem() {
        return nomeImagem;
    }
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
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
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public String getDatanasc() {
        return datanasc;
    }
    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }
    private String email;
    private String senha;
    private String id;
    private String numero;
    private String tipo;
    private String imagem;
    private String datanasc;
    private String nomeImagem;
    private boolean adm;
    private String crm;

    public UsuarioMedico(){}
    public UsuarioMedico(String id, String nome, String imagem, String tipo, boolean adm, String datanasc, String email, String senha,  String numero, String nomeImagem, String crm){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.adm = adm;
        this.imagem = imagem;
        this.nomeImagem = nomeImagem;
        this.datanasc = datanasc;
        this.nomeImagem = nomeImagem;
        this.crm = crm;
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
    @Override
    public String toString() {
        return "UsuariosSpring [adm=" + adm + ", datanasc=" + datanasc + ", tipo="  + ", email=" + email 
                + ", id=" + id + ", imagem=" + imagem + ", nome=" + nome
                + ", numero=" + numero + ", senha=" + senha + "]";
    }
}
