package com.projeto.clinicalfem.models;

import org.springframework.format.annotation.DateTimeFormat;

public class CadPaciente {
    
    private String id;

    private String rg;

    private String nome;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String datanasc;

    private String email;

    private String telefone;

    private String cpf;

    public CadPaciente(){
    }
    public CadPaciente(String id, String rg, String nome, String datanas, String email, String telefone, String cpf){
        this.id = id;
        this.rg = rg;
        this.nome = nome;
        this.datanasc = datanasc;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf; 
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
