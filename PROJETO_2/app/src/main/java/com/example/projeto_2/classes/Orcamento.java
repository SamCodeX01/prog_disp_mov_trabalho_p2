package com.example.projeto_2.classes;

public class Orcamento {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String celular;
    private String endereco;
    private String nomePacote;
    private String dataEvento;
    private String qtdConvidados;
    private String horarioInicio;
    private String horarioTermino;
    private String enderecoEvento;


    // Construtores
    public Orcamento() {
        id = 0;
        nome = "";
        cpf = "";
        email = "";
        celular = "";
        endereco = "";
        nomePacote = "";
        dataEvento = "";
        qtdConvidados = "";
        horarioInicio = "";
        horarioTermino = "";
        enderecoEvento = "";
    }

    public Orcamento(int id, String nome, String cpf, String email, String celular, String endereco, String nomePacote, String dataEvento, String qtdConvidados, String horarioInicio, String horarioTermino, String enderecoEvento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.celular = celular;
        this.endereco = endereco;
        this.nomePacote = nomePacote;
        this.dataEvento = dataEvento;
        this.qtdConvidados = qtdConvidados;
        this.horarioInicio = horarioInicio;
        this.horarioTermino = horarioTermino;
        this.enderecoEvento = enderecoEvento;
    }


    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getQtdConvidados() {
        return qtdConvidados;
    }

    public void setQtdConvidados(String qtdConvidados) {
        this.qtdConvidados = qtdConvidados;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioTermino() {
        return horarioTermino;
    }

    public void setHorarioTermino(String horarioTermino) {
        this.horarioTermino = horarioTermino;
    }

    public String getEnderecoEvento() {
        return enderecoEvento;
    }

    public void setEnderecoEvento(String enderecoEvento) {
        this.enderecoEvento = enderecoEvento;
    }
}