package com.projeto.clinicalfem.models;

import org.springframework.format.annotation.DateTimeFormat;

public class Agendamento {

    private String codigo;

    private String cpf;

    private String nome;

    private String servico;

    private String medico;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String dataC;

    private String horario;

    private String pagamento;

    private String valorC;

    private String obs;

    public Agendamento(){
    }
    public Agendamento(String codigo, String nome, String servico, String medico, String dataC, String horario, String valorC, String pagamento, String obs){
        this.codigo = codigo;
        this.nome = nome;
        this.servico = servico;
        this.medico = medico;
        this.dataC = dataC;
        this.horario = horario;
        this.pagamento = pagamento;
        this.valorC = valorC;
        this.obs = obs;
    }
    public String getCodigo() {
        return codigo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getDataC() {
        return dataC;
    }

    public void setDataC(String dataC) {
        this.dataC = dataC;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getValorC() {
        return valorC;
    }

    public void setValorC(String valorC) {
        this.valorC = valorC;
    }
}
