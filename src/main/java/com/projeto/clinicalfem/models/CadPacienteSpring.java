package com.projeto.clinicalfem.models;

import java.time.LocalDate;

public class CadPacienteSpring {
     
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

    public LocalDate getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(LocalDate datanasc) {
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

    private String id;

    private String rg;

    private String nome;

    private LocalDate datanasc;

    private String email;

    private String telefone;

    private String cpf;
}
