package com.projeto.clinicalfem.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

public class UsuarioMedicoSpring {
    private String nome;
    private String email;
    private String senha;
    private String id;
    private String tipo;
    private String numero;
    private boolean adm;
    private byte[] imagem;
    private LocalDate datanasc;
    private String nomeImagem;

    public String getNomeImagem() {
        return nomeImagem;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

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
    public String getDataNascLocal() {
        return datanasc.toString();
    }

    public void setDataNascLocal(String datanasc) {
        if(datanasc != null){
            this.datanasc = LocalDate.parse(datanasc);
        }else{
            this.datanasc = null;
        }
    }
    UsuarioMedicoSpring(){}
    UsuarioMedicoSpring(String id, String nome, String imagem, String tipo, boolean adm, String datanasc, String email, String senha, String numero, String nomeImagem){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.id = id;
        this.numero = numero;
        this.adm = adm;
        this.tipo = tipo;
        setImagemLocal(imagem);
        setDataNascLocal(datanasc);
        this.nomeImagem = nomeImagem;
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
    @Override
    public String toString() {
        return "UsuariosSpring [adm=" + adm + ", datanasc=" + datanasc + ", tipo="  + tipo + ", email=" + email 
                + ", id=" + id + ", imagem=" + Arrays.toString(imagem) + ", nome=" + nome
                + ", numero=" + numero + ", senha=" + senha + "]";
    }
}
